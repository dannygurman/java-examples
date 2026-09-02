# Router Rule Matcher — Phase 3

**Time:** ~20 minutes.

Two changes.

## A rule can combine criteria

`ALL` requires every child to match. `ANY` requires at least one. These nest:

```
R20: priority 20, ALLOW
     ALL[ sourceIp 10.0.0.0/8,
          ANY[ destinationPort 443, destinationPort 8080 ],
          protocol TCP ]
```

## Order is no longer list order

Every rule has an explicit **priority**. Rules are evaluated from the **lowest priority number upward**. The order in which rules arrive in the list is meaningless.

## Updated spec types

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
