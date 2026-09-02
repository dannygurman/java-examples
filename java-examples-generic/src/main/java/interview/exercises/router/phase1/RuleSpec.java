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
