package spring.examples.servicelocator.parsers;

// factory interface
public interface ParserFactory {
    Parser getParser(ContentType contentType);
}
