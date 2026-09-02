package interview.exercises.router.phase2.rule;

import interview.exercises.router.phase2.RuleSpec;
import interview.exercises.router.phase2.match.Criterion;
import interview.exercises.router.phase2.match.PacketField;
import interview.exercises.router.phase2.match.ValueMatcherFactory;

/**
 * The single place that knows how to turn flat spec data into the rule model.
 * <p>
 * Phase 2 changed exactly one line here: the matcher is now chosen from the value
 * syntax instead of always being an exact match.
 */
public class RuleFactory {

    private final ValueMatcherFactory matcherFactory = new ValueMatcherFactory();

    public Rule create(RuleSpec spec) {
        Criterion criterion = new Criterion(
                PacketField.fromSpecName(spec.field()),
                matcherFactory.create(spec.value()));

        return new Rule(spec.id(), spec.action(), criterion);
    }
}
