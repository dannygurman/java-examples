package spring.examples.servicelocator.services;

import spring.examples.servicelocator.model.Worker;
import spring.examples.servicelocator.parsers.ContentType;
import java.util.List;

public interface AvengersService {

  List<Worker> getAll(ContentType contentType);
}
