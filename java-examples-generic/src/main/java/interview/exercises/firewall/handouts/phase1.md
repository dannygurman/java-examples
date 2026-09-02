# Router Rule Matcher — Phase 1

**Time:** ~30 minutes. Java. Jackson and JUnit are on the classpath.

## The problem

A **packet** has a source IP, a destination IP, a destination port, and a protocol (`TCP` or `UDP`).

A **rule** has an id, an action (`ALLOW` or `DENY`), and matches on **exactly one** field: source IP, destination IP, destination port, or protocol.

Rules are an **ordered list**. For each packet, the **first matching rule** decides. If no rule matches, the action is `DENY`.

## Example

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

## Provided

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

## What to implement

A class `RuleBasedFirewall implements Firewall` with a no-arg constructor. Everything else is yours to design.

`FirewallPhase1Test` is the spec. Do not modify it.

## What we are looking for

This is a small problem, so the answer is not "does it work" — it is **how you structure it**. Assume this code will keep changing: new fields, new ways of comparing them, different ordering rules.

We will look at how you model rules and matching, where ordering and the default action live, where JSON serialization lives, and whether the code reads without comments.

---

**When you are done, tell us — there is a Phase 2.**
