package ejb.lifecycle.ex1.stateless;

/**
 * Created by DannyG on 10/06/2015.
 */
import java.io.IOException;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * What about Stateless Beans?

 These beans are inherently thread safe. Why?

 It is because by default, the container makes sure that each new request is served by a new instance of the bean.

 Remember, that a client can obtain a reference to stateless bean in 3 possible ways � DI, JNDI or via a remote interface (RMI).
 In all these cases, it is the container (proxy) which intercepts the call   thus,
 even if multiple threads are seemingly accessing the same bean instance, its actually not the same one..
 */
@WebServlet(name = "SLSBTestServlet", urlPatterns = {"/SLSBTestServlet"})
public class SLSBTestServlet extends HttpServlet {

	@Inject
	MyStatelessBean slsb;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Entered SLSBTestServlet/doGet() on " + new Date().toString() + " . Servlet instance " + this.hashCode() + " Thread : " + Thread.currentThread().getName());

		slsb.act();

	}

}
