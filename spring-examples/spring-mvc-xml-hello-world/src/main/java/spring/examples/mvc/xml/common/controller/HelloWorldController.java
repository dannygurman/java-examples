package spring.examples.mvc.xml.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
//Spring comes with many Controllers,
// normally, you just need to extend the AbstractController,
// and override the handleRequestInternal() method.

public class HelloWorldController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//To identify which view should return back to the user, in this example HelloWorldPage.jsp will be returned
		ModelAndView model = new ModelAndView("HelloWorldPage");

		//Add a “hello world” string into a model named “msg”, later you can use EL ${msg} to display the “hello world” string.
		//EL - Expression Language
		//JSP - JavaServer Pages
		//see HelloWorldPage.jsp
		model.addObject("msg", "hello world danny");

		return model;
	}

}