package mockmvcexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import mockmvcexample.config.MyServiceTestConfig;
import mockmvcexample.controlllers.exceptionhandler.MyServiceExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.BeforeClass;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.mock;

//At its core, the TestContext framework allows you to annotate test classes with @ContextConfiguration to specify which configuration
//files to use to load the ApplicationContext for your test.
//By default the ApplicationContext is loaded using the GenericXmlContextLoader which loads a context
//from XML Spring configuration files.
//You can then access beans from the ApplicationContext by annotating fields in your test class with @Autowired, @Resource, or @Inject.

@ImportAutoConfiguration({MyServiceTestConfig.class})
@ContextConfiguration(classes = {ObjectMapper.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations="classpath:application-test.properties")
@WithUserDetails(userDetailsServiceBeanName = "testUserDetailsService")
public class AbstractControllerTest {

    protected final static ResultMatcher MOCK_MVC_EXPECTED_RESULT_STATUS_OK = MockMvcResultMatchers.status().isOk();

    private final static Authentication authentication = mock(Authentication.class);

    private  HttpMessageConverter[] messageConverters =
        {new MappingJackson2HttpMessageConverter(), new ByteArrayHttpMessageConverter() };

    @Value("${v1API}")
    protected String v1API;

    protected MockMvc mvc;



    @BeforeClass
    public static void mockSpringSecurity() {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    protected void setUp(Object... controllers) {
        mvc = standaloneSetup(controllers)
            .addPlaceholderValue("v1API", v1API)
            .setMessageConverters(messageConverters)
            .setControllerAdvice(new MyServiceExceptionHandler())
            .build();
    }


    protected MockHttpServletResponse performAndValidateGetRequest(String url, ResultMatcher resultMatcher) throws Exception {
        MvcResult result =  performAndValidateGetRequestInternal(url, resultMatcher);
        return result.getResponse();
    }

    protected Object performAndValidateGetRequestAsyncResultObject(String url, ResultMatcher resultMatcher) throws Exception {
        MvcResult result =  performAndValidateGetRequestInternal(url, resultMatcher);
        return result.getAsyncResult();
    }

    protected MvcResult performAndValidateGetRequestInternal(String url, ResultMatcher resultMatcher) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).with(testSecurityContext());
        return performAndValidateRequestInternal( requestBuilder,  resultMatcher);
    }


    protected MockHttpServletResponse performAndValidatePostRequest(String url, MediaType mediaType,
                                                                    String content, ResultMatcher resultMatcher) throws Exception {
        MvcResult result = performAndValidatePostRequestInternal(url, mediaType,content, null, resultMatcher);
        return result.getResponse();
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
