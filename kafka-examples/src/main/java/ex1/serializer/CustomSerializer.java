package ex1.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import ex1.pojo.CustomObject;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by dannyg on 12/13/2018.
 */

public class CustomSerializer implements Serializer<CustomObject> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, CustomObject data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception exception) {
            System.out.println("Error in serializing object" + data);
        }
        return retVal;
    }

    @Override
    public void close() {

    }

}