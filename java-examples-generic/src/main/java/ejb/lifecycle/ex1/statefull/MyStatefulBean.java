package ejb.lifecycle.ex1.statefull;

/**
 * Created by DannyG on 10/06/2015.
 */
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;

@Stateful
public class MyStatefulBean {

	public MyStatefulBean() {
	}

	public void act() {
		System.out.println("Entered MyStatefulBean/act() on " + new Date().toString() + " . SFSB instance " + this.hashCode() + " Thread : " + Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Logger.getLogger(MyStatefulBean.class.getName()).log(Level.SEVERE, null, ex);
		}

		System.out.println("Exit MyStatefulBean/act() on " + new Date().toString() + " . SFSB instance " + this.hashCode() + " Thread : " + Thread.currentThread().getName());

	}
}
