package interview.exercises.firewall.phase3.match;

import java.util.Optional;

/**
 * Recognises one value syntax and builds the matcher for it.
 * <p>
 * A new syntax means a new parser, not an edit to an existing one.
 */
@FunctionalInterface
public interface ValueMatcherParser {

    Optional<ValueMatcher> parse(String value);
}
