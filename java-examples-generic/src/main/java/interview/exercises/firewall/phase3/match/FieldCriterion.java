package interview.exercises.firewall.phase3.match;

import interview.exercises.firewall.phase3.Packet;

/**
 * Reads one field off a packet and compares it.
 * <p>
 * Field and comparison vary independently, so adding a new comparison does not
 * multiply with the number of fields.
 */
public record FieldCriterion(PacketField field, ValueMatcher matcher) implements Criterion {

    @Override
    public boolean matches(Packet packet) {
        return matcher.matches(field.valueOf(packet));
    }
}
