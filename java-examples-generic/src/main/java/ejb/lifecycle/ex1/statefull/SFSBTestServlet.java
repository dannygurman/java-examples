package ejb.lifecycle.ex1.statefull;

import ejb.lifecycle.ex1.statefull.MyStatefulBean;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
The point to be noted is that this thread safety is free of cost and does not need
		any concurrency related constructs to be coded in by the bean
		developer himself (there are a few exceptions).

		As far as SFSB are concerned, the EJB container ensures that only one thread can access a bean instance at a particular time.

		In this example, we are trying to simulate concurrent access to a single instance
		of a SFSB by invoking a test Servlet via JMeter.

		The Servlet injects the bean via DI and calls a method on it.

		The SFSB method just uses a Thread.sleep() to pretend as if it's executing something.
**/
@WebServlet(name = "SFSBTestServlet", urlPatterns = {"/SFSBTestServlet"})
public class SFSBTestServlet extends HttpServlet {

	public SFSBTestServlet() {
	}

	@Inject
	MyStatefulBean mySFSB;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entered SFSBTestServlet/doGet() on " + new Date().toString() + " . Servlet instance " + this.hashCode() + " Thread : " + Thread.currentThread().getName());
		mySFSB.act();
	}

}