package designPatterns.ddd.repository.ex2.repository;

import designPatterns.ddd.repository.ex2.Account;

/**
 * Created by DannyG on 05/05/2016.
 */
//One common implementation of a criterion is Specification pattern.
// A specification is a simple predicate that takes a domain object and returns a boolean.


public interface AccountSpecification {

    boolean specified(Account account);

}
