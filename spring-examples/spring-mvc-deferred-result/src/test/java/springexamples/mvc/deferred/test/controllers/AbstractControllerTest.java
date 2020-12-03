package springexamples.mvc.deferred.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ContextConfiguration(classes = {ObjectMapper.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class AbstractControllerTest {

    protected final static ResultMatcher MOCK_MVC_EXPECTED_RESULT_STATUS_OK = MockMvcResultMatchers.status().isOk();

    protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private  HttpMessageConverter[] messageConverters =
        {new MappingJackson2HttpMessageConverter(), new ByteArrayHttpMessageConverter() };

    protected MockMvc mvc;

    protected void setUp(Object... controllers) {
        mvc = standaloneSetup(controllers)
            .setMessageConverters(messageConverters)
            .build();
    }



    protected Object performAndValidatePostRequestAsync(String url, MediaType mediaType,
                                                                    String content, ResultMatcher resultMatcher) throws Exception {
        MvcResult result = performAndValidatePostRequestInternal(url, mediaType,content, null, resultMatcher);
    //Using get async instead of result.getResponse
        return result.getAsyncResult();
    }


    private MvcResult performAndValidatePostRequestInternal(String url, MediaType mediaType,
                                                            String contentStr,
                                                            byte [] contentBin,
                                                            ResultMatcher resultMatcher) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.
            post(url).
            with(testSecurityContext())
            .contentType(mediaType);

        if (contentStr != null) {
            requestBuilder = requestBuilder.content(contentStr);
        } else if (contentBin != null) {
            requestBuilder = requestBuilder.content(contentBin);
        }

        return performAndValidateRequestInternal( requestBuilder,  resultMatcher);
    }

    private MvcResult performAndValidateRequestInternal(MockHttpServletRequestBuilder requestBuilder, ResultMatcher resultMatcher) throws Exception {
        ResultActions resultActions = mvc.perform(requestBuilder);
        //printDetails)
        resultActions = resultActions.andDo(MockMvcResultHandlers.print());
        return  resultActions.andExpect(resultMatcher).andReturn();
    }
}
