package springexampes.injectcollection.beanreference.ex1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Zoo {
	List<Animal> animals;

	@Autowired
	public Zoo(List<Animal> animals) {
		this.animals = animals;
	}

	public void showAnimals() {
		animals.forEach(System.out::println);
	}
}
