package loadimplementors;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(SpringContext.class);

		Zoo zoo = context.getBean(Zoo.class);
		zoo.showAnimals();
	}
}
