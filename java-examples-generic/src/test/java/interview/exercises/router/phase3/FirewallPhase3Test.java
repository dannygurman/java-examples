package interview.exercises.router.phase3;

import com.fasterxml.jackson.databind.ObjectMapper;
import interview.exercises.firewall.phase3.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FirewallPhase3Test {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /** ALLOW TCP from 10/8 to either 443 or 8080. */
    private static final RuleSpec ALLOW_WEB_FROM_INTERNAL = new RuleSpec("R20", 20, Action.ALLOW,
            all(match("sourceIp", "10.0.0.0/8"),
                any(match("destinationPort", "443"),
                    match("destinationPort", "8080")),
                match("protocol", "TCP")));

    private static final RuleSpec DENY_UDP = new RuleSpec("R10", 10, Action.DENY,
            match("protocol", "UDP"));

    private static final RuleSpec DENY_EVERYTHING = new RuleSpec("R30", 30, Action.DENY,
            match("destinationIp", "any"));

    private static final String EXPECTED = """
            [
              { "packet": 0, "rule": "R20", "action": "ALLOW" },
              { "packet": 1, "rule": "R10", "action": "DENY"  },
              { "packet": 2, "rule": "R30", "action": "DENY"  },
              { "packet": 3, "rule": "R30", "action": "DENY"  },
              { "packet": 4, "rule": "R20", "action": "ALLOW" }
            ]""";

    /** Deliberately not in priority order. */
    private final List<RuleSpec> rules = List.of(
            ALLOW_WEB_FROM_INTERNAL, DENY_EVERYTHING, DENY_UDP);

    private final List<Packet> packets = List.of(
            new Packet("10.0.0.5",   "1.1.1.1", 443,  Protocol.TCP),
            new Packet("10.0.0.5",   "1.1.1.1", 443,  Protocol.UDP),
            new Packet("10.0.0.5",   "1.1.1.1", 9090, Protocol.TCP),
            new Packet("172.16.0.1", "1.1.1.1", 8080, Protocol.TCP),
            new Packet("10.0.0.5",   "1.1.1.1", 8080, Protocol.TCP));

    private Firewall firewall;

    @Before
    public void setUp() {
        firewall = new RuleBasedFirewall();
    }

    @Test
    public void compositeRulesAreEvaluatedInPriorityOrder() throws Exception {
        assertJsonEquals(EXPECTED, firewall.evaluate(rules, packets));
    }

    @Test
    public void inputListOrderIsIrrelevant() throws Exception {
        List<RuleSpec> shuffled = List.of(DENY_UDP, ALLOW_WEB_FROM_INTERNAL, DENY_EVERYTHING);
        List<RuleSpec> reversed = List.of(DENY_EVERYTHING, DENY_UDP, ALLOW_WEB_FROM_INTERNAL);

        assertJsonEquals(EXPECTED, firewall.evaluate(shuffled, packets));
        assertJsonEquals(EXPECTED, firewall.evaluate(reversed, packets));
    }

    @Test
    public void allRequiresEveryChildToMatch() throws Exception {
        List<RuleSpec> onlyTheCompositeRule = List.of(ALLOW_WEB_FROM_INTERNAL);

        List<Packet> oneGoodThreeBad = List.of(
                new Packet("10.0.0.5",   "1.1.1.1", 443, Protocol.TCP),   // all three match
                new Packet("172.16.0.1", "1.1.1.1", 443, Protocol.TCP),   // wrong subnet
                new Packet("10.0.0.5",   "1.1.1.1", 22,  Protocol.TCP),   // wrong port
                new Packet("10.0.0.5",   "1.1.1.1", 443, Protocol.UDP));  // wrong protocol

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "R20", "action": "ALLOW" },
                  { "packet": 1, "rule": null,  "action": "DENY"  },
                  { "packet": 2, "rule": null,  "action": "DENY"  },
                  { "packet": 3, "rule": null,  "action": "DENY"  }
                ]""",
                firewall.evaluate(onlyTheCompositeRule, oneGoodThreeBad));
    }

    @Test
    public void anyRequiresOneChildToMatch() throws Exception {
        List<RuleSpec> eitherPort = List.of(new RuleSpec("EITHER", 1, Action.ALLOW,
                any(match("destinationPort", "443"),
                    match("destinationPort", "8080"))));

        List<Packet> threePorts = List.of(
                new Packet("1.1.1.1", "2.2.2.2", 443,  Protocol.TCP),
                new Packet("1.1.1.1", "2.2.2.2", 8080, Protocol.TCP),
                new Packet("1.1.1.1", "2.2.2.2", 9090, Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "EITHER", "action": "ALLOW" },
                  { "packet": 1, "rule": "EITHER", "action": "ALLOW" },
                  { "packet": 2, "rule": null,     "action": "DENY"  }
                ]""",
                firewall.evaluate(eitherPort, threePorts));
    }

    @Test
    public void criteriaNestMoreThanTwoDeep() throws Exception {
        List<RuleSpec> deeplyNested = List.of(new RuleSpec("DEEP", 1, Action.ALLOW,
                all(match("protocol", "TCP"),
                    any(all(match("sourceIp", "10.0.0.0/8"),
                            match("destinationPort", "443")),
                        all(match("sourceIp", "192.168.0.0/16"),
                            match("destinationPort", "8080"))))));

        List<Packet> packetsForBranches = List.of(
                new Packet("10.0.0.5",    "2.2.2.2", 443,  Protocol.TCP),  // first branch
                new Packet("192.168.1.7", "2.2.2.2", 8080, Protocol.TCP),  // second branch
                new Packet("10.0.0.5",    "2.2.2.2", 8080, Protocol.TCP),  // crossed branches
                new Packet("10.0.0.5",    "2.2.2.2", 443,  Protocol.UDP)); // outer ALL fails

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "DEEP", "action": "ALLOW" },
                  { "packet": 1, "rule": "DEEP", "action": "ALLOW" },
                  { "packet": 2, "rule": null,   "action": "DENY"  },
                  { "packet": 3, "rule": null,   "action": "DENY"  }
                ]""",
                firewall.evaluate(deeplyNested, packetsForBranches));
    }

    @Test
    public void aSingleMatchCriterionStillWorksOnItsOwn() throws Exception {
        List<RuleSpec> singleCriterion = List.of(
                new RuleSpec("SOLO", 1, Action.ALLOW, match("protocol", "UDP")));

        List<Packet> twoProtocols = List.of(
                new Packet("1.1.1.1", "2.2.2.2", 53, Protocol.UDP),
                new Packet("1.1.1.1", "2.2.2.2", 53, Protocol.TCP));

        assertJsonEquals("""
                [
                  { "packet": 0, "rule": "SOLO", "action": "ALLOW" },
                  { "packet": 1, "rule": null,   "action": "DENY"  }
                ]""",
                firewall.evaluate(singleCriterion, twoProtocols));
    }

    private static CriterionSpec match(String field, String value) {
        return new CriterionSpec("MATCH", field, value, List.of());
    }

    private static CriterionSpec all(CriterionSpec... children) {
        return new CriterionSpec("ALL", null, null, List.of(children));
    }

    private static CriterionSpec any(CriterionSpec... children) {
        return new CriterionSpec("ANY", null, null, List.of(children));
    }

    private static void assertJsonEquals(String expected, String actual) throws Exception {
        assertEquals(MAPPER.readTree(expected), MAPPER.readTree(actual));
    }
}
