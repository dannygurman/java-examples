package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//We can narrow down the tests to just the web layer by using @WebMvcTest

//The test assertion is the same as in the previous case, but here Spring Boot is only instantiating the web layer,
// not the whole context.
//
// In an application with multiple controllers you can even ask for just one to be instantiated,
// using, for example @WebMvcTest(HomeController.class)

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class WebLayerTest {

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
