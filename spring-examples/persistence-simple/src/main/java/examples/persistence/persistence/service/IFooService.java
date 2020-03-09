package examples.persistence.persistence.service;

import examples.persistence.persistence.model.Foo;

import examples.persistence.dao.common.IOperations;

public interface IFooService extends IOperations<Foo> {

    Foo retrieveByName(String name);

}
