package spring.examples.servicelocator.factory;

import spring.examples.servicelocator.config.ContentType;
import spring.examples.servicelocator.parsers.Parser;

// factory interface - No explicit implementation !
public interface ParserFactory {
    Parser getParser(ContentType contentType);
}
