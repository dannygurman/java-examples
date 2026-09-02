package interview.exercises.firewall.phase2.match;

/**
 * Matches a port inside an inclusive range, written {@code 8000-8999}.
 */
public record PortRangeMatcher(int from, int to) implements ValueMatcher {

    @Override
    public boolean matches(String fieldValue) {
        int port = Integer.parseInt(fieldValue);
        return port >= from && port <= to;
    }
}
