package interview.exercises.router.phase3.rule;

import interview.exercises.router.phase3.RuleSpec;
import interview.exercises.router.phase3.match.CriterionFactory;

/**
 * The single place that knows how to turn flat spec data into the rule model.
 */
public class RuleFactory {

    private final CriterionFactory criterionFactory = new CriterionFactory();

    public Rule create(RuleSpec spec) {
        return new Rule(spec.id(),
                spec.priority(),
                spec.action(),
                criterionFactory.create(spec.criterion()));
    }
}
