# Router Rule Matcher — Phase 2

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
