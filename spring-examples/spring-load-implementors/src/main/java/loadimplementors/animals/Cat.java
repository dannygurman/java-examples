package loadimplementors.animals;

import org.springframework.stereotype.Service;

@Service
public class Cat implements Animal {

	@Override
	public String toString() {
		return "Cat";
	}

}
