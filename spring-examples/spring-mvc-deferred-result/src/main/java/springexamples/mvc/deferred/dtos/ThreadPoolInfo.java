package springexamples.mvc.deferred.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThreadPoolInfo {

    @JsonProperty(value = "activeCount")
    private int activeCount;

    @JsonProperty(value = "poolSize")
    private int poolSize;
}
