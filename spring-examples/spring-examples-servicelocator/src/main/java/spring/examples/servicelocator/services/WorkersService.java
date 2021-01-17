package spring.examples.servicelocator.services;

import spring.examples.servicelocator.model.Worker;
import spring.examples.servicelocator.config.ContentType;
import java.util.List;

public interface WorkersService {

  List<Worker> getAll(ContentType contentType);
}
