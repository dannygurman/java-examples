package interview.exercises.firewall.phase3.match;

import interview.exercises.firewall.phase3.Packet;

import java.util.List;

/**
 * Matches when every child matches. An empty group matches everything.
 */
public record AllOf(List<Criterion> children) implements Criterion {

    public AllOf {
        children = List.copyOf(children);
    }

    @Override
    public boolean matches(Packet packet) {
        return children.stream().allMatch(child -> child.matches(packet));
    }
}
