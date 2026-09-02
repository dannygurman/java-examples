# Router Rule Matcher — Interviewer Guide

> **Interviewer only. Do not share with candidates.**
> Candidate-facing text lives in `CANDIDATE-GUIDE.md` and `handouts/phase1.md` … `phase4.md`.

## What this exercise measures

Basic OO design under changing requirements. Every phase changes the spec in a way a good Phase 1 design absorbs and a poor one cannot. The signal is not whether the code works — it is **how much has to move** each time the requirements do.

Hand out one phase at a time. Never show a later phase early; the whole point is that the change is unforeseen.

## Running it

| Phase | Time | Form | What it tests |
|---|---|---|---|
| 1 | 30 min | Code | Modelling, polymorphism, separation of concerns |
| 2 | 20 min | Code | Open/closed — does a new comparison cost one class or many? |
| 3 | 20 min | Code | Composite pattern, and swapping the ordering strategy |
| 4 | 20 min | Whiteboard | Concurrency and scale. Score separately |

Give the candidate the phase handout and that phase's test. The tests are the spec.

## Reference implementation

`phase1/`, `phase2/`, `phase3/` are self-contained packages. The duplication is deliberate: each phase compiles on its own, and **diffing one against the previous one shows exactly what the requirement change cost**. Use the diff in debriefs.

Tests are in `src/test/java/interview/exercises/router/phase{1,2,3}/`.

### The central design decision

Matching has **two independent axes**: *which* field to read, and *how* to compare it.

```java
public record Criterion(PacketField field, ValueMatcher matcher) {
    public boolean matches(Packet packet) {
        return matcher.matches(field.valueOf(packet));
    }
}
```

Everything downstream follows from whether the candidate finds this. Fuse the axes and Phase 2 costs 4 fields × 3 comparisons of classes. Separate them and it costs two classes.

### What each phase actually changed

| Phase | Files added | Files modified | Untouched |
|---|---|---|---|
| 1 → 2 | `CidrMatcher`, `PortRangeMatcher`, `AnyMatcher`, `ValueMatcherParser`, `ValueMatcherFactory` | `RuleFactory` (one line) | `Rule`, `RuleSet`, `Criterion`, `PacketField`, serializer, `Firewall` |
| 2 → 3 | `FieldCriterion`, `AllOf`, `AnyOf`, `CriterionFactory`, `CriterionSpec` | `Criterion` (record → interface), `Rule` (+priority), `RuleSet` (constructor sorts), `RuleFactory` | **`RuleSet.decide()`**, all matchers, serializer, `Firewall`, `RuleBasedFirewall` |

`RuleSet.decide()` surviving both changes untouched is the thing to point at. Ordering lived in one place, so replacing list order with priority order was a constructor change.

---

## Phase 1 — grading

**Names you hope they invent**

| Concept | Good names | What its absence tells you |
|---|---|---|
| Rule that answers yes/no | `Rule` with `boolean matches(Packet)` | Matching done in the matcher via getters — polymorphism not found |
| The comparison itself | `Criterion`, `Condition`, `Predicate<Packet>` | Fused into `Rule` — Phase 3 composites will hurt |
| Reading a field | `PacketField`, `FieldSelector`, `FieldExtractor` | Usually missing; its absence is why field × value becomes an N×M switch |
| Spec → model | `RuleFactory` | `new` scattered — Phase 2 touches many files |
| The ordered policy | `RuleSet`, `Policy`, `RuleChain` | A bare `List<Rule>` leaves ordering logic homeless |
| One outcome | `Decision`, `Verdict` | Returning a bare `Action` loses which rule fired |
| JSON | `DecisionSerializer` | `toString()` returning JSON — serialization leaked into the model |

**Questions**

- What's the complexity per packet? Per thousand packets?
- What would change if the rule list had 50,000 entries?
- Is a rule that matches nothing distinguishable from a rule that denies?

**Test notes.** `reversingRuleOrderReversesTheDecision` catches anyone who sorts or dedupes rules instead of respecting order — same packet, same two rules, opposite outcome.

---

## Phase 2 — grading

`RuleSpec` does not change. **Say nothing about this.** If their Phase 1 design was right they will notice; if it wasn't, watch them try to add a type field.

Look for new **classes**, not new **branches**. The failure mode is `Rule` growing a `matchType` enum and a switch inside `matches()`.

The sharper tell is whether they conflated *which field* with *how it's compared*. Candidates who fused the axes now write `SourceIpExactRule`, `SourceIpCidrRule`, `PortExactRule`, `PortRangeRule`, `ProtocolExactRule`. If that happens, ask directly: *"if we added a fifth field, how many classes would you add?"* Let them do the multiplication out loud — it lands better than being told.

**Questions**

- What happens if a CIDR value is configured against the port field?
- Is `any` a matcher, or the absence of a matcher? Does it matter?
- `10.0.0.0/8`, `10.0.0.5/32` and `10.0.0.5` — three types or one?

**Test notes.** `portRangeIsInclusiveAtBothEnds` and `cidrBoundariesAreRespected` sit exactly on the boundaries (`7999`/`8000`, `8999`/`9000`, `192.168.0.255`/`192.168.1.0`) because hand-rolled CIDR math is where off-by-ones live. `slashZeroMatchesEveryAddress` catches the `-1 << 32` shift bug, which in Java silently returns `-1` instead of `0`.

---

## Phase 3 — grading

> **Deliberately unspecified — do not volunteer:** what happens when two rules share a priority; how deep nesting may go; whether an empty `ALL`/`ANY` is legal.

This is the Composite pattern arriving on schedule. A single criterion and a group of criteria must be the same type to everything that evaluates them.

- Does the evaluating code ever ask *"are you a group?"* If yes, the abstraction leaked
- Did swapping list-order for priority-order touch matching? It shouldn't. If it did, Phase 1 was over-coupled
- Java's `sorted()` is stable, so equal priorities keep input order. Ask whether they knew that or got lucky

**Questions**

- Two rules, same priority — what did you do, and why?
- `NOT` is the obvious next operator. Where does it go?
- Could a rule contain itself? Should you care?
- `AllOf` with no children matches everything; `AnyOf` with no children matches nothing. Is that right?
- How would you print a rule back out in readable form?

**Test notes.** `aSingleMatchCriterionStillWorksOnItsOwn` is the quiet one — a bare criterion has to work in the same position as a group. It fails for anyone who made composites a special case instead of finding the pattern.

---

## Phase 4 — grading

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

---

## Calibration

| Signal | Read |
|---|---|
| Phase 1 clean, Phase 2 costs one or two classes, Phase 3 finds Composite unprompted | Strong. Move to Phase 4 and push hard |
| Phase 1 clean, Phase 2 multiplies classes, sees why when asked | Solid and coachable — right instinct, abstraction not yet found |
| Phase 1 works, later phases need rewrites, and they say so plainly | Often good. Naming your own design debt is a senior trait |
| Phase 1 works, later phases patched with flags and switches, no discomfort | The finding. Working code, no design sense, no self-correction |
| Phase 1 doesn't work in 30 minutes | Separate Java fluency from problem comprehension before concluding anything |

A candidate who asks about the deliberate gaps — CIDR against a port field, priority ties, empty `ALL` — is showing you how they'd handle a real spec. Note who asks, and when.

**One fairness note.** A candidate with networking background has an edge here on CIDR and 5-tuples. If you're comparing candidates across different exercises, account for it.
