# Router Rule Matcher — Complete Exercise

> **Interviewer document. Contains the answers.** Candidate-facing text is in the "Handout" section of each phase — those are also standalone files in `handouts/`.

An OO design exercise in four phases. Phases 1–3 are code driven by tests; Phase 4 is a whiteboard discussion.

## What this measures

Not whether the code works. **How much has to move when the requirements change.** Every phase changes the spec in a way a good Phase 1 design absorbs and a poor one cannot.

Hand out one phase at a time. Never show a later phase early — the change has to be unforeseen for the exercise to measure anything.

## The session

| Phase | Time | Form | Change introduced |
|---|---|---|---|
| 1 | 30 min | Code | Ordered rules, one field each, first match wins |
| 2 | 20 min | Code | CIDR subnets, port ranges, `any` |
| 3 | 20 min | Code | Nested `ALL`/`ANY` criteria; priority replaces list order |
| 4 | 20 min | Whiteboard | 500k packets/sec, 50k rules, live rule edits |

**Total 90 minutes.** Stop wherever time runs out — Phase 1 alone decides many candidates.

| Level | Run | Note |
|---|---|---|
| Junior | Phases 1–2 | Phase 3 is a stretch if fast. Skip Phase 4 — it produces recitation, not signal |
| Mid | Phases 1–3 | Phase 4 only if time remains |
| Senior / Staff | Phases 1–3, then 4 | Phase 4 carries real weight. Push on the runtime rule change |

The candidate provides `RuleBasedFirewall implements Firewall` with a no-arg constructor. Everything else is theirs to design.

## What each phase costs

| Phase | Files added | Files modified | Untouched |
|---|---|---|---|
| 1 → 2 | `CidrMatcher`, `PortRangeMatcher`, `AnyMatcher`, `ValueMatcherParser`, `ValueMatcherFactory` | `RuleFactory` (one line) | `Rule`, `RuleSet`, `Criterion`, `PacketField`, serializer, `Firewall` |
| 2 → 3 | `FieldCriterion`, `AllOf`, `AnyOf`, `CriterionFactory`, `CriterionSpec` | `Criterion` (record → interface), `Rule` (+priority), `RuleSet` (constructor sorts), `RuleFactory` | **`RuleSet.decide()`**, all matchers, serializer, `Firewall`, `RuleBasedFirewall` |

`RuleSet.decide()` surviving both changes untouched is the thing to point at in a debrief. Ordering lived in one place, so replacing list order with priority order was a constructor change.

---

# Phase 1

## Handout

**Time:** ~30 minutes. Java. Jackson and JUnit are on the classpath.

### The problem

A **packet** has a source IP, a destination IP, a destination port, and a protocol (`TCP` or `UDP`).

A **rule** has an id, an action (`ALLOW` or `DENY`), and matches on **exactly one** field: source IP, destination IP, destination port, or protocol.

Rules are an **ordered list**. For each packet, the **first matching rule** decides. If no rule matches, the action is `DENY`.

### Example

| Id | Matches | Action |
|---|---|---|
| R1 | protocol `UDP` | DENY |
| R2 | destinationPort `443` | ALLOW |
| R3 | sourceIp `10.0.0.5` | ALLOW |
| R4 | destinationPort `22` | DENY |

```
packet 0:  10.0.0.5     -> 93.184.216.34:443   TCP
packet 1:  10.0.0.5     -> 8.8.8.8:53          UDP
packet 2:  192.168.1.9  -> 10.0.0.1:22         TCP
packet 3:  172.16.0.4   -> 10.0.0.1:8080       TCP
packet 4:  10.0.0.5     -> 10.0.0.1:8080       TCP
```

Expected output — one object per packet, in input order:

```json
[
  { "packet": 0, "rule": "R2",  "action": "ALLOW" },
  { "packet": 1, "rule": "R1",  "action": "DENY"  },
  { "packet": 2, "rule": "R4",  "action": "DENY"  },
  { "packet": 3, "rule": null,  "action": "DENY"  },
  { "packet": 4, "rule": "R3",  "action": "ALLOW" }
]
```

Whitespace and key order are not significant — the output is compared as parsed JSON.

### Provided

```java
public enum Action   { ALLOW, DENY }
public enum Protocol { TCP, UDP }

public record Packet(String sourceIp,
                     String destinationIp,
                     int destinationPort,
                     Protocol protocol) {}

/** One rule as flat configuration data. */
public record RuleSpec(String id,
                       Action action,
                       String field,   // sourceIp | destinationIp | destinationPort | protocol
                       String value) {}

public interface Firewall {
    /** @return the decisions as a JSON array */
    String evaluate(List<RuleSpec> rules, List<Packet> packets);
}
```

### What to implement

A class `RuleBasedFirewall implements Firewall` with a no-arg constructor. Everything else is yours to design.

`FirewallPhase1Test` is the spec. Do not modify it.

### What we are looking for

This is a small problem, so the answer is not "does it work" — it is **how you structure it**. Assume this code will keep changing: new fields, new ways of comparing them, different ordering rules.

We will look at how you model rules and matching, where ordering and the default action live, where JSON serialization lives, and whether the code reads without comments.

---

**When you are done, tell us — there is a Phase 2.**

## Provided scaffolding

Given to the candidate. Deliberately dumb: there is no `Rule` type and no `matches(Packet)` method, because handing those over would give away the central insight.

**`Action.java`**

```java
package interview.exercises.router.phase1;

public enum Action {
    ALLOW,
    DENY
}
```

**`Protocol.java`**

```java
package interview.exercises.router.phase1;

public enum Protocol {
    TCP,
    UDP
}
```

**`Packet.java`**

```java
package interview.exercises.router.phase1;

public record Packet(String sourceIp,
                     String destinationIp,
                     int destinationPort,
                     Protocol protocol) {
}
```

**`RuleSpec.java`**

```java
package interview.exercises.router.phase1;

/**
 * One rule as flat configuration data, exactly as it arrives from outside.
 * Turning this into a rule model is the exercise.
 */
public record RuleSpec(String id,
                       Action action,
                       String field,
                       String value) {
}
```

**`Decision.java`**

```java
package interview.exercises.router.phase1;

/**
 * The outcome for a single packet.
 *
 * @param ruleId the rule that decided, or {@code null} when no rule matched
 */
public record Decision(String ruleId, Action action) {
}
```

**`Firewall.java`**

```java
package interview.exercises.router.phase1;

import java.util.List;

public interface Firewall {

    /**
     * Evaluates every packet against the rules and returns the decisions as a JSON array.
     */
    String evaluate(List<RuleSpec> rules, List<Packet> packets);
}
```

## Reference solution

The central decision is that matching has **two independent axes**: *which* field to read, and *how* to compare it. `Criterion` composes a `PacketField` with a `ValueMatcher` and keeps them independent. Everything downstream follows from whether the candidate finds this — fuse the axes and Phase 2 costs 4 fields × 3 comparisons of classes; separate them and it costs two.

Note also that `Rule` answers about itself, ordering lives only in `RuleSet`, and nothing in the model knows JSON exists.

### Grading

| Concept | Good names | What its absence tells you |
|---|---|---|
| Rule that answers yes/no | `Rule` with `boolean matches(Packet)` | Matching done in the matcher via getters — polymorphism not found |
| The comparison itself | `Criterion`, `Condition`, `Predicate<Packet>` | Fused into `Rule` — Phase 3 composites will hurt |
| Reading a field | `PacketField`, `FieldSelector` | Usually missing; its absence is why field × value becomes an N×M switch |
| Spec → model | `RuleFactory` | `new` scattered — Phase 2 touches many files |
| The ordered policy | `RuleSet`, `Policy`, `RuleChain` | A bare `List<Rule>` leaves ordering logic homeless |
| One outcome | `Decision`, `Verdict` | Returning a bare `Action` loses which rule fired |
| JSON | `DecisionSerializer` | `toString()` returning JSON — serialization leaked |

**Questions to ask**

- What's the complexity per packet? Per thousand packets?
- What would change if the rule list had 50,000 entries?
- Is a rule that matches nothing distinguishable from a rule that denies?

**Test note.** `reversingRuleOrderReversesTheDecision` catches anyone who sorts or dedupes rules instead of respecting order — same packet, same two rules, opposite outcome.

**`match/Criterion.java`**

