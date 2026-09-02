package interview.exercises.router.phase2.match;

import interview.exercises.router.phase2.Packet;

/**
 * Reads one field off a packet and compares it.
 * <p>
 * Field and comparison vary independently, so adding a new comparison does not
 * multiply with the number of fields.
 */
public record Criterion(PacketField field, ValueMatcher matcher) {

    public boolean matches(Packet packet) {
        return matcher.matches(field.valueOf(packet));
    }
}
