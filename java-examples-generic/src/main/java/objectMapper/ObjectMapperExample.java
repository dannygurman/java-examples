package objectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class ObjectMapperExample {

    final static private ObjectMapper OBJECT_MAPPER = new ObjectMapper();//Object mapper is thread safe

    public static void main(String[] args) throws Exception {
        POJO pojo = POJO.builder()
            .field1Str("a")
            .field2Map(new HashMap<>())
            .build();

        String json = OBJECT_MAPPER.writeValueAsString(pojo);
        System.out.println("Json: [" + json + "]");

    }

}