```java
package interview.exercises.router.phase1.match;

import interview.exercises.router.phase1.Packet;

/**
 * Reads one field off a packet and compares it.
 * <p>
 * Field and comparison vary independently, so adding a new comparison does not
 * multiply with the number of fields.
 */
public record Criterion(PacketField field, ValueMatcher matcher) {

    public boolean matches(Packet packet) {
        return matcher.matches(field.valueOf(packet));
    }
}
```

**`match/ExactMatcher.java`**

```java
package interview.exercises.router.phase1.match;

public record ExactMatcher(String expected) implements ValueMatcher {

    @Override
    public boolean matches(String fieldValue) {
        return expected.equals(fieldValue);
    }
}
```

**`match/PacketField.java`**

```java
package interview.exercises.router.phase1.match;

import interview.exercises.router.phase1.Packet;

import java.util.Arrays;

/**
 * Which field of a packet a criterion reads.
 * <p>
 * This is one of the two independent axes of matching: <em>what</em> to read.
 * How the value is compared is the other, and lives in {@link ValueMatcher}.
 */
public enum PacketField {

    SOURCE_IP("sourceIp") {
        @Override
        public String valueOf(Packet packet) {
            return packet.sourceIp();
        }
    },
    DESTINATION_IP("destinationIp") {
        @Override
        public String valueOf(Packet packet) {
            return packet.destinationIp();
        }
    },
    DESTINATION_PORT("destinationPort") {
        @Override
        public String valueOf(Packet packet) {
            return String.valueOf(packet.destinationPort());
        }
    },
    PROTOCOL("protocol") {
        @Override
        public String valueOf(Packet packet) {
            return packet.protocol().name();
        }
    };

    private final String specName;

    PacketField(String specName) {
        this.specName = specName;
    }

    public abstract String valueOf(Packet packet);

    public static PacketField fromSpecName(String specName) {
        return Arrays.stream(values())
                .filter(field -> field.specName.equals(specName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown packet field: " + specName));
    }
}
```

**`match/ValueMatcher.java`**

```java
package interview.exercises.router.phase1.match;

/**
 * How a single field value is compared.
 * <p>
 * Phase 1 needs only exact equality. Later phases add subnet and range matching
 * by adding implementations here, not by changing anything that uses this type.
 */
@FunctionalInterface
public interface ValueMatcher {

    boolean matches(String fieldValue);
}
```

**`rule/Rule.java`**

```java
package interview.exercises.router.phase1.rule;

import interview.exercises.router.phase1.Action;
import interview.exercises.router.phase1.Packet;
import interview.exercises.router.phase1.match.Criterion;

/**
 * A rule knows whether it applies to a packet and what to do about it.
 * It does not know its position in the policy, or how decisions are reported.
 */
public record Rule(String id, Action action, Criterion criterion) {

    public boolean matches(Packet packet) {
        return criterion.matches(packet);
    }
}
```

**`rule/RuleFactory.java`**

```java
package interview.exercises.router.phase1.rule;

import interview.exercises.router.phase1.RuleSpec;
import interview.exercises.router.phase1.match.Criterion;
import interview.exercises.router.phase1.match.ExactMatcher;
import interview.exercises.router.phase1.match.PacketField;

/**
 * The single place that knows how to turn flat spec data into the rule model.
 */
public class RuleFactory {

    public Rule create(RuleSpec spec) {
        Criterion criterion = new Criterion(
                PacketField.fromSpecName(spec.field()),
                new ExactMatcher(spec.value()));

        return new Rule(spec.id(), spec.action(), criterion);
    }
}
```

**`rule/RuleSet.java`**

```java
package interview.exercises.router.phase1.rule;

import interview.exercises.router.phase1.Action;
import interview.exercises.router.phase1.Decision;
import interview.exercises.router.phase1.Packet;

import java.util.List;
import java.util.Optional;

/**
 * An ordered policy: the first matching rule decides, otherwise the default applies.
 * <p>
 * Ordering lives here and nowhere else, so changing how rules are ordered does not
 * touch how they match.
 */
public class RuleSet {

    private final List<Rule> rules;
    private final Action defaultAction;

    public RuleSet(List<Rule> rules, Action defaultAction) {
        this.rules = List.copyOf(rules);
        this.defaultAction = defaultAction;
    }

    public Decision decide(Packet packet) {
        Optional<Rule> firstMatch = rules.stream()
                .filter(rule -> rule.matches(packet))
                .findFirst();

        return firstMatch
                .map(rule -> new Decision(rule.id(), rule.action()))
                .orElseGet(() -> new Decision(null, defaultAction));
    }
}
```

**`json/DecisionSerializer.java`**

```java
package interview.exercises.router.phase1.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import interview.exercises.router.phase1.Decision;

import java.util.List;

/**
 * Renders decisions as JSON. Nothing in the rule model knows this class exists.
 */
public class DecisionSerializer {

    private final ObjectMapper mapper = new ObjectMapper();

    public String toJson(List<Decision> decisions) {
        ArrayNode array = mapper.createArrayNode();

        for (int packetIndex = 0; packetIndex < decisions.size(); packetIndex++) {
            Decision decision = decisions.get(packetIndex);

            ObjectNode node = array.addObject();
            node.put("packet", packetIndex);
            node.put("rule", decision.ruleId());
            node.put("action", decision.action().name());
        }

        return array.toString();
    }
}
```

**`RuleBasedFirewall.java`**

```java
package interview.exercises.router.phase1;

import interview.exercises.router.phase1.json.DecisionSerializer;
import interview.exercises.router.phase1.rule.Rule;
import interview.exercises.router.phase1.rule.RuleFactory;
import interview.exercises.router.phase1.rule.RuleSet;

import java.util.List;

public class RuleBasedFirewall implements Firewall {

    private static final Action DEFAULT_ACTION = Action.DENY;

    private final RuleFactory ruleFactory = new RuleFactory();
    private final DecisionSerializer serializer = new DecisionSerializer();

    @Override
    public String evaluate(List<RuleSpec> specs, List<Packet> packets) {
        List<Rule> rules = specs.stream()
                .map(ruleFactory::create)
                .toList();

        RuleSet ruleSet = new RuleSet(rules, DEFAULT_ACTION);

        List<Decision> decisions = packets.stream()
                .map(ruleSet::decide)
                .toList();

        return serializer.toJson(decisions);
    }
}
```

## Test

**`FirewallPhase1Test.java`**

