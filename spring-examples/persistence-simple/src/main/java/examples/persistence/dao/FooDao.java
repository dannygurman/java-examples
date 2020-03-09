package examples.persistence.dao;

import examples.persistence.model.Foo;
import org.springframework.stereotype.Repository;

import examples.persistence.dao.IFooDao;
import examples.persistence.dao.common.AbstractHibernateDao;

@Repository
public class FooDao extends AbstractHibernateDao<Foo> implements IFooDao {

    public FooDao() {
        super();

        setClazz(Foo.class);
    }

    // API

}
