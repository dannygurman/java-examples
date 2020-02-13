package loadimplementors.animals;

import org.springframework.stereotype.Service;

@Service
public class Libi extends Dog {

	@Override
	public String toString() {
		return "Libi";
	}

}
