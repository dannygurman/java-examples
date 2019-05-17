package designPatterns.ddd.repository.ex1;

import java.util.Optional;
import java.util.Set;

/**
 * Created by DannyG on 02/05/2016.
 */
//Now we can define an interface for our Repository
    //For the person it could look like this:

public interface PersonRepository {

    /**
     * @return the person with the given id.
     */
    Optional<Person> get(String id);

    /**
     * @return a collection of all persons.
     */
    Set<Person> get();

    /**
     * Persist the given person instance.
     */
    void persist(Person person);

    /**
     * Remove the given person instance.
     */
    void remove(Person person);
}