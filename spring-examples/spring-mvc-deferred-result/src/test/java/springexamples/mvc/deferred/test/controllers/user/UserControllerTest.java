package springexamples.mvc.deferred.test.controllers.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import springexamples.mvc.deferred.config.TaskExecutorsConfiguration;
import springexamples.mvc.deferred.controllers.UserController;
import springexamples.mvc.deferred.dtos.User;
import springexamples.mvc.deferred.services.UserService;
import springexamples.mvc.deferred.test.controllers.AbstractControllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

@ImportAutoConfiguration({TaskExecutorsConfiguration.class})
@ContextConfiguration(classes = {UserController.class, UserService.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class UserControllerTest extends AbstractControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    private String userApiMainUrl = "/users/";

    @Before
    public void setup() {
        super.setUp(userController);
    }

    @Test
    public void given_userCreatRequest_then_returnOkHttpCode_ExpectedResponse() throws Exception {
       final String username = " danny";
        final String userCreatApiUrl = this.userApiMainUrl + username;

        final User user = User.builder().name(username).build();
        //Mockito.any() - could be use as "wildcard" for parameter
        Mockito.doReturn(user).when(userService).createUserLongOperation(eq(username));

        Object resultObject =
            performAndValidatePostRequestAsync(userCreatApiUrl, MediaType.ALL,"",  MOCK_MVC_EXPECTED_RESULT_STATUS_OK);

        //Verify repository findBookById was called with right parameter-
        Mockito.verify(userService).createUserLongOperation(eq(username));

        assertTrue(resultObject instanceof User);

        User returnedUser = (User) resultObject;

        assertEquals(user, returnedUser);
    }

}
