package spring.examples.servicelocator.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.examples.servicelocator.exception.ParserIOException;
import spring.examples.servicelocator.model.Worker;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component(ContentType.TypeConstants.JSON_PARSER)
@Slf4j
public class JSONParser implements ContentType.Parser {

  private static ObjectMapper _cached;

  static {
    _cached = new ObjectMapper();
  }

  @Override
  public List<Worker> parse(Reader r) {
    try {
      return _cached.readValue(r, new TypeReference<List<Worker>>() {
      });

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing JSON file", e);
    }
  }

  @Override
  public List<Worker> parse(String payload) {
    try {
      return _cached.readValue(payload, new TypeReference<List<Worker>>() {
      });

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing JSON file", e);
    }
  }
}
