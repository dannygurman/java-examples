package interview.exercises.firewall.phase1.match;

public record ExactMatcher(String expected) implements ValueMatcher {

    @Override
    public boolean matches(String fieldValue) {
        return expected.equals(fieldValue);
    }
}
