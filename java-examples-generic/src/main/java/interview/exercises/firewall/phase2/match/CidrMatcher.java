package interview.exercises.firewall.phase2.match;

/**
 * Matches an IPv4 address inside a subnet, written {@code 10.0.0.0/8}.
 * <p>
 * The address and prefix are collapsed to a network and a mask once, at construction,
 * so matching is two integer operations.
 */
public final class CidrMatcher implements ValueMatcher {

    private final int network;
    private final int mask;

    public CidrMatcher(String cidr) {
        String[] parts = cidr.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Not a CIDR block: " + cidr);
        }
        this.mask = maskOf(Integer.parseInt(parts[1]));
        this.network = toInt(parts[0]) & mask;
    }

    @Override
    public boolean matches(String fieldValue) {
        return (toInt(fieldValue) & mask) == network;
    }

    private static int maskOf(int prefixLength) {
        if (prefixLength < 0 || prefixLength > 32) {
            throw new IllegalArgumentException("Prefix length out of range: " + prefixLength);
        }
        return prefixLength == 0 ? 0 : -1 << (32 - prefixLength);
    }

    private static int toInt(String ipv4) {
        String[] octets = ipv4.split("\\.");
        if (octets.length != 4) {
            throw new IllegalArgumentException("Not an IPv4 address: " + ipv4);
        }
        int value = 0;
        for (String octet : octets) {
            value = (value << 8) | Integer.parseInt(octet);
        }
        return value;
    }
}
