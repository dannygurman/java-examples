package interview.exercises.router.phase3.match;

import java.util.List;
import java.util.Optional;

/**
 * Chooses a matcher from the syntax of a configured value.
 * <p>
 * Parsers are tried in order; the exact-match parser is last and always succeeds.
 */
public class ValueMatcherFactory {

    private static final String RANGE_SYNTAX = "\\d+-\\d+";

    private static final List<ValueMatcherParser> PARSERS = List.of(
            value -> "any".equalsIgnoreCase(value)
                    ? Optional.of(AnyMatcher.INSTANCE)
                    : Optional.empty(),
            value -> value.contains("/")
                    ? Optional.of(new CidrMatcher(value))
                    : Optional.empty(),
            value -> value.matches(RANGE_SYNTAX)
                    ? Optional.of(portRange(value))
                    : Optional.empty(),
            value -> Optional.of(new ExactMatcher(value)));

    public ValueMatcher create(String value) {
        return PARSERS.stream()
                .map(parser -> parser.parse(value))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unparsable rule value: " + value));
    }

    private static ValueMatcher portRange(String value) {
        String[] bounds = value.split("-");
        return new PortRangeMatcher(Integer.parseInt(bounds[0]), Integer.parseInt(bounds[1]));
    }
}