```java
package interview.exercises.router.phase1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FirewallPhase1Test {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final List<RuleSpec> rules = List.of(
            new RuleSpec("R1", Action.DENY,  "protocol",        "UDP"),
            new RuleSpec("R2", Action.ALLOW, "destinationPort", "443"),
            new RuleSpec("R3", Action.ALLOW, "sourceIp",        "10.0.0.5"),
            new RuleSpec("R4", Action.DENY,  "destinationPort", "22"));

    private final List<Packet> packets = List.of(
            new Packet("10.0.0.5",    "93.184.216.34", 443,  Protocol.TCP),
            new Packet("10.0.0.5",    "8.8.8.8",       53,   Protocol.UDP),
            new Packet("192.168.1.9", "10.0.0.1",      22,   Protocol.TCP),
            new Packet("172.16.0.4",  "10.0.0.1",      8080, Protocol.TCP),
            new Packet("10.0.0.5",    "10.0.0.1",      8080, Protocol.TCP));

    private Firewall firewall;

    @Before
    public void setUp() {
        firewall = new RuleBasedFirewall();
    }

    @Test
    public void firstMatchingRuleDecides() throws Exception {
        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "R2",  "action": "ALLOW" },
                  { "packet": 1, "rule": "R1",  "action": "DENY"  },
                  { "packet": 2, "rule": "R4",  "action": "DENY"  },
                  { "packet": 3, "rule": null,  "action": "DENY"  },
                  { "packet": 4, "rule": "R3",  "action": "ALLOW" }
                ]""",
                firewall.evaluate(rules, packets));
    }

    @Test
    public void ruleOrderDecidesWhenTwoRulesMatch() throws Exception {
        // R3 would allow this packet, but R1 denies it first.
        List<Packet> udpFromAllowedSource = List.of(
                new Packet("10.0.0.5", "8.8.8.8", 53, Protocol.UDP));

        assertJsonEquals("""
                [ { "packet": 0, "rule": "R1", "action": "DENY" } ]""",
                firewall.evaluate(rules, udpFromAllowedSource));
    }

    @Test
    public void reversingRuleOrderReversesTheDecision() throws Exception {
        List<RuleSpec> allowFirst = List.of(
                new RuleSpec("R3", Action.ALLOW, "sourceIp", "10.0.0.5"),
                new RuleSpec("R1", Action.DENY,  "protocol", "UDP"));

        List<Packet> udpFromAllowedSource = List.of(
                new Packet("10.0.0.5", "8.8.8.8", 53, Protocol.UDP));

        assertJsonEquals("""
                [ { "packet": 0, "rule": "R3", "action": "ALLOW" } ]""",
                firewall.evaluate(allowFirst, udpFromAllowedSource));
    }

    @Test
    public void unmatchedPacketIsDeniedWithNoRule() throws Exception {
        List<Packet> unmatched = List.of(
                new Packet("172.16.0.4", "10.0.0.1", 8080, Protocol.TCP));

        assertJsonEquals("""
                [ { "packet": 0, "rule": null, "action": "DENY" } ]""",
                firewall.evaluate(rules, unmatched));
    }

    @Test
    public void emptyRuleListDeniesEverything() throws Exception {
        assertJsonEquals("""
                [
                  { "packet": 0, "rule": null, "action": "DENY" },
                  { "packet": 1, "rule": null, "action": "DENY" }
                ]""",
                firewall.evaluate(List.of(), packets.subList(0, 2)));
    }

    @Test
    public void noPacketsProducesAnEmptyArray() throws Exception {
        assertJsonEquals("[]", firewall.evaluate(rules, List.of()));
    }

    @Test
    public void eachFieldCanBeMatchedOn() throws Exception {
        List<RuleSpec> oneRulePerField = List.of(
                new RuleSpec("SRC",   Action.ALLOW, "sourceIp",        "1.1.1.1"),
                new RuleSpec("DST",   Action.ALLOW, "destinationIp",   "2.2.2.2"),
                new RuleSpec("PORT",  Action.ALLOW, "destinationPort", "99"),
                new RuleSpec("PROTO", Action.ALLOW, "protocol",        "UDP"));

        List<Packet> packetPerField = List.of(
                new Packet("1.1.1.1", "9.9.9.9", 1,  Protocol.TCP),
                new Packet("9.9.9.9", "2.2.2.2", 1,  Protocol.TCP),
                new Packet("9.9.9.9", "9.9.9.9", 99, Protocol.TCP),
                new Packet("9.9.9.9", "9.9.9.9", 1,  Protocol.UDP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "SRC",   "action": "ALLOW" },
                  { "packet": 1, "rule": "DST",   "action": "ALLOW" },
                  { "packet": 2, "rule": "PORT",  "action": "ALLOW" },
                  { "packet": 3, "rule": "PROTO", "action": "ALLOW" }
                ]""",
                firewall.evaluate(oneRulePerField, packetPerField));
    }

    /** Compares as parsed JSON, so whitespace and key order do not matter. */
    private static void assertJsonEquals(String expected, String actual) throws Exception {
        assertEquals(MAPPER.readTree(expected), MAPPER.readTree(actual));
    }
}
```

---

# Phase 2

## Handout

**Time:** ~20 minutes.

Exact matches aren't enough. A rule can now match:

- a **CIDR subnet** — `10.0.0.0/8`
- a **port range** — `8000-8999`, inclusive at both ends
- **any** — every value of that field

| Id | Matches | Action |
|---|---|---|
| R1 | protocol `UDP` | DENY |
| R2 | destinationPort `8000-8999` | ALLOW |
| R3 | sourceIp `10.0.0.0/8` | ALLOW |
| R4 | destinationIp `any` | DENY |

The new syntax arrives in the same `value` field of `RuleSpec`.

Everything else is unchanged: ordered list, first match wins, default `DENY`, same output format.

`FirewallPhase2Test` is the spec.

---

**When you are done, tell us — there is a Phase 3.**

## Provided scaffolding

Unchanged from Phase 1 except where noted below.

**`Action.java`**

```java
package interview.exercises.router.phase2;

public enum Action {
    ALLOW,
    DENY
}
```

**`Protocol.java`**

```java
package interview.exercises.router.phase2;

public enum Protocol {
    TCP,
    UDP
}
```

**`Packet.java`**

```java
package interview.exercises.router.phase2;

public record Packet(String sourceIp,
                     String destinationIp,
                     int destinationPort,
                     Protocol protocol) {
}
```

**`RuleSpec.java`**

```java
package interview.exercises.router.phase2;

/**
 * One rule as flat configuration data, exactly as it arrives from outside.
 * Turning this into a rule model is the exercise.
 */
public record RuleSpec(String id,
                       Action action,
                       String field,
                       String value) {
}
```

**`Decision.java`**

```java
package interview.exercises.router.phase2;

/**
 * The outcome for a single packet.
 *
 * @param ruleId the rule that decided, or {@code null} when no rule matched
 */
public record Decision(String ruleId, Action action) {
}
```

**`Firewall.java`**

```java
package interview.exercises.router.phase2;

import java.util.List;

public interface Firewall {

    /**
     * Evaluates every packet against the rules and returns the decisions as a JSON array.
     */
    String evaluate(List<RuleSpec> rules, List<Packet> packets);
}
```

## Reference solution

Three new `ValueMatcher` implementations, plus one abstraction for choosing between them from the value syntax. `RuleFactory` changes by one line. Nothing else moves.

`RuleSpec` does not change — **say nothing about this**. If their Phase 1 design was right they will notice; if it wasn't, watch them try to add a type field.

### Grading

Look for new **classes**, not new **branches**. The failure mode is `Rule` growing a `matchType` enum and a switch inside `matches()`.

The sharper tell is whether they conflated *which field* with *how it's compared*. Candidates who fused the axes now write `SourceIpExactRule`, `SourceIpCidrRule`, `PortExactRule`, `PortRangeRule`, `ProtocolExactRule`. If that happens, ask directly: *"if we added a fifth field, how many classes would you add?"* Let them do the multiplication out loud — it lands better than being told.

**Questions to ask**

- What happens if a CIDR value is configured against the port field?
- Is `any` a matcher, or the absence of a matcher? Does it matter?
- `10.0.0.0/8`, `10.0.0.5/32` and `10.0.0.5` — three types or one?

**Test notes.** `portRangeIsInclusiveAtBothEnds` and `cidrBoundariesAreRespected` sit exactly on the boundaries (`7999`/`8000`, `8999`/`9000`, `192.168.0.255`/`192.168.1.0`), because hand-rolled CIDR math is where off-by-ones live. `slashZeroMatchesEveryAddress` catches the `-1 << 32` bug — in Java that shift silently returns `-1` instead of `0`.

**`match/AnyMatcher.java`**

```java
package interview.exercises.router.phase2.match;

/**
 * Matches every value of its field. Stateless, so a single instance is enough.
 */
public final class AnyMatcher implements ValueMatcher {

    public static final AnyMatcher INSTANCE = new AnyMatcher();

    private AnyMatcher() {
    }

    @Override
    public boolean matches(String fieldValue) {
        return true;
    }
}
```

**`match/CidrMatcher.java`**

```java
package interview.exercises.router.phase2.match;

/**
 * Matches an IPv4 address inside a subnet, written {@code 10.0.0.0/8}.
 * <p>
 * The address and prefix are collapsed to a network and a mask once, at construction,
 * so matching is two integer operations.
 */
public final class CidrMatcher implements ValueMatcher {

    private final int network;
    private final int mask;

    public CidrMatcher(String cidr) {
        String[] parts = cidr.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Not a CIDR block: " + cidr);
        }
        this.mask = maskOf(Integer.parseInt(parts[1]));
        this.network = toInt(parts[0]) & mask;
    }

    @Override
    public boolean matches(String fieldValue) {
        return (toInt(fieldValue) & mask) == network;
    }

    private static int maskOf(int prefixLength) {
        if (prefixLength < 0 || prefixLength > 32) {
            throw new IllegalArgumentException("Prefix length out of range: " + prefixLength);
        }
        return prefixLength == 0 ? 0 : -1 << (32 - prefixLength);
    }

    private static int toInt(String ipv4) {
        String[] octets = ipv4.split("\\.");
        if (octets.length != 4) {
            throw new IllegalArgumentException("Not an IPv4 address: " + ipv4);
        }
        int value = 0;
        for (String octet : octets) {
            value = (value << 8) | Integer.parseInt(octet);
        }
        return value;
    }
}
```

**`match/Criterion.java`**

