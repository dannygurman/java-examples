package mockmvcexample.controllers;

import lombok.extern.slf4j.Slf4j;
import mockmvcexample.AbstractControllerTest;
import mockmvcexample.config.MyServiceTestConfig;
import mockmvcexample.controlllers.BookController;
import mockmvcexample.services.BookService;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ImportAutoConfiguration({MyServiceTestConfig.class})
@ContextConfiguration(classes = {BookController.class, BookService.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class BookControllerTest extends AbstractControllerTest {

}
