package interview.exercises.router.phase1.rule;

import interview.exercises.router.phase1.RuleSpec;
import interview.exercises.router.phase1.match.Criterion;
import interview.exercises.router.phase1.match.ExactMatcher;
import interview.exercises.router.phase1.match.PacketField;

/**
 * The single place that knows how to turn flat spec data into the rule model.
 */
public class RuleFactory {

    public Rule create(RuleSpec spec) {
        Criterion criterion = new Criterion(
                PacketField.fromSpecName(spec.field()),
                new ExactMatcher(spec.value()));

        return new Rule(spec.id(), spec.action(), criterion);
    }
}