```java
package interview.exercises.router.phase2.match;

import interview.exercises.router.phase2.Packet;

/**
 * Reads one field off a packet and compares it.
 * <p>
 * Field and comparison vary independently, so adding a new comparison does not
 * multiply with the number of fields.
 */
public record Criterion(PacketField field, ValueMatcher matcher) {

    public boolean matches(Packet packet) {
        return matcher.matches(field.valueOf(packet));
    }
}
```

**`match/ExactMatcher.java`**

```java
package interview.exercises.router.phase2.match;

public record ExactMatcher(String expected) implements ValueMatcher {

    @Override
    public boolean matches(String fieldValue) {
        return expected.equals(fieldValue);
    }
}
```

**`match/PacketField.java`**

```java
package interview.exercises.router.phase2.match;

import interview.exercises.router.phase2.Packet;

import java.util.Arrays;

/**
 * Which field of a packet a criterion reads.
 * <p>
 * This is one of the two independent axes of matching: <em>what</em> to read.
 * How the value is compared is the other, and lives in {@link ValueMatcher}.
 */
public enum PacketField {

    SOURCE_IP("sourceIp") {
        @Override
        public String valueOf(Packet packet) {
            return packet.sourceIp();
        }
    },
    DESTINATION_IP("destinationIp") {
        @Override
        public String valueOf(Packet packet) {
            return packet.destinationIp();
        }
    },
    DESTINATION_PORT("destinationPort") {
        @Override
        public String valueOf(Packet packet) {
            return String.valueOf(packet.destinationPort());
        }
    },
    PROTOCOL("protocol") {
        @Override
        public String valueOf(Packet packet) {
            return packet.protocol().name();
        }
    };

    private final String specName;

    PacketField(String specName) {
        this.specName = specName;
    }

    public abstract String valueOf(Packet packet);

    public static PacketField fromSpecName(String specName) {
        return Arrays.stream(values())
                .filter(field -> field.specName.equals(specName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown packet field: " + specName));
    }
}
```

**`match/PortRangeMatcher.java`**

```java
package interview.exercises.router.phase2.match;

/**
 * Matches a port inside an inclusive range, written {@code 8000-8999}.
 */
public record PortRangeMatcher(int from, int to) implements ValueMatcher {

    @Override
    public boolean matches(String fieldValue) {
        int port = Integer.parseInt(fieldValue);
        return port >= from && port <= to;
    }
}
```

**`match/ValueMatcher.java`**

```java
package interview.exercises.router.phase2.match;

/**
 * How a single field value is compared.
 * <p>
 * Exact equality, subnet and range matching are separate implementations,
 * added without changing anything that uses this type.
 */
@FunctionalInterface
public interface ValueMatcher {

    boolean matches(String fieldValue);
}
```

**`match/ValueMatcherFactory.java`**

```java
package interview.exercises.router.phase2.match;

import java.util.List;
import java.util.Optional;

/**
 * Chooses a matcher from the syntax of a configured value.
 * <p>
 * Parsers are tried in order; the exact-match parser is last and always succeeds.
 */
public class ValueMatcherFactory {

    private static final String RANGE_SYNTAX = "\\d+-\\d+";

    private static final List<ValueMatcherParser> PARSERS = List.of(
            value -> "any".equalsIgnoreCase(value)
                    ? Optional.of(AnyMatcher.INSTANCE)
                    : Optional.empty(),
            value -> value.contains("/")
                    ? Optional.of(new CidrMatcher(value))
                    : Optional.empty(),
            value -> value.matches(RANGE_SYNTAX)
                    ? Optional.of(portRange(value))
                    : Optional.empty(),
            value -> Optional.of(new ExactMatcher(value)));

    public ValueMatcher create(String value) {
        return PARSERS.stream()
                .map(parser -> parser.parse(value))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unparsable rule value: " + value));
    }

    private static ValueMatcher portRange(String value) {
        String[] bounds = value.split("-");
        return new PortRangeMatcher(Integer.parseInt(bounds[0]), Integer.parseInt(bounds[1]));
    }
}
```

**`match/ValueMatcherParser.java`**

```java
package interview.exercises.router.phase2.match;

import java.util.Optional;

/**
 * Recognises one value syntax and builds the matcher for it.
 * <p>
 * A new syntax means a new parser, not an edit to an existing one.
 */
@FunctionalInterface
public interface ValueMatcherParser {

    Optional<ValueMatcher> parse(String value);
}
```

**`rule/Rule.java`**

```java
package interview.exercises.router.phase2.rule;

import interview.exercises.router.phase2.Action;
import interview.exercises.router.phase2.Packet;
import interview.exercises.router.phase2.match.Criterion;

/**
 * A rule knows whether it applies to a packet and what to do about it.
 * It does not know its position in the policy, or how decisions are reported.
 */
public record Rule(String id, Action action, Criterion criterion) {

    public boolean matches(Packet packet) {
        return criterion.matches(packet);
    }
}
```

**`rule/RuleFactory.java`**

```java
package interview.exercises.router.phase2.rule;

import interview.exercises.router.phase2.RuleSpec;
import interview.exercises.router.phase2.match.Criterion;
import interview.exercises.router.phase2.match.PacketField;
import interview.exercises.router.phase2.match.ValueMatcherFactory;

/**
 * The single place that knows how to turn flat spec data into the rule model.
 * <p>
 * Phase 2 changed exactly one line here: the matcher is now chosen from the value
 * syntax instead of always being an exact match.
 */
public class RuleFactory {

    private final ValueMatcherFactory matcherFactory = new ValueMatcherFactory();

    public Rule create(RuleSpec spec) {
        Criterion criterion = new Criterion(
                PacketField.fromSpecName(spec.field()),
                matcherFactory.create(spec.value()));

        return new Rule(spec.id(), spec.action(), criterion);
    }
}
```

**`rule/RuleSet.java`**

```java
package interview.exercises.router.phase2.rule;

import interview.exercises.router.phase2.Action;
import interview.exercises.router.phase2.Decision;
import interview.exercises.router.phase2.Packet;

import java.util.List;
import java.util.Optional;

/**
 * An ordered policy: the first matching rule decides, otherwise the default applies.
 * <p>
 * Ordering lives here and nowhere else, so changing how rules are ordered does not
 * touch how they match.
 */
public class RuleSet {

    private final List<Rule> rules;
    private final Action defaultAction;

    public RuleSet(List<Rule> rules, Action defaultAction) {
        this.rules = List.copyOf(rules);
        this.defaultAction = defaultAction;
    }

    public Decision decide(Packet packet) {
        Optional<Rule> firstMatch = rules.stream()
                .filter(rule -> rule.matches(packet))
                .findFirst();

        return firstMatch
                .map(rule -> new Decision(rule.id(), rule.action()))
                .orElseGet(() -> new Decision(null, defaultAction));
    }
}
```

**`json/DecisionSerializer.java`**

```java
package interview.exercises.router.phase2.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import interview.exercises.router.phase2.Decision;

import java.util.List;

/**
 * Renders decisions as JSON. Nothing in the rule model knows this class exists.
 */
public class DecisionSerializer {

    private final ObjectMapper mapper = new ObjectMapper();

    public String toJson(List<Decision> decisions) {
        ArrayNode array = mapper.createArrayNode();

        for (int packetIndex = 0; packetIndex < decisions.size(); packetIndex++) {
            Decision decision = decisions.get(packetIndex);

            ObjectNode node = array.addObject();
            node.put("packet", packetIndex);
            node.put("rule", decision.ruleId());
            node.put("action", decision.action().name());
        }

        return array.toString();
    }
}
```

**`RuleBasedFirewall.java`**

```java
package interview.exercises.router.phase2;

import interview.exercises.router.phase2.json.DecisionSerializer;
import interview.exercises.router.phase2.rule.Rule;
import interview.exercises.router.phase2.rule.RuleFactory;
import interview.exercises.router.phase2.rule.RuleSet;

import java.util.List;

public class RuleBasedFirewall implements Firewall {

    private static final Action DEFAULT_ACTION = Action.DENY;

    private final RuleFactory ruleFactory = new RuleFactory();
    private final DecisionSerializer serializer = new DecisionSerializer();

    @Override
    public String evaluate(List<RuleSpec> specs, List<Packet> packets) {
        List<Rule> rules = specs.stream()
                .map(ruleFactory::create)
                .toList();

        RuleSet ruleSet = new RuleSet(rules, DEFAULT_ACTION);

        List<Decision> decisions = packets.stream()
                .map(ruleSet::decide)
                .toList();

        return serializer.toJson(decisions);
    }
}
```

