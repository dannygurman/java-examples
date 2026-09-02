package interview.exercises.firewall.phase3;

import java.util.List;

public interface Firewall {

    /**
     * Evaluates every packet against the rules and returns the decisions as a JSON array.
     */
    String evaluate(List<RuleSpec> rules, List<Packet> packets);
}
