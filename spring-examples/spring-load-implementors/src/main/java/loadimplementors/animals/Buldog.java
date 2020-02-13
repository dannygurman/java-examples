package loadimplementors.animals;

import org.springframework.stereotype.Service;

@Service
public class Buldog extends Dog {

	@Override
	public String toString() {
		return "Buldog []";
	}

}
