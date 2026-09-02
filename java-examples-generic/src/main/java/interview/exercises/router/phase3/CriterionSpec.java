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
