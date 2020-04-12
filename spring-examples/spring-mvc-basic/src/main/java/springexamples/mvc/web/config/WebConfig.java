package springexamples.mvc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "springexamples.mvc.web.controllers" })
// @EnableWebMvc- will set up the basic support we need for an MVC project,
// such as registering controllers and mappings, type converters, validation support,
// message converters and exception handling.
//If we want to customize this configuration, we need to implement the WebMvcConfigurer interface:
public class WebConfig implements WebMvcConfigurer {

    //In this example, we've registered a ViewResolver bean that will return .jsp views
    // from the /WEB-INF/view directory.
//   Very important here is that we can register view controllers that create a
//   direct mapping between the URL and the view name using the ViewControllerRegistry.
//   This way, there's no need for any Controller between the two.
   // If we want to also define and scan controller classes,
    // we can add the @ComponentScan annotation with the package that contains the controllers

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }


  /*  @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/src/main/resources/view/");
        bean.setSuffix(".jsp");

        return bean;
    }*/
}
