package ejb.lifecycle.ex1.stateless;

/**
 * Created by DannyG on 10/06/2015.
 */

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/*
Stateful Session Beans: Concurrency Management

Thread safety is one of the core features of EJBs.


*/

@Stateless
public class MyStatelessBean {

	public void act() {

		System.out.println("Entered MyStatelesslBean/act() on " + new Date().toString() + " . SLSB instance " + this.hashCode() + " Thread : " + Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Logger.getLogger(MyStatelessBean.class.getName()).log(Level.SEVERE, null, ex);
		}

		System.out.println("Exit MyStatelesslBean/act() on " + new Date().toString() + " . SLSB instance " + this.hashCode() + " Thread : " + Thread.currentThread().getName());
	}
}