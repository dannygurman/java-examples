package examples.persistence.dao;

import java.util.List;

import examples.persistence.model.Foo;

public interface IFooDao {

    Foo findOne(long id);

    List<Foo> findAll();

    Foo create(Foo entity);

    Foo update(Foo entity);

    void delete(Foo entity);

    void deleteById(long entityId);

}
