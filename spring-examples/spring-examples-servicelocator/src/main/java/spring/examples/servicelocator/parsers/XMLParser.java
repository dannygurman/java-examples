package spring.examples.servicelocator.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.examples.servicelocator.exception.ParserIOException;
import spring.examples.servicelocator.model.Worker;


@Component(ContentType.TypeConstants.XML_PARSER)
@Slf4j
public class XMLParser implements ContentType.Parser {

  private static XmlMapper _cached;

  static {
    _cached = new XmlMapper();
  }

  @Override
  public List<Worker> parse(Reader r) {
    try {
      return _cached.readValue(r, new TypeReference<List<Worker>>() {
      });

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing XML file", e);
    }
  }

  @Override
  public List<Worker> parse(String payload) {
    try {
      return _cached.readValue(payload, new TypeReference<List<Worker>>() {
      });

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing XML file", e);
    }
  }
}