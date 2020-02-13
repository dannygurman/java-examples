package loadimplementors;

import java.util.List;

import loadimplementors.animals.Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Zoo {
	List<Animal> animals;

	@Autowired
	public Zoo(List<Animal> runners) {
		this.animals = runners;
	}

	public void showAnimals() {
		animals.forEach(System.out::println);
	}
}
