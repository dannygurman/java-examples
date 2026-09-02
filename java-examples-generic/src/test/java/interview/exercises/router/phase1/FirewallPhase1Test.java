package interview.exercises.router.phase1;

import com.fasterxml.jackson.databind.ObjectMapper;
import interview.exercises.firewall.phase1.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FirewallPhase1Test {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final List<RuleSpec> rules = List.of(
            new RuleSpec("R1", Action.DENY,  "protocol",        "UDP"),
            new RuleSpec("R2", Action.ALLOW, "destinationPort", "443"),
            new RuleSpec("R3", Action.ALLOW, "sourceIp",        "10.0.0.5"),
            new RuleSpec("R4", Action.DENY,  "destinationPort", "22"));

    private final List<Packet> packets = List.of(
            new Packet("10.0.0.5",    "93.184.216.34", 443,  Protocol.TCP),
            new Packet("10.0.0.5",    "8.8.8.8",       53,   Protocol.UDP),
            new Packet("192.168.1.9", "10.0.0.1",      22,   Protocol.TCP),
            new Packet("172.16.0.4",  "10.0.0.1",      8080, Protocol.TCP),
            new Packet("10.0.0.5",    "10.0.0.1",      8080, Protocol.TCP));

    private Firewall firewall;

    @Before
    public void setUp() {
        firewall = new RuleBasedFirewall();
    }

    @Test
    public void firstMatchingRuleDecides() throws Exception {
        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "R2",  "action": "ALLOW" },
                  { "packet": 1, "rule": "R1",  "action": "DENY"  },
                  { "packet": 2, "rule": "R4",  "action": "DENY"  },
                  { "packet": 3, "rule": null,  "action": "DENY"  },
                  { "packet": 4, "rule": "R3",  "action": "ALLOW" }
                ]""",
                firewall.evaluate(rules, packets));
    }

    @Test
    public void ruleOrderDecidesWhenTwoRulesMatch() throws Exception {
        // R3 would allow this packet, but R1 denies it first.
        List<Packet> udpFromAllowedSource = List.of(
                new Packet("10.0.0.5", "8.8.8.8", 53, Protocol.UDP));

        assertJsonEquals("""
                [ { "packet": 0, "rule": "R1", "action": "DENY" } ]""",
                firewall.evaluate(rules, udpFromAllowedSource));
    }

    @Test
    public void reversingRuleOrderReversesTheDecision() throws Exception {
        List<RuleSpec> allowFirst = List.of(
                new RuleSpec("R3", Action.ALLOW, "sourceIp", "10.0.0.5"),
                new RuleSpec("R1", Action.DENY,  "protocol", "UDP"));

        List<Packet> udpFromAllowedSource = List.of(
                new Packet("10.0.0.5", "8.8.8.8", 53, Protocol.UDP));

        assertJsonEquals("""
                [ { "packet": 0, "rule": "R3", "action": "ALLOW" } ]""",
                firewall.evaluate(allowFirst, udpFromAllowedSource));
    }

    @Test
    public void unmatchedPacketIsDeniedWithNoRule() throws Exception {
        List<Packet> unmatched = List.of(
                new Packet("172.16.0.4", "10.0.0.1", 8080, Protocol.TCP));

        assertJsonEquals("""
                [ { "packet": 0, "rule": null, "action": "DENY" } ]""",
                firewall.evaluate(rules, unmatched));
    }

    @Test
    public void emptyRuleListDeniesEverything() throws Exception {
        assertJsonEquals("""
                [
                  { "packet": 0, "rule": null, "action": "DENY" },
                  { "packet": 1, "rule": null, "action": "DENY" }
                ]""",
                firewall.evaluate(List.of(), packets.subList(0, 2)));
    }

    @Test
    public void noPacketsProducesAnEmptyArray() throws Exception {
        assertJsonEquals("[]", firewall.evaluate(rules, List.of()));
    }

    @Test
    public void eachFieldCanBeMatchedOn() throws Exception {
        List<RuleSpec> oneRulePerField = List.of(
                new RuleSpec("SRC",   Action.ALLOW, "sourceIp",        "1.1.1.1"),
                new RuleSpec("DST",   Action.ALLOW, "destinationIp",   "2.2.2.2"),
                new RuleSpec("PORT",  Action.ALLOW, "destinationPort", "99"),
                new RuleSpec("PROTO", Action.ALLOW, "protocol",        "UDP"));

        List<Packet> packetPerField = List.of(
                new Packet("1.1.1.1", "9.9.9.9", 1,  Protocol.TCP),
                new Packet("9.9.9.9", "2.2.2.2", 1,  Protocol.TCP),
                new Packet("9.9.9.9", "9.9.9.9", 99, Protocol.TCP),
                new Packet("9.9.9.9", "9.9.9.9", 1,  Protocol.UDP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "SRC",   "action": "ALLOW" },
                  { "packet": 1, "rule": "DST",   "action": "ALLOW" },
                  { "packet": 2, "rule": "PORT",  "action": "ALLOW" },
                  { "packet": 3, "rule": "PROTO", "action": "ALLOW" }
                ]""",
                firewall.evaluate(oneRulePerField, packetPerField));
    }

    /** Compares as parsed JSON, so whitespace and key order do not matter. */
    private static void assertJsonEquals(String expected, String actual) throws Exception {
        assertEquals(MAPPER.readTree(expected), MAPPER.readTree(actual));
    }
}
