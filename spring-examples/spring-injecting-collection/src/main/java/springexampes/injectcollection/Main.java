package springexampes.injectcollection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springexampes.injectcollection.beanreference.ex1.Zoo;
import springexampes.injectcollection.beanreference.ex2.CollectionContainerBean;
import springexampes.injectcollection.collections.ex1.CollectionsBean1;
import springexampes.injectcollection.collections.ex2.CollectionsBean2;
import springexampes.injectcollection.collections.ex3.CollectionsBean3;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(SpringContext.class);

		//springexampes.injectcollection.collections.ex1
		CollectionsBean1 collectionsBean1 = context.getBean (CollectionsBean1.class);
		collectionsBean1.printNameList();

		//springexampes.injectcollection.collections.ex2
		CollectionsBean2 collectionsBean2 = context.getBean (CollectionsBean2.class);
		collectionsBean2.printNameSet();

		//springexampes.injectcollection.collections.ex3
		CollectionsBean3 collectionsBean3 = context.getBean (CollectionsBean3.class);
		collectionsBean3.printNameMap();

		//beanreference.ex1
		Zoo zoo = context.getBean(Zoo.class);
		zoo.showAnimals();

		//beanreference.ex2
		CollectionContainerBean collectionContainerBean =context.getBean(CollectionContainerBean.class);
		//pring according to @Order in BeanReferenceCollectionConfig
		collectionContainerBean.printBeanList();


	}
}