## Test

**`FirewallPhase2Test.java`**

```java
package interview.exercises.router.phase2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FirewallPhase2Test {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final List<RuleSpec> rules = List.of(
            new RuleSpec("R1", Action.DENY,  "protocol",        "UDP"),
            new RuleSpec("R2", Action.ALLOW, "destinationPort", "8000-8999"),
            new RuleSpec("R3", Action.ALLOW, "sourceIp",        "10.0.0.0/8"),
            new RuleSpec("R4", Action.DENY,  "destinationIp",   "any"));

    private Firewall firewall;

    @Before
    public void setUp() {
        firewall = new RuleBasedFirewall();
    }

    @Test
    public void rangesSubnetsAndWildcardsAllApply() throws Exception {
        List<Packet> packets = List.of(
                new Packet("10.5.0.7",       "1.1.1.1", 8080, Protocol.TCP),
                new Packet("10.5.0.7",       "1.1.1.1", 443,  Protocol.TCP),
                new Packet("172.16.0.9",     "1.1.1.1", 443,  Protocol.TCP),
                new Packet("10.5.0.7",       "1.1.1.1", 8500, Protocol.UDP),
                new Packet("9.255.255.255",  "1.1.1.1", 443,  Protocol.TCP),
                new Packet("10.255.255.255", "1.1.1.1", 7999, Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "R2", "action": "ALLOW" },
                  { "packet": 1, "rule": "R3", "action": "ALLOW" },
                  { "packet": 2, "rule": "R4", "action": "DENY"  },
                  { "packet": 3, "rule": "R1", "action": "DENY"  },
                  { "packet": 4, "rule": "R4", "action": "DENY"  },
                  { "packet": 5, "rule": "R3", "action": "ALLOW" }
                ]""",
                firewall.evaluate(rules, packets));
    }

    @Test
    public void portRangeIsInclusiveAtBothEnds() throws Exception {
        List<RuleSpec> rangeOnly = List.of(
                new RuleSpec("RANGE", Action.ALLOW, "destinationPort", "8000-8999"));

        List<Packet> aroundTheRange = List.of(
                new Packet("1.1.1.1", "2.2.2.2", 7999, Protocol.TCP),
                new Packet("1.1.1.1", "2.2.2.2", 8000, Protocol.TCP),
                new Packet("1.1.1.1", "2.2.2.2", 8999, Protocol.TCP),
                new Packet("1.1.1.1", "2.2.2.2", 9000, Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": null,    "action": "DENY"  },
                  { "packet": 1, "rule": "RANGE", "action": "ALLOW" },
                  { "packet": 2, "rule": "RANGE", "action": "ALLOW" },
                  { "packet": 3, "rule": null,    "action": "DENY"  }
                ]""",
                firewall.evaluate(rangeOnly, aroundTheRange));
    }

    @Test
    public void cidrBoundariesAreRespected() throws Exception {
        List<RuleSpec> subnetOnly = List.of(
                new RuleSpec("SUBNET", Action.ALLOW, "sourceIp", "192.168.1.0/24"));

        List<Packet> aroundTheSubnet = List.of(
                new Packet("192.168.0.255", "2.2.2.2", 1, Protocol.TCP),
                new Packet("192.168.1.0",   "2.2.2.2", 1, Protocol.TCP),
                new Packet("192.168.1.255", "2.2.2.2", 1, Protocol.TCP),
                new Packet("192.168.2.0",   "2.2.2.2", 1, Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": null,     "action": "DENY"  },
                  { "packet": 1, "rule": "SUBNET", "action": "ALLOW" },
                  { "packet": 2, "rule": "SUBNET", "action": "ALLOW" },
                  { "packet": 3, "rule": null,     "action": "DENY"  }
                ]""",
                firewall.evaluate(subnetOnly, aroundTheSubnet));
    }

    @Test
    public void slashThirtyTwoMatchesExactlyOneAddress() throws Exception {
        List<RuleSpec> hostRoute = List.of(
                new RuleSpec("HOST", Action.ALLOW, "sourceIp", "10.0.0.5/32"));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "HOST", "action": "ALLOW" },
                  { "packet": 1, "rule": null,   "action": "DENY"  }
                ]""",
                firewall.evaluate(hostRoute, twoHosts()));
    }

    @Test
    public void slashZeroMatchesEveryAddress() throws Exception {
        List<RuleSpec> defaultRoute = List.of(
                new RuleSpec("ALL", Action.ALLOW, "sourceIp", "0.0.0.0/0"));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "ALL", "action": "ALLOW" },
                  { "packet": 1, "rule": "ALL", "action": "ALLOW" }
                ]""",
                firewall.evaluate(defaultRoute, twoHosts()));
    }

    @Test
    public void anyMatchesEveryValueOfItsField() throws Exception {
        List<RuleSpec> catchAll = List.of(
                new RuleSpec("ANY", Action.ALLOW, "destinationIp", "any"));

        List<Packet> unrelated = List.of(
                new Packet("1.1.1.1", "8.8.8.8",   53, Protocol.UDP),
                new Packet("2.2.2.2", "127.0.0.1", 1,  Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "ANY", "action": "ALLOW" },
                  { "packet": 1, "rule": "ANY", "action": "ALLOW" }
                ]""",
                firewall.evaluate(catchAll, unrelated));
    }

    @Test
    public void exactMatchingStillWorks() throws Exception {
        List<RuleSpec> exact = List.of(
                new RuleSpec("EXACT", Action.ALLOW, "sourceIp", "10.0.0.5"));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "EXACT", "action": "ALLOW" },
                  { "packet": 1, "rule": null,    "action": "DENY"  }
                ]""",
                firewall.evaluate(exact, twoHosts()));
    }

    private static List<Packet> twoHosts() {
        return List.of(
                new Packet("10.0.0.5", "2.2.2.2", 1, Protocol.TCP),
                new Packet("10.0.0.6", "2.2.2.2", 1, Protocol.TCP));
    }

    private static void assertJsonEquals(String expected, String actual) throws Exception {
        assertEquals(MAPPER.readTree(expected), MAPPER.readTree(actual));
    }
}
```

---

# Phase 3

## Handout

**Time:** ~20 minutes.

Two changes.

### A rule can combine criteria

`ALL` requires every child to match. `ANY` requires at least one. These nest:

```
R20: priority 20, ALLOW
     ALL[ sourceIp 10.0.0.0/8,
          ANY[ destinationPort 443, destinationPort 8080 ],
          protocol TCP ]
```

### Order is no longer list order

Every rule has an explicit **priority**. Rules are evaluated from the **lowest priority number upward**. The order in which rules arrive in the list is meaningless.

### Updated spec types

```java
public record RuleSpec(String id, int priority, Action action, CriterionSpec criterion) {}

public record CriterionSpec(String op,                       // "MATCH" | "ALL" | "ANY"
                            String field,                    // when op == MATCH
                            String value,                    // when op == MATCH
                            List<CriterionSpec> children) {}  // when op == ALL | ANY
```

Everything else is unchanged: same output format, same default `DENY`.

`FirewallPhase3Test` is the spec.

---

**When you are done, tell us — there is a Phase 4. It has no code.**

## Provided scaffolding

Unchanged from Phase 2 except where noted below.

**`Action.java`**

```java
package interview.exercises.router.phase3;

public enum Action {
    ALLOW,
    DENY
}
```

**`Protocol.java`**

```java
package interview.exercises.router.phase3;

public enum Protocol {
    TCP,
    UDP
}
```

**`Packet.java`**

```java
package interview.exercises.router.phase3;

public record Packet(String sourceIp,
                     String destinationIp,
                     int destinationPort,
                     Protocol protocol) {
}
```

**`RuleSpec.java`**

```java
package interview.exercises.router.phase3;

/**
 * One rule as flat configuration data, exactly as it arrives from outside.
 * <p>
 * Rules are evaluated from the lowest priority number upward; the order in which
 * they arrive is meaningless.
 */
public record RuleSpec(String id,
                       int priority,
                       Action action,
                       CriterionSpec criterion) {
}
```

**`CriterionSpec.java`**

```java
package interview.exercises.router.phase3;

import java.util.List;

/**
 * A criterion as flat configuration data. Either a leaf comparison
 * ({@code op == "MATCH"}) or a group of nested criteria ({@code "ALL"} / {@code "ANY"}).
 */
public record CriterionSpec(String op,
                            String field,
                            String value,
                            List<CriterionSpec> children) {
}
```

