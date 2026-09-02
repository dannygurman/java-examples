package interview.exercises.firewall.phase2.match;

/**
 * Matches every value of its field. Stateless, so a single instance is enough.
 */
public final class AnyMatcher implements ValueMatcher {

    public static final AnyMatcher INSTANCE = new AnyMatcher();

    private AnyMatcher() {
    }

    @Override
    public boolean matches(String fieldValue) {
        return true;
    }
}
