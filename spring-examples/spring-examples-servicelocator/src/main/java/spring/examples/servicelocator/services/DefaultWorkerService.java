package spring.examples.servicelocator.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.examples.servicelocator.model.Worker;
import spring.examples.servicelocator.config.ContentType;
import spring.examples.servicelocator.parsers.Parser;
import spring.examples.servicelocator.factory.ParserFactory;

@Service
@Slf4j
public class DefaultWorkerService implements WorkersService {

  private ParserFactory parserFactory;

  @Autowired
  public DefaultWorkerService(ParserFactory parserFactory) {
    this.parserFactory = parserFactory;
  }

  @Override
  public List<Worker> getAll(ContentType contentType) {
    String fileName = contentType.fileName;
    log.info("Fetching list from file {}", fileName);

    //return matching parser
    Parser parser = parserFactory.getParser(contentType);

    Reader reader = getFileHandle(fileName);
    return parser.parse(reader);
  }

  private Reader getFileHandle(String fileName) {
    InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
    return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
  }
}
