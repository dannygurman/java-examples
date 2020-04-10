package springexampes.injectcollection.beanreference.ex1;

import org.springframework.stereotype.Service;

@Service
public class Buldog extends Dog {

	@Override
	public String toString() {
		return "Buldog []";
	}

}
