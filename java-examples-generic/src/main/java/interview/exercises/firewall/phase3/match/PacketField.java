package interview.exercises.firewall.phase3.match;

import interview.exercises.firewall.phase3.Packet;

import java.util.Arrays;

/**
 * Which field of a packet a criterion reads.
 * <p>
 * This is one of the two independent axes of matching: <em>what</em> to read.
 * How the value is compared is the other, and lives in {@link ValueMatcher}.
 */
public enum PacketField {

    SOURCE_IP("sourceIp") {
        @Override
        public String valueOf(Packet packet) {
            return packet.sourceIp();
        }
    },
    DESTINATION_IP("destinationIp") {
        @Override
        public String valueOf(Packet packet) {
            return packet.destinationIp();
        }
    },
    DESTINATION_PORT("destinationPort") {
        @Override
        public String valueOf(Packet packet) {
            return String.valueOf(packet.destinationPort());
        }
    },
    PROTOCOL("protocol") {
        @Override
        public String valueOf(Packet packet) {
            return packet.protocol().name();
        }
    };

    private final String specName;

    PacketField(String specName) {
        this.specName = specName;
    }

    public abstract String valueOf(Packet packet);

    public static PacketField fromSpecName(String specName) {
        return Arrays.stream(values())
                .filter(field -> field.specName.equals(specName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown packet field: " + specName));
    }
}
