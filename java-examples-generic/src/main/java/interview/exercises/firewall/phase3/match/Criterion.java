package interview.exercises.firewall.phase3.match;

import interview.exercises.firewall.phase3.Packet;

/**
 * Anything that can decide whether it applies to a packet.
 * <p>
 * A single comparison and a group of comparisons are both criteria, so nothing
 * that evaluates a criterion needs to know which it is holding.
 */
public interface Criterion {

    boolean matches(Packet packet);
}