**`Decision.java`**

```java
package interview.exercises.router.phase3;

/**
 * The outcome for a single packet.
 *
 * @param ruleId the rule that decided, or {@code null} when no rule matched
 */
public record Decision(String ruleId, Action action) {
}
```

**`Firewall.java`**

```java
package interview.exercises.router.phase3;

import java.util.List;

public interface Firewall {

    /**
     * Evaluates every packet against the rules and returns the decisions as a JSON array.
     */
    String evaluate(List<RuleSpec> rules, List<Packet> packets);
}
```

## Reference solution

`Criterion` becomes an interface. `FieldCriterion` is the old leaf; `AllOf` and `AnyOf` are groups. A single criterion and a group of criteria are the same type to everything that evaluates them — that is the Composite pattern, and it is what this phase tests.

`Rule` gains a priority. `RuleSet` sorts on construction, and **`decide()` does not change at all** — the payoff for having kept ordering in one place. `CriterionFactory` recurses; the switch there is legitimate, since the factory is the one place allowed to know every concrete type.

> **Deliberately unspecified — do not volunteer:** what happens when two rules share a priority; how deep nesting may go; whether an empty `ALL`/`ANY` is legal.

### Grading

- Does the evaluating code ever ask *"are you a group?"* If yes, the abstraction leaked
- Did swapping list-order for priority-order touch matching? It shouldn't. If it did, Phase 1 was over-coupled
- Java's `sorted()` is stable, so equal priorities keep input order. Ask whether they knew that or got lucky

**Questions to ask**

- Two rules, same priority — what did you do, and why?
- `NOT` is the obvious next operator. Where does it go?
- Could a rule contain itself? Should you care?
- `AllOf` with no children matches everything; `AnyOf` with no children matches nothing. Is that right?
- How would you print a rule back out in readable form?

**Test note.** `aSingleMatchCriterionStillWorksOnItsOwn` is the quiet one — a bare criterion has to work in the same position as a group. It fails for anyone who made composites a special case instead of finding the pattern.

**`match/AllOf.java`**

```java
package interview.exercises.router.phase3.match;

import interview.exercises.router.phase3.Packet;

import java.util.List;

/**
 * Matches when every child matches. An empty group matches everything.
 */
public record AllOf(List<Criterion> children) implements Criterion {

    public AllOf {
        children = List.copyOf(children);
    }

    @Override
    public boolean matches(Packet packet) {
        return children.stream().allMatch(child -> child.matches(packet));
    }
}
```

**`match/AnyMatcher.java`**

```java
package interview.exercises.router.phase3.match;

/**
 * Matches every value of its field. Stateless, so a single instance is enough.
 */
public final class AnyMatcher implements ValueMatcher {

    public static final AnyMatcher INSTANCE = new AnyMatcher();

    private AnyMatcher() {
    }

    @Override
    public boolean matches(String fieldValue) {
        return true;
    }
}
```

**`match/AnyOf.java`**

```java
package interview.exercises.router.phase3.match;

import interview.exercises.router.phase3.Packet;

import java.util.List;

/**
 * Matches when at least one child matches. An empty group matches nothing.
 */
public record AnyOf(List<Criterion> children) implements Criterion {

    public AnyOf {
        children = List.copyOf(children);
    }

    @Override
    public boolean matches(Packet packet) {
        return children.stream().anyMatch(child -> child.matches(packet));
    }
}
```

**`match/CidrMatcher.java`**

```java
package interview.exercises.router.phase3.match;

/**
 * Matches an IPv4 address inside a subnet, written {@code 10.0.0.0/8}.
 * <p>
 * The address and prefix are collapsed to a network and a mask once, at construction,
 * so matching is two integer operations.
 */
public final class CidrMatcher implements ValueMatcher {

    private final int network;
    private final int mask;

    public CidrMatcher(String cidr) {
        String[] parts = cidr.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Not a CIDR block: " + cidr);
        }
        this.mask = maskOf(Integer.parseInt(parts[1]));
        this.network = toInt(parts[0]) & mask;
    }

    @Override
    public boolean matches(String fieldValue) {
        return (toInt(fieldValue) & mask) == network;
    }

    private static int maskOf(int prefixLength) {
        if (prefixLength < 0 || prefixLength > 32) {
            throw new IllegalArgumentException("Prefix length out of range: " + prefixLength);
        }
        return prefixLength == 0 ? 0 : -1 << (32 - prefixLength);
    }

    private static int toInt(String ipv4) {
        String[] octets = ipv4.split("\\.");
        if (octets.length != 4) {
            throw new IllegalArgumentException("Not an IPv4 address: " + ipv4);
        }
        int value = 0;
        for (String octet : octets) {
            value = (value << 8) | Integer.parseInt(octet);
        }
        return value;
    }
}
```

**`match/Criterion.java`**

```java
package interview.exercises.router.phase3.match;

import interview.exercises.router.phase3.Packet;

/**
 * Anything that can decide whether it applies to a packet.
 * <p>
 * A single comparison and a group of comparisons are both criteria, so nothing
 * that evaluates a criterion needs to know which it is holding.
 */
public interface Criterion {

    boolean matches(Packet packet);
}
```

**`match/CriterionFactory.java`**

```java
package interview.exercises.router.phase3.match;

import interview.exercises.router.phase3.CriterionSpec;

import java.util.List;

/**
 * Builds the criterion tree from configuration.
 * <p>
 * This is the one place allowed to know every concrete criterion type; everywhere
 * else works through {@link Criterion}.
 */
public class CriterionFactory {

    private static final String MATCH = "MATCH";
    private static final String ALL = "ALL";
    private static final String ANY = "ANY";

    private final ValueMatcherFactory matcherFactory = new ValueMatcherFactory();

    public Criterion create(CriterionSpec spec) {
        return switch (spec.op()) {
            case MATCH -> new FieldCriterion(
                    PacketField.fromSpecName(spec.field()),
                    matcherFactory.create(spec.value()));
            case ALL -> new AllOf(children(spec));
            case ANY -> new AnyOf(children(spec));
            default -> throw new IllegalArgumentException("Unknown criterion op: " + spec.op());
        };
    }

    private List<Criterion> children(CriterionSpec spec) {
        return spec.children().stream()
                .map(this::create)
                .toList();
    }
}
```

**`match/ExactMatcher.java`**

```java
package interview.exercises.router.phase3.match;

public record ExactMatcher(String expected) implements ValueMatcher {

    @Override
    public boolean matches(String fieldValue) {
        return expected.equals(fieldValue);
    }
}
```

**`match/FieldCriterion.java`**

```java
package interview.exercises.router.phase3.match;

import interview.exercises.router.phase3.Packet;

/**
 * Reads one field off a packet and compares it.
 * <p>
 * Field and comparison vary independently, so adding a new comparison does not
 * multiply with the number of fields.
 */
public record FieldCriterion(PacketField field, ValueMatcher matcher) implements Criterion {

    @Override
    public boolean matches(Packet packet) {
        return matcher.matches(field.valueOf(packet));
    }
}
```

**`match/PacketField.java`**

```java
package interview.exercises.router.phase3.match;

import interview.exercises.router.phase3.Packet;

import java.util.Arrays;

/**
 * Which field of a packet a criterion reads.
 * <p>
 * This is one of the two independent axes of matching: <em>what</em> to read.
 * How the value is compared is the other, and lives in {@link ValueMatcher}.
 */
public enum PacketField {

    SOURCE_IP("sourceIp") {
        @Override
        public String valueOf(Packet packet) {
            return packet.sourceIp();
        }
    },
    DESTINATION_IP("destinationIp") {
        @Override
        public String valueOf(Packet packet) {
            return packet.destinationIp();
        }
    },
    DESTINATION_PORT("destinationPort") {
        @Override
        public String valueOf(Packet packet) {
            return String.valueOf(packet.destinationPort());
        }
    },
    PROTOCOL("protocol") {
        @Override
        public String valueOf(Packet packet) {
            return packet.protocol().name();
        }
    };

    private final String specName;

    PacketField(String specName) {
        this.specName = specName;
    }

    public abstract String valueOf(Packet packet);

    public static PacketField fromSpecName(String specName) {
        return Arrays.stream(values())
                .filter(field -> field.specName.equals(specName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown packet field: " + specName));
    }
}
```

**`match/PortRangeMatcher.java`**

