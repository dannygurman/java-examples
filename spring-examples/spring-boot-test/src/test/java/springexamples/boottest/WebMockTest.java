package springexamples.boottest;

//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;


//The service dependency will be automatically injected by Spring into the controller (because of the constructor signature).
// To test this controller with @WebMvcTest you can do this:

//We use @MockBean to create and inject a mock for the GreetingService
// (if you don not do this the application context cannot start),
// and we set its expectations using Mockito

// In an application with multiple controllers you can even ask for just one to be instantiated,
// using, for example @WebMvcTest(GreetingController.class)

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class WebMockTest {

    private static final String MOCK_RESULT_STRING = "Hello Mock";
    @Autowired
    private MockMvc mockMvc;

    @MockBean  //- from org.springframework.boot.test.mock.mockito
    private GreetingService service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {

        Mockito.when(service.greet()).thenReturn(MOCK_RESULT_STRING);//Could be import statically -

        RequestBuilder requestBuilder = get("/"+GreetingController.GREETING_URL);
        ResultHandler resultHandler = print();
        ResultMatcher resultMatcher1 =  status().isOk();

        //Hamcrest - Matchers that can be combined to create flexible expressions of intent
        org.hamcrest.Matcher<String> containMockResultMatcher = org.hamcrest.Matchers.containsString(MOCK_RESULT_STRING);
        ResultMatcher resultMatcher2 = content().string(containMockResultMatcher);

        this.mockMvc.perform(requestBuilder).andDo(resultHandler).andExpect(resultMatcher1).andExpect(resultMatcher2);
    }




}
