package interview.exercises.router.phase3;

/**
 * The outcome for a single packet.
 *
 * @param ruleId the rule that decided, or {@code null} when no rule matched
 */
public record Decision(String ruleId, Action action) {
}
