package spring.examples.servicelocator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.examples.servicelocator.model.Worker;
import spring.examples.servicelocator.parsers.ContentType;
import spring.examples.servicelocator.services.WorkersService;

/**
 * http://localhost:9000/api/workers/json
 * http://localhost:9000/api/workers/csv
 * http://localhost:9000/api/workers/xml
 */
@RestController
@RequestMapping(value = "/api/workers", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkersController {

  private WorkersService service;

  @Autowired
  public WorkersController(WorkersService service) {
    this.service = service;
  }

  @GetMapping("/csv")
  public ResponseEntity<List<Worker>> getAllFromCsv() {
    return ResponseEntity.ok().body(service.getAll(ContentType.CSV));
  }

  @GetMapping("/json")
  public ResponseEntity<List<Worker>> getAllFromJson() {
    return ResponseEntity.ok().body(service.getAll(ContentType.JSON));
  }

  @GetMapping("/xml")
  public ResponseEntity<List<Worker>> getAllFromXml() {
    return ResponseEntity.ok().body(service.getAll(ContentType.XML));
  }
}
