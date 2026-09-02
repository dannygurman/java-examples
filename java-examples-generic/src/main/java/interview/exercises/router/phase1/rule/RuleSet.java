package interview.exercises.router.phase1.rule;

import interview.exercises.router.phase1.Action;
import interview.exercises.router.phase1.Decision;
import interview.exercises.router.phase1.Packet;

import java.util.List;

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
        return rules.stream()
                .filter(rule -> rule.matches(packet))
                .findFirst()
                .map(rule -> new Decision(rule.id(), rule.action()))
                .orElseGet(() -> new Decision(null, defaultAction));
    }
}
