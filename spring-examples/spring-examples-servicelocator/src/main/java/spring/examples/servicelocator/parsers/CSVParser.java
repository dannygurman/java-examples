package spring.examples.servicelocator.parsers;

import static java.util.stream.Collectors.toList;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.simpleflatmapper.csv.CsvParser;
import org.springframework.stereotype.Component;
import spring.examples.servicelocator.config.TypeConstants;
import spring.examples.servicelocator.exception.ParserIOException;
import spring.examples.servicelocator.model.Worker;

@Component(TypeConstants.CSV_PARSER)
@Slf4j
public class CSVParser implements Parser {

  private static CsvParser.MapToDSL _cached;

  static {
    _cached = CsvParser
        .separator(',')
        .trimSpaces()
        .mapTo(Worker.class);
  }

  @Override
  public List<Worker> parse(Reader r) {
    try {
      return (List<Worker>) _cached.stream(r).collect(toList());

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing CSV file", e);
    }
  }

  @Override
  public List<Worker> parse(String payload) {
    try {
      return (List<Worker>) _cached.stream(payload).collect(toList());

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing CSV file", e);
    }
  }
}
