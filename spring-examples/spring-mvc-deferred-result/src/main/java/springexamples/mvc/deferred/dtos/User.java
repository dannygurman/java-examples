package springexamples.mvc.deferred.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "name")
    private String name;
}