```java
package interview.exercises.router.phase3.match;

/**
 * Matches a port inside an inclusive range, written {@code 8000-8999}.
 */
public record PortRangeMatcher(int from, int to) implements ValueMatcher {

    @Override
    public boolean matches(String fieldValue) {
        int port = Integer.parseInt(fieldValue);
        return port >= from && port <= to;
    }
}
```

**`match/ValueMatcher.java`**

```java
package interview.exercises.router.phase3.match;

/**
 * How a single field value is compared.
 * <p>
 * Exact equality, subnet and range matching are separate implementations,
 * added without changing anything that uses this type.
 */
@FunctionalInterface
public interface ValueMatcher {

    boolean matches(String fieldValue);
}
```

**`match/ValueMatcherFactory.java`**

```java
package interview.exercises.router.phase3.match;

import java.util.List;
import java.util.Optional;

/**
 * Chooses a matcher from the syntax of a configured value.
 * <p>
 * Parsers are tried in order; the exact-match parser is last and always succeeds.
 */
public class ValueMatcherFactory {

    private static final String RANGE_SYNTAX = "\\d+-\\d+";

    private static final List<ValueMatcherParser> PARSERS = List.of(
            value -> "any".equalsIgnoreCase(value)
                    ? Optional.of(AnyMatcher.INSTANCE)
                    : Optional.empty(),
            value -> value.contains("/")
                    ? Optional.of(new CidrMatcher(value))
                    : Optional.empty(),
            value -> value.matches(RANGE_SYNTAX)
                    ? Optional.of(portRange(value))
                    : Optional.empty(),
            value -> Optional.of(new ExactMatcher(value)));

    public ValueMatcher create(String value) {
        return PARSERS.stream()
                .map(parser -> parser.parse(value))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unparsable rule value: " + value));
    }

    private static ValueMatcher portRange(String value) {
        String[] bounds = value.split("-");
        return new PortRangeMatcher(Integer.parseInt(bounds[0]), Integer.parseInt(bounds[1]));
    }
}
```

**`match/ValueMatcherParser.java`**

```java
package interview.exercises.router.phase3.match;

import java.util.Optional;

/**
 * Recognises one value syntax and builds the matcher for it.
 * <p>
 * A new syntax means a new parser, not an edit to an existing one.
 */
@FunctionalInterface
public interface ValueMatcherParser {

    Optional<ValueMatcher> parse(String value);
}
```

**`rule/Rule.java`**

```java
package interview.exercises.router.phase3.rule;

import interview.exercises.router.phase3.Action;
import interview.exercises.router.phase3.Packet;
import interview.exercises.router.phase3.match.Criterion;

/**
 * A rule knows whether it applies to a packet and what to do about it.
 * It does not know how decisions are reported, or how it compares to other rules.
 */
public record Rule(String id, int priority, Action action, Criterion criterion) {

    public boolean matches(Packet packet) {
        return criterion.matches(packet);
    }
}
```

**`rule/RuleFactory.java`**

```java
package interview.exercises.router.phase3.rule;

import interview.exercises.router.phase3.RuleSpec;
import interview.exercises.router.phase3.match.CriterionFactory;

/**
 * The single place that knows how to turn flat spec data into the rule model.
 */
public class RuleFactory {

    private final CriterionFactory criterionFactory = new CriterionFactory();

    public Rule create(RuleSpec spec) {
        return new Rule(spec.id(),
                spec.priority(),
                spec.action(),
                criterionFactory.create(spec.criterion()));
    }
}
```

**`rule/RuleSet.java`**

```java
package interview.exercises.router.phase3.rule;

import interview.exercises.router.phase3.Action;
import interview.exercises.router.phase3.Decision;
import interview.exercises.router.phase3.Packet;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * A prioritised policy: the lowest-priority-number matching rule decides,
 * otherwise the default applies.
 * <p>
 * Ordering lives here and nowhere else. Phase 3 replaced list order with priority
 * order by changing the constructor; {@link #decide} did not change at all.
 */
public class RuleSet {

    private final List<Rule> rules;
    private final Action defaultAction;

    public RuleSet(List<Rule> rules, Action defaultAction) {
        this.rules = rules.stream()
                .sorted(Comparator.comparingInt(Rule::priority))
                .toList();
        this.defaultAction = defaultAction;
    }

    public Decision decide(Packet packet) {
        Optional<Rule> firstMatch = rules.stream()
                .filter(rule -> rule.matches(packet))
                .findFirst();

        return firstMatch
                .map(rule -> new Decision(rule.id(), rule.action()))
                .orElseGet(() -> new Decision(null, defaultAction));
    }
}
```

**`json/DecisionSerializer.java`**

```java
package interview.exercises.router.phase3.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import interview.exercises.router.phase3.Decision;

import java.util.List;

/**
 * Renders decisions as JSON. Nothing in the rule model knows this class exists.
 */
public class DecisionSerializer {

    private final ObjectMapper mapper = new ObjectMapper();

    public String toJson(List<Decision> decisions) {
        ArrayNode array = mapper.createArrayNode();

        for (int packetIndex = 0; packetIndex < decisions.size(); packetIndex++) {
            Decision decision = decisions.get(packetIndex);

            ObjectNode node = array.addObject();
            node.put("packet", packetIndex);
            node.put("rule", decision.ruleId());
            node.put("action", decision.action().name());
        }

        return array.toString();
    }
}
```

**`RuleBasedFirewall.java`**

```java
package interview.exercises.router.phase3;

import interview.exercises.router.phase3.json.DecisionSerializer;
import interview.exercises.router.phase3.rule.Rule;
import interview.exercises.router.phase3.rule.RuleFactory;
import interview.exercises.router.phase3.rule.RuleSet;

import java.util.List;

public class RuleBasedFirewall implements Firewall {

    private static final Action DEFAULT_ACTION = Action.DENY;

    private final RuleFactory ruleFactory = new RuleFactory();
    private final DecisionSerializer serializer = new DecisionSerializer();

    @Override
    public String evaluate(List<RuleSpec> specs, List<Packet> packets) {
        List<Rule> rules = specs.stream()
                .map(ruleFactory::create)
                .toList();

        RuleSet ruleSet = new RuleSet(rules, DEFAULT_ACTION);

        List<Decision> decisions = packets.stream()
                .map(ruleSet::decide)
                .toList();

        return serializer.toJson(decisions);
    }
}
```

## Test

**`FirewallPhase3Test.java`**

