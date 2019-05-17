package designPatterns.ddd.repository.ex1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by DannyG on 02/05/2016.
 *
 * //The cool thing is: As we have the Identity base class we can define
 * a generic interface for the repository functionality:
 * This way we can have repositories for many entity types that share a common interface for persistence.
 * The given example only defines some base functionality but we could extend the interface a little bit.
 * For example we may want to persist/remove many entities at once.
 * Or we like to make more specific queries to get entity instances that match a given condition.
 *
 * Now there are only three methods that needs to be implemented: Set<T> get(), void persist(T entity) and void remove(T entity).
 * For all other methods there are default implementations that are using these three methods directly or indirectly.
 * An interesting detail is that we can use the String getId() method of the Identity class because we
 * have the type constraint for T to be a subclass of Identity.

 Of cause the default implementations may not be optimal when it comes to performance.
 But every implementor can choose to overwrite the default implementations with a more advanced implementation that makes better use
 of the underlaying persistence technique.
 */
public interface Repository<T extends Identity> {

    default Optional<T> get(String id) {
        return get()
                .stream ()
                .filter(entity -> entity.getId().equals(id))
                .findAny();
    }

    default Set<T> get(Predicate<T> predicate) {
        return get()
                .stream()
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    Set<T> get();

    void persist(T entity);

    default void persist(T... entities) {
        persist(Arrays.asList(entities));
    }

    default void persist(Collection<T> entities) {
        entities.forEach(this::persist);
    }

    void remove(T entity);

    default void remove(T... entities) {
        remove(Arrays.asList(entities));
    }

    default void remove(Collection<T> entities) {
        entities.forEach(this::remove);
    }

    default void remove(String id) {
        remove(entity -> entity.getId().equals(id)); // predicate to match the id
    }

    default void remove(Predicate<T> predicate) {
        get(predicate).forEach(this::remove);
    }
}