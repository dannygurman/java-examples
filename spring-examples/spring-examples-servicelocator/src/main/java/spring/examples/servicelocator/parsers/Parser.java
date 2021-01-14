package spring.examples.servicelocator.parsers;

import spring.examples.servicelocator.model.Worker;

import java.io.Reader;
import java.util.List;

// object interface
public interface Parser {

    List<Worker> parse(Reader r);

    List<Worker> parse(String payload);
}