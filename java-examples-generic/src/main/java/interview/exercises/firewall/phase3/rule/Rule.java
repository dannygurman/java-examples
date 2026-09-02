package interview.exercises.firewall.phase3.rule;

import interview.exercises.firewall.phase3.Action;
import interview.exercises.firewall.phase3.Packet;
import interview.exercises.firewall.phase3.match.Criterion;

/**
 * A rule knows whether it applies to a packet and what to do about it.
 * It does not know how decisions are reported, or how it compares to other rules.
 */
public record Rule(String id, int priority, Action action, Criterion criterion) {

    public boolean matches(Packet packet) {
        return criterion.matches(packet);
    }
}
