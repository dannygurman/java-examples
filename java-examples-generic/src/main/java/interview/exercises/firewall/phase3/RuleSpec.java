package interview.exercises.firewall.phase3;

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
