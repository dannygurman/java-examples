package interview.exercises.firewall.phase2.match;

/**
 * How a single field value is compared.
 * <p>
 * Exact equality, subnet and range matching are separate implementations,
 * added without changing anything that uses this type.
 */
@FunctionalInterface
public interface ValueMatcher {

    boolean matches(String fieldValue);
}
