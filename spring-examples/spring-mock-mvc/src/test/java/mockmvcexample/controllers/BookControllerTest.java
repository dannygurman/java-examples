package mockmvcexample.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mockmvcexample.AbstractControllerTest;
import mockmvcexample.config.MyServiceTestConfig;
import mockmvcexample.controlllers.BookController;
import mockmvcexample.dao.BookRepository;
import mockmvcexample.model.Book;
import mockmvcexample.services.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

@ImportAutoConfiguration({MyServiceTestConfig.class})
@ContextConfiguration(classes = {BookController.class, BookService.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class BookControllerTest extends AbstractControllerTest {

    protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected String bookApiMainUrl;


    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookController bookController;


    @Before
    public void setup() {
        super.setUp(bookController);
        this.bookApiMainUrl = v1API + "/books/";
    }

    @Test
    public void given_bookGetSpecificRequest_then_returnOkHttpCode_ExpectedResponse() throws Exception {
        final String bookId1 = "id1";
        final String bookName1 = "bookname1";

        final String bookApiFindBookUrl = this.bookApiMainUrl + bookId1;

        final Book book1 = Book.builder().id(bookId1).name(bookName1).build();
         //Mockito.any() - couls be use as "wildcard" for parameter
        Mockito.doReturn(book1).when(bookRepository).findBookById(eq(bookId1));

        MockHttpServletResponse apiResponseForGetBook =
            performAndValidateGetRequest(bookApiFindBookUrl,
                MOCK_MVC_EXPECTED_RESULT_STATUS_OK);

        //Verify repository findBookById was called with right parameter-
        Mockito.verify(bookRepository).findBookById(eq(bookId1));

        String resultBodyStr = apiResponseForGetBook.getContentAsString();
        Book returnedBook =   OBJECT_MAPPER.readValue(resultBodyStr, Book.class);
        assertEquals(book1, returnedBook);
    }

    @Test
    public void given_bookSaveRequest_then_returnOkHttpCode_and_repositoryCalled() throws Exception {
        final String bookId1 = "id1";
        final String bookName1 = "bookname1";

        final Book book1 = Book.builder().id(bookId1).name(bookName1).build();

        String saveBookRequestJson = OBJECT_MAPPER.writeValueAsString(book1);

        MockHttpServletResponse saveBookApiResponse =
            performAndValidatePostRequest(this.bookApiMainUrl,
                MediaType.APPLICATION_JSON,
                saveBookRequestJson,
                MOCK_MVC_EXPECTED_RESULT_STATUS_OK);

        //Verify repository addBook was called-
        Mockito.verify(bookRepository).addBook(Mockito.any());
    }

}
