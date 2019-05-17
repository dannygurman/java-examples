package hello;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    //Another useful approach is to NOT start the server at all, but test only the layer below that,
    // where Spring handles the incoming HTTP request and hands it off to your controller.
    //
    // That way, almost the full stack is used, and your code will be called exactly the same way as if it was processing a real HTTP request,
    // but without the cost of starting the server.

    // To do that we will use Spring’s MockMvc, and we can ask for that to be injected for us by
    // using the @AutoConfigureMockMvc annotation on the test case:

   // In this test, the full Spring application context is started, but without the server

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {

        RequestBuilder requestBuilder = get("/");
        ResultHandler resultHandler = print();
        ResultMatcher resultMatcher1 =  status().isOk();
        ResultMatcher resultMatcher2 = content().string(containsString("Hello World"));

        this.mockMvc.perform(requestBuilder).andDo(resultHandler).andExpect(resultMatcher1).andExpect(resultMatcher2);
    }
}
