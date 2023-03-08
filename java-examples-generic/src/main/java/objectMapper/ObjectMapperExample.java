package objectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ObjectMapperExample {

    final static private ObjectMapper OBJECT_MAPPER = new ObjectMapper();//Object mapper is thread safe

    public static void main(String[] args) throws Exception {
        POJO pojo = POJO.builder()
            .field1Str("xxx")
            .field2Map(new HashMap<>())
            .build();

        String json = OBJECT_MAPPER.writeValueAsString(pojo);
        System.out.println("Json: [" + json + "]");
        Path path = Paths.get("examplefile.json");
        OBJECT_MAPPER.writeValue(path.toFile(), pojo);
    }

}
