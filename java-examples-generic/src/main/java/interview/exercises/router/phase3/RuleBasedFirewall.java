package interview.exercises.router.phase3;

import interview.exercises.router.phase3.json.DecisionSerializer;
import interview.exercises.router.phase3.rule.Rule;
import interview.exercises.router.phase3.rule.RuleFactory;
import interview.exercises.router.phase3.rule.RuleSet;

import java.util.List;

public class RuleBasedFirewall implements Firewall {

    private static final Action DEFAULT_ACTION = Action.DENY;

    private final RuleFactory ruleFactory = new RuleFactory();
    private final DecisionSerializer serializer = new DecisionSerializer();

    @Override
    public String evaluate(List<RuleSpec> specs, List<Packet> packets) {
        List<Rule> rules = specs.stream()
                .map(ruleFactory::create)
                .toList();

        RuleSet ruleSet = new RuleSet(rules, DEFAULT_ACTION);

        List<Decision> decisions = packets.stream()
                .map(ruleSet::decide)
                .toList();

        return serializer.toJson(decisions);
    }
}
