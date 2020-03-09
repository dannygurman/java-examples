package examples.persistence.persistence.service;

import com.baeldung.spring.data.persistence.model.Foo;

import examples.persistence.dao.common.IOperations;

public interface IFooService extends IOperations<Foo> {

    Foo retrieveByName(String name);

}
