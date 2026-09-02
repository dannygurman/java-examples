package interview.exercises.router.phase3.match;

import interview.exercises.router.phase3.CriterionSpec;

import java.util.List;

/**
 * Builds the criterion tree from configuration.
 * <p>
 * This is the one place allowed to know every concrete criterion type; everywhere
 * else works through {@link Criterion}.
 */
public class CriterionFactory {

    private static final String MATCH = "MATCH";
    private static final String ALL = "ALL";
    private static final String ANY = "ANY";

    private final ValueMatcherFactory matcherFactory = new ValueMatcherFactory();

    public Criterion create(CriterionSpec spec) {
        return switch (spec.op()) {
            case MATCH -> new FieldCriterion(
                    PacketField.fromSpecName(spec.field()),
                    matcherFactory.create(spec.value()));
            case ALL -> new AllOf(children(spec));
            case ANY -> new AnyOf(children(spec));
            default -> throw new IllegalArgumentException("Unknown criterion op: " + spec.op());
        };
    }

    private List<Criterion> children(CriterionSpec spec) {
        return spec.children().stream()
                .map(this::create)
                .toList();
    }
}
