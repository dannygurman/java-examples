package spring.examples.servicelocator.parsers;

import spring.examples.servicelocator.model.Worker;

import java.io.Reader;
import java.util.List;

public enum ContentType {

  JSON(TypeConstants.JSON_PARSER) {
    @Override
    public String fileName() {
      return FILE_PREFIX +".json";
    }
  },
  CSV(TypeConstants.CSV_PARSER) {
    @Override
    public String fileName() {
      return FILE_PREFIX +".csv";
    }
  },
  XML(TypeConstants.XML_PARSER) {
    @Override
    public String fileName() {
      return FILE_PREFIX + ".xml";
    }
  };

  private static String FILE_PREFIX = "workers";
  private final String parserName;

  ContentType(String parserName) {
    this.parserName = parserName;
  }
  
  public abstract String fileName();

  @Override
  public String toString() {
    return parserName;
  }

  // object interface
  public interface Parser {

    List<Worker> parse(Reader r);

    List<Worker> parse(String payload);
  }

  // factory interface
  public interface ParserFactory {

    Parser getParser(ContentType contentType);
  }

  // parserNames
  public interface TypeConstants {

    String CSV_PARSER = "csvParser";
    String JSON_PARSER = "jsonParser";
    String XML_PARSER = "xmlParser";
  }
}
