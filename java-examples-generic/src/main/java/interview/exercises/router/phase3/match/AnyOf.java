package interview.exercises.router.phase3.match;

import interview.exercises.router.phase3.Packet;

import java.util.List;

/**
 * Matches when at least one child matches. An empty group matches nothing.
 */
public record AnyOf(List<Criterion> children) implements Criterion {

    public AnyOf {
        children = List.copyOf(children);
    }

    @Override
    public boolean matches(Packet packet) {
        return children.stream().anyMatch(child -> child.matches(packet));
    }
}
