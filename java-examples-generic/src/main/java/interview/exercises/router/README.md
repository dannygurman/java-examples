# Router Rule Matcher — Interview Exercise

An OO design exercise in four phases. Phases 1–3 are code driven by tests; Phase 4 is a whiteboard discussion.

## Contents

```
router/
  README.md               this page — run it from here
  INTERVIEWER-GUIDE.md    grading, answers, deliberate gaps, calibration
  CANDIDATE-GUIDE.md      send to the candidate before the session
  handouts/               give these to the candidate, one at a time
      phase1.md  phase2.md  phase3.md  phase4.md
  phase1/  phase2/  phase3/    reference solutions, one per phase
```

Tests: `src/test/java/interview/exercises/router/phase{1,2,3}/`

## The session

| Phase | Time | Form | Change introduced |
|---|---|---|---|
| 1 | 30 min | Code | Ordered rules, one field each, first match wins |
| 2 | 20 min | Code | CIDR subnets, port ranges, `any` |
| 3 | 20 min | Code | Nested `ALL`/`ANY` criteria; priority replaces list order |
| 4 | 20 min | Whiteboard | 500k packets/sec, 50k rules, live rule edits |

**Total: 90 minutes.** Stop wherever time runs out — Phase 1 alone decides many candidates.

## By seniority

| Level | Run | Note |
|---|---|---|
| Junior | Phases 1–2 | Phase 3 is a reasonable stretch if they're fast. Skip Phase 4 — it produces recitation, not signal |
| Mid | Phases 1–3 | Phase 4 only if there's time left |
| Senior / Staff | Phases 1–3, then 4 | Phase 4 carries real weight. Push on the runtime rule change |

## How to run it

0. Send `CANDIDATE-GUIDE.md` ahead of the session. It covers setup, ground rules and what we grade, and gives away no part of the problem.
1. Give them `handouts/phase1.md` and `FirewallPhase1Test`. **Nothing else.**
2. When a phase works, hand out the next one. Never earlier — the change has to be unforeseen for the exercise to measure anything.
3. Ask that phase's questions from the interviewer guide before moving on. The conversation is worth more than the code.
4. Phase 4 is whiteboard only. Score it separately from 1–3.

The candidate provides `RuleBasedFirewall implements Firewall` with a no-arg constructor. Everything else is theirs to design — in particular there is no `Rule` type and no `matches(Packet)` in the provided scaffolding, because handing those over would give away the central insight.

## What it measures

Not whether the code works. **How much has to move when the requirements change.**

Phase 2 is the first real reading: a candidate who separated *which field to read* from *how to compare it* adds two classes. A candidate who fused them adds one per field-and-comparison combination, and the class count multiplies. Phase 3 asks the same question about single-versus-grouped criteria.

In the reference solution, `RuleSet.decide()` never changes across all three phases. That's the payoff you're looking for, and the thing to show them in the debrief.

## Before first use

Run `mvn test` in `java-examples-generic` and confirm all three phases pass. The reference solution and tests have not been executed yet.
