package interview.exercises.router.phase1.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import interview.exercises.router.phase1.Decision;

import java.util.List;

/**
 * Renders decisions as JSON. Nothing in the rule model knows this class exists.
 */
public class DecisionSerializer {

    private final ObjectMapper mapper = new ObjectMapper();

    public String toJson(List<Decision> decisions) {
        ArrayNode array = mapper.createArrayNode();

        for (int packetIndex = 0; packetIndex < decisions.size(); packetIndex++) {
            Decision decision = decisions.get(packetIndex);

            ObjectNode node = array.addObject();
            node.put("packet", packetIndex);
            node.put("rule", decision.ruleId());
            node.put("action", decision.action().name());
        }

        return array.toString();
    }
}
