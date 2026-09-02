package interview.exercises.firewall.phase2.match;

public record ExactMatcher(String expected) implements ValueMatcher {

    @Override
    public boolean matches(String fieldValue) {
        return expected.equals(fieldValue);
    }
}
