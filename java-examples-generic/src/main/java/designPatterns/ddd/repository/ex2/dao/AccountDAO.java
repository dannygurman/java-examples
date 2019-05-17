package designPatterns.ddd.repository.ex2.dao;

import designPatterns.ddd.repository.ex2.Account;

/**
 * Created by DannyG on 05/05/2016.
 */
//The AccountDAO interface may have multiple implementations which use some kind of O/R mapper or executing plan sql queries.
 // The pattern has these advantages:

 //   It separates the domain logic that use it from any particular persistence mechanism or APIs.
//The interface methods signature are independent of the content of the Account class.
// When you add a telephone number field to the Account, you don’t need to change the AccountDAO interface nor its callers’.


public interface AccountDAO {

    Account get(String userName);
    void create(Account account);
    void update(Account account);
    void delete(String userName);

}