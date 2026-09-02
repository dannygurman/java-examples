package interview.exercises.firewall.phase3.match;

public record ExactMatcher(String expected) implements ValueMatcher {

    @Override
    public boolean matches(String fieldValue) {
        return expected.equals(fieldValue);
    }
}
