package interview.exercises.router.phase1.match;

/**
 * How a single field value is compared.
 * <p>
 * Phase 1 needs only exact equality. Later phases add subnet and range matching
 * by adding implementations here, not by changing anything that uses this type.
 */
@FunctionalInterface
public interface ValueMatcher {

    boolean matches(String fieldValue);
}
