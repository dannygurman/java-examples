package springexampes.injectcollection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springexampes.injectcollection.beanreference.ex1.Zoo;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(SpringContext.class);

		//beanreference.ex1
		Zoo zoo = context.getBean(Zoo.class);
		zoo.showAnimals();
	}
}
