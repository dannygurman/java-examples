package hibernateexample.util;

import java.util.Properties;

import hibernateexample.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
public class HibernateUtil {

    private static final String DRIVER_URL = "dbc:h2:mem:testdb";
   // The in-memory databases are volatile, by default, and all stored data will be lost when we restart the application.
    // In this case, data is written in temporary memory and as soon as JVM is stopped, data is flushed.
    //To have a persistent data store, which is capable to storing data between application start/stop, we should store the data in files.
   // For this change the spring.datasource.url property.
    private static final String DRIVER_URL_2 = "jdbc:h2:file:./sample";
    private static final String DRIVER_CLASSNAME = "org.h2.Driver";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final String DIALECT = "org.hibernate.dialect.H2Dialect";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER_CLASSNAME);
                settings.put(Environment.URL, DRIVER_URL_2);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, DIALECT);
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                // HBM is a short name for Hibernate Mapping
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Student.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
