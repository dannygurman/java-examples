package interview.exercises.firewall.phase3.rule;

import interview.exercises.firewall.phase3.Action;
import interview.exercises.firewall.phase3.Decision;
import interview.exercises.firewall.phase3.Packet;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * A prioritised policy: the lowest-priority-number matching rule decides,
 * otherwise the default applies.
 * <p>
 * Ordering lives here and nowhere else. Phase 3 replaced list order with priority
 * order by changing the constructor; {@link #decide} did not change at all.
 */
public class RuleSet {

    private final List<Rule> rules;
    private final Action defaultAction;

    public RuleSet(List<Rule> rules, Action defaultAction) {
        this.rules = rules.stream()
                .sorted(Comparator.comparingInt(Rule::priority))
                .toList();
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
