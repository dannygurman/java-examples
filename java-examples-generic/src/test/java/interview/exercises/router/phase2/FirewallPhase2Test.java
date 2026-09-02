package interview.exercises.router.phase2;

import com.fasterxml.jackson.databind.ObjectMapper;
import interview.exercises.firewall.phase2.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FirewallPhase2Test {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final List<RuleSpec> rules = List.of(
            new RuleSpec("R1", Action.DENY,  "protocol",        "UDP"),
            new RuleSpec("R2", Action.ALLOW, "destinationPort", "8000-8999"),
            new RuleSpec("R3", Action.ALLOW, "sourceIp",        "10.0.0.0/8"),
            new RuleSpec("R4", Action.DENY,  "destinationIp",   "any"));

    private Firewall firewall;

    @Before
    public void setUp() {
        firewall = new RuleBasedFirewall();
    }

    @Test
    public void rangesSubnetsAndWildcardsAllApply() throws Exception {
        List<Packet> packets = List.of(
                new Packet("10.5.0.7",       "1.1.1.1", 8080, Protocol.TCP),
                new Packet("10.5.0.7",       "1.1.1.1", 443,  Protocol.TCP),
                new Packet("172.16.0.9",     "1.1.1.1", 443,  Protocol.TCP),
                new Packet("10.5.0.7",       "1.1.1.1", 8500, Protocol.UDP),
                new Packet("9.255.255.255",  "1.1.1.1", 443,  Protocol.TCP),
                new Packet("10.255.255.255", "1.1.1.1", 7999, Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "R2", "action": "ALLOW" },
                  { "packet": 1, "rule": "R3", "action": "ALLOW" },
                  { "packet": 2, "rule": "R4", "action": "DENY"  },
                  { "packet": 3, "rule": "R1", "action": "DENY"  },
                  { "packet": 4, "rule": "R4", "action": "DENY"  },
                  { "packet": 5, "rule": "R3", "action": "ALLOW" }
                ]""",
                firewall.evaluate(rules, packets));
    }

    @Test
    public void portRangeIsInclusiveAtBothEnds() throws Exception {
        List<RuleSpec> rangeOnly = List.of(
                new RuleSpec("RANGE", Action.ALLOW, "destinationPort", "8000-8999"));

        List<Packet> aroundTheRange = List.of(
                new Packet("1.1.1.1", "2.2.2.2", 7999, Protocol.TCP),
                new Packet("1.1.1.1", "2.2.2.2", 8000, Protocol.TCP),
                new Packet("1.1.1.1", "2.2.2.2", 8999, Protocol.TCP),
                new Packet("1.1.1.1", "2.2.2.2", 9000, Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": null,    "action": "DENY"  },
                  { "packet": 1, "rule": "RANGE", "action": "ALLOW" },
                  { "packet": 2, "rule": "RANGE", "action": "ALLOW" },
                  { "packet": 3, "rule": null,    "action": "DENY"  }
                ]""",
                firewall.evaluate(rangeOnly, aroundTheRange));
    }

    @Test
    public void cidrBoundariesAreRespected() throws Exception {
        List<RuleSpec> subnetOnly = List.of(
                new RuleSpec("SUBNET", Action.ALLOW, "sourceIp", "192.168.1.0/24"));

        List<Packet> aroundTheSubnet = List.of(
                new Packet("192.168.0.255", "2.2.2.2", 1, Protocol.TCP),
                new Packet("192.168.1.0",   "2.2.2.2", 1, Protocol.TCP),
                new Packet("192.168.1.255", "2.2.2.2", 1, Protocol.TCP),
                new Packet("192.168.2.0",   "2.2.2.2", 1, Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": null,     "action": "DENY"  },
                  { "packet": 1, "rule": "SUBNET", "action": "ALLOW" },
                  { "packet": 2, "rule": "SUBNET", "action": "ALLOW" },
                  { "packet": 3, "rule": null,     "action": "DENY"  }
                ]""",
                firewall.evaluate(subnetOnly, aroundTheSubnet));
    }

    @Test
    public void slashThirtyTwoMatchesExactlyOneAddress() throws Exception {
        List<RuleSpec> hostRoute = List.of(
                new RuleSpec("HOST", Action.ALLOW, "sourceIp", "10.0.0.5/32"));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "HOST", "action": "ALLOW" },
                  { "packet": 1, "rule": null,   "action": "DENY"  }
                ]""",
                firewall.evaluate(hostRoute, twoHosts()));
    }

    @Test
    public void slashZeroMatchesEveryAddress() throws Exception {
        List<RuleSpec> defaultRoute = List.of(
                new RuleSpec("ALL", Action.ALLOW, "sourceIp", "0.0.0.0/0"));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "ALL", "action": "ALLOW" },
                  { "packet": 1, "rule": "ALL", "action": "ALLOW" }
                ]""",
                firewall.evaluate(defaultRoute, twoHosts()));
    }

    @Test
    public void anyMatchesEveryValueOfItsField() throws Exception {
        List<RuleSpec> catchAll = List.of(
                new RuleSpec("ANY", Action.ALLOW, "destinationIp", "any"));

        List<Packet> unrelated = List.of(
                new Packet("1.1.1.1", "8.8.8.8",   53, Protocol.UDP),
                new Packet("2.2.2.2", "127.0.0.1", 1,  Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "ANY", "action": "ALLOW" },
                  { "packet": 1, "rule": "ANY", "action": "ALLOW" }
                ]""",
                firewall.evaluate(catchAll, unrelated));
    }

    @Test
    public void exactMatchingStillWorks() throws Exception {
        List<RuleSpec> exact = List.of(
                new RuleSpec("EXACT", Action.ALLOW, "sourceIp", "10.0.0.5"));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "EXACT", "action": "ALLOW" },
                  { "packet": 1, "rule": null,    "action": "DENY"  }
                ]""",
                firewall.evaluate(exact, twoHosts()));
    }

    private static List<Packet> twoHosts() {
        return List.of(
                new Packet("10.0.0.5", "2.2.2.2", 1, Protocol.TCP),
                new Packet("10.0.0.6", "2.2.2.2", 1, Protocol.TCP));
    }

    private static void assertJsonEquals(String expected, String actual) throws Exception {
        assertEquals(MAPPER.readTree(expected), MAPPER.readTree(actual));
    }
}