```java
package interview.exercises.router.phase3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FirewallPhase3Test {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /** ALLOW TCP from 10/8 to either 443 or 8080. */
    private static final RuleSpec ALLOW_WEB_FROM_INTERNAL = new RuleSpec("R20", 20, Action.ALLOW,
            all(match("sourceIp", "10.0.0.0/8"),
                any(match("destinationPort", "443"),
                    match("destinationPort", "8080")),
                match("protocol", "TCP")));

    private static final RuleSpec DENY_UDP = new RuleSpec("R10", 10, Action.DENY,
            match("protocol", "UDP"));

    private static final RuleSpec DENY_EVERYTHING = new RuleSpec("R30", 30, Action.DENY,
            match("destinationIp", "any"));

    private static final String EXPECTED = """
            [
              { "packet": 0, "rule": "R20", "action": "ALLOW" },
              { "packet": 1, "rule": "R10", "action": "DENY"  },
              { "packet": 2, "rule": "R30", "action": "DENY"  },
              { "packet": 3, "rule": "R30", "action": "DENY"  },
              { "packet": 4, "rule": "R20", "action": "ALLOW" }
            ]""";

    /** Deliberately not in priority order. */
    private final List<RuleSpec> rules = List.of(
            ALLOW_WEB_FROM_INTERNAL, DENY_EVERYTHING, DENY_UDP);

    private final List<Packet> packets = List.of(
            new Packet("10.0.0.5",   "1.1.1.1", 443,  Protocol.TCP),
            new Packet("10.0.0.5",   "1.1.1.1", 443,  Protocol.UDP),
            new Packet("10.0.0.5",   "1.1.1.1", 9090, Protocol.TCP),
            new Packet("172.16.0.1", "1.1.1.1", 8080, Protocol.TCP),
            new Packet("10.0.0.5",   "1.1.1.1", 8080, Protocol.TCP));

    private Firewall firewall;

    @Before
    public void setUp() {
        firewall = new RuleBasedFirewall();
    }

    @Test
    public void compositeRulesAreEvaluatedInPriorityOrder() throws Exception {
        assertJsonEquals(EXPECTED, firewall.evaluate(rules, packets));
    }

    @Test
    public void inputListOrderIsIrrelevant() throws Exception {
        List<RuleSpec> shuffled = List.of(DENY_UDP, ALLOW_WEB_FROM_INTERNAL, DENY_EVERYTHING);
        List<RuleSpec> reversed = List.of(DENY_EVERYTHING, DENY_UDP, ALLOW_WEB_FROM_INTERNAL);

        assertJsonEquals(EXPECTED, firewall.evaluate(shuffled, packets));
        assertJsonEquals(EXPECTED, firewall.evaluate(reversed, packets));
    }

    @Test
    public void allRequiresEveryChildToMatch() throws Exception {
        List<RuleSpec> onlyTheCompositeRule = List.of(ALLOW_WEB_FROM_INTERNAL);

        List<Packet> oneGoodThreeBad = List.of(
                new Packet("10.0.0.5",   "1.1.1.1", 443, Protocol.TCP),   // all three match
                new Packet("172.16.0.1", "1.1.1.1", 443, Protocol.TCP),   // wrong subnet
                new Packet("10.0.0.5",   "1.1.1.1", 22,  Protocol.TCP),   // wrong port
                new Packet("10.0.0.5",   "1.1.1.1", 443, Protocol.UDP));  // wrong protocol

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "R20", "action": "ALLOW" },
                  { "packet": 1, "rule": null,  "action": "DENY"  },
                  { "packet": 2, "rule": null,  "action": "DENY"  },
                  { "packet": 3, "rule": null,  "action": "DENY"  }
                ]""",
                firewall.evaluate(onlyTheCompositeRule, oneGoodThreeBad));
    }

    @Test
    public void anyRequiresOneChildToMatch() throws Exception {
        List<RuleSpec> eitherPort = List.of(new RuleSpec("EITHER", 1, Action.ALLOW,
                any(match("destinationPort", "443"),
                    match("destinationPort", "8080"))));

        List<Packet> threePorts = List.of(
                new Packet("1.1.1.1", "2.2.2.2", 443,  Protocol.TCP),
                new Packet("1.1.1.1", "2.2.2.2", 8080, Protocol.TCP),
                new Packet("1.1.1.1", "2.2.2.2", 9090, Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "EITHER", "action": "ALLOW" },
                  { "packet": 1, "rule": "EITHER", "action": "ALLOW" },
                  { "packet": 2, "rule": null,     "action": "DENY"  }
                ]""",
                firewall.evaluate(eitherPort, threePorts));
    }

    @Test
    public void criteriaNestMoreThanTwoDeep() throws Exception {
        List<RuleSpec> deeplyNested = List.of(new RuleSpec("DEEP", 1, Action.ALLOW,
                all(match("protocol", "TCP"),
                    any(all(match("sourceIp", "10.0.0.0/8"),
                            match("destinationPort", "443")),
                        all(match("sourceIp", "192.168.0.0/16"),
                            match("destinationPort", "8080"))))));

        List<Packet> packetsForBranches = List.of(
                new Packet("10.0.0.5",    "2.2.2.2", 443,  Protocol.TCP),  // first branch
                new Packet("192.168.1.7", "2.2.2.2", 8080, Protocol.TCP),  // second branch
                new Packet("10.0.0.5",    "2.2.2.2", 8080, Protocol.TCP),  // crossed branches
                new Packet("10.0.0.5",    "2.2.2.2", 443,  Protocol.UDP)); // outer ALL fails

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "DEEP", "action": "ALLOW" },
                  { "packet": 1, "rule": "DEEP", "action": "ALLOW" },
                  { "packet": 2, "rule": null,   "action": "DENY"  },
                  { "packet": 3, "rule": null,   "action": "DENY"  }
                ]""",
                firewall.evaluate(deeplyNested, packetsForBranches));
    }

    @Test
    public void aSingleMatchCriterionStillWorksOnItsOwn() throws Exception {
        List<RuleSpec> singleCriterion = List.of(
                new RuleSpec("SOLO", 1, Action.ALLOW, match("protocol", "UDP")));

        List<Packet> twoProtocols = List.of(
                new Packet("1.1.1.1", "2.2.2.2", 53, Protocol.UDP),
                new Packet("1.1.1.1", "2.2.2.2", 53, Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "SOLO", "action": "ALLOW" },
                  { "packet": 1, "rule": null,   "action": "DENY"  }
                ]""",
                firewall.evaluate(singleCriterion, twoProtocols));
    }

    private static CriterionSpec match(String field, String value) {
        return new CriterionSpec("MATCH", field, value, List.of());
    }

    private static CriterionSpec all(CriterionSpec... children) {
        return new CriterionSpec("ALL", null, null, List.of(children));
    }

    private static CriterionSpec any(CriterionSpec... children) {
        return new CriterionSpec("ANY", null, null, List.of(children));
    }

    private static void assertJsonEquals(String expected, String actual) throws Exception {
        assertEquals(MAPPER.readTree(expected), MAPPER.readTree(actual));
    }
}
```

---

# Phase 4

No code. Draw it and talk.

## Handout

This matcher now sits in the data path.

- **500,000 packets per second**, per node
- Budget is **under 50 microseconds** per packet
- **50,000 rules**
- The rule set is **edited by admins at runtime**. A change must take effect within seconds, without dropping or misrouting a single packet
- Every rule needs a **hit counter**, readable from outside

Cover: how you avoid walking 50,000 rules for every packet; what data structure gets you there and what it costs to build; how a rule change lands while packets are in flight; whether matching takes a lock, and why not; where hit counters live when every core is writing to them.

Name specific technologies, but be ready to say **why that one** and what you give up by choosing it. "We'd put an index in front" is a starting point, not an answer.

Don't design everything. If you're unsure whether something matters here, say so and move on.

## Grading

| Topic | Strong | Weak |
|---|---|---|
| Lookup | Indexes rules by the most selective field — hash for exact, interval tree for ranges, trie for CIDR — then linear-scans only the survivors | "Loop over 50,000 rules, it's fine" |
| Rule updates | Builds a new immutable rule set, swaps an `AtomicReference`; readers never block, in-flight packets finish on the old set | Mutates a shared list; `synchronized` on the match path |
| Concurrency | Knows the match path must be lock-free and allocation-free | "Use a `ConcurrentHashMap`" as a catch-all |
| Counters | Per-thread or striped counters summed on read; knows one shared `AtomicLong` per rule is a contention point at 500k/s | One shared counter incremented per hit |
| Caching | Flow cache keyed on the 5-tuple, since packets arrive in flows; knows it must be invalidated on rule change | Caches with no invalidation story |
| Honesty | Names the part they'd measure before optimising | Optimises everything with equal confidence |

**The trap is the runtime rule change.** It's the only requirement that makes concurrency unavoidable, and *immutable snapshot plus atomic swap* is the answer you're listening for. A candidate who reaches for locks here will reach for locks in production.

**The second trap is the flow cache.** Almost everyone proposes caching; far fewer volunteer that a rule edit must invalidate it. A stale allow after an admin adds a deny is a security bug, not a performance bug. Ask if they don't raise it.

Score this phase separately from 1–3. A strong coder can be weak here and still be the right hire, depending on the role.

---

# Calibration

| Signal | Read |
|---|---|
| Phase 1 clean, Phase 2 costs one or two classes, Phase 3 finds Composite unprompted | Strong. Move to Phase 4 and push hard |
| Phase 1 clean, Phase 2 multiplies classes, sees why when asked | Solid and coachable — right instinct, abstraction not yet found |
| Phase 1 works, later phases need rewrites, and they say so plainly | Often good. Naming your own design debt is a senior trait |
| Phase 1 works, later phases patched with flags and switches, no discomfort | The finding. Working code, no design sense, no self-correction |
| Phase 1 doesn't work in 30 minutes | Separate Java fluency from problem comprehension before concluding anything |

A candidate who asks about the deliberate gaps — CIDR against a port field, priority ties, empty `ALL` — is showing you how they'd handle a real spec. Note who asks, and when.

**One fairness note.** A candidate with networking background has an edge here on CIDR and 5-tuples. If you're comparing candidates across different exercises, account for it.

## Files

This document is generated from the repo, so it will drift if the code changes. Sources:

```
interview/exercises/router/
    README.md               run-the-session summary
    INTERVIEWER-GUIDE.md    grading only, no code
    handouts/               candidate-facing, one file per phase
    phase1/ phase2/ phase3/ reference solutions

src/test/java/interview/exercises/router/phase{1,2,3}/
```

