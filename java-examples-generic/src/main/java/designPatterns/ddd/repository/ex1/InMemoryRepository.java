package designPatterns.ddd.repository.ex1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by DannyG on 02/05/2016.
 */
// a generic in-memory implementation for the repository:
public abstract class InMemoryRepository<T extends Identity> implements Repository<T> {

    private Set<T> entities = new HashSet<>();

    @Override
    public Set<T> get() {
        return Collections.unmodifiableSet(entities);
    }

    @Override
    public void persist(T entity) {
        entities.add(entity);
    }

    @Override
    public void remove(T entity) {
        entities.remove(entity);
    }
}