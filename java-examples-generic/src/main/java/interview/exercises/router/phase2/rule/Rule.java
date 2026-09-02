package interview.exercises.router.phase2.rule;

import interview.exercises.router.phase2.Action;
import interview.exercises.router.phase2.Packet;
import interview.exercises.router.phase2.match.Criterion;

/**
 * A rule knows whether it applies to a packet and what to do about it.
 * It does not know its position in the policy, or how decisions are reported.
 */
public record Rule(String id, Action action, Criterion criterion) {

    public boolean matches(Packet packet) {
        return criterion.matches(packet);
    }
}
