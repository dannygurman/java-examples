package interview.exercises.firewall.phase1.rule;

import interview.exercises.firewall.phase1.Action;
import interview.exercises.firewall.phase1.Decision;
import interview.exercises.firewall.phase1.Packet;

import java.util.List;
import java.util.Optional;

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
        Optional<Rule> firstMatch = rules.stream()
                .filter(rule -> rule.matches(packet))
                .findFirst();

        return firstMatch
                .map(rule -> new Decision(rule.id(), rule.action()))
                .orElseGet(() -> new Decision(null, defaultAction));
    }
}
