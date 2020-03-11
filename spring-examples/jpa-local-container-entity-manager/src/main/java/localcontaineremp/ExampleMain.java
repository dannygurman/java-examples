package localcontaineremp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class ExampleMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);

        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);

        try {
            nativeQuery(emf, "SHOW TABLES");
            nativeQuery(emf, "SHOW COLUMNS from Person");
            Person person = new Person();
            person.setName("Joe");
            persistEntity(emf, person);
            nativeQuery(emf, "Select * from Person");
        } finally {
            emf.close();
        }
    }

    private static void persistEntity(EntityManagerFactory emf, Person person) {
        System.out.println("------------");
        System.out.println("Persisting person: " + person);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

    private static void nativeQuery(EntityManagerFactory emf, String s) {
        EntityManager em = emf.createEntityManager();
        System.out.printf("---------------------------%n'%s'%n", s);
        Query query = em.createNativeQuery(s);
        List list = query.getResultList();
        for (Object o : list) {
            if (o instanceof Object[]) {
                System.out.println(Arrays.toString((Object[]) o));
            } else {
                System.out.println(o);
            }
        }
        em.close();
    }
}