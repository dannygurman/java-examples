package designPatterns.ddd.repository.ex2.repository;

import designPatterns.ddd.repository.ex2.Account;

import java.util.List;

/**
 * Created by DannyG on 05/05/2016.
 */
public interface AccountRepository {

    //A better pattern is Repository. Eric Evans gave it a precise description in his book [DDD],
    // “A Repository represents all objects of a certain type as a conceptual set.
    // It acts like a collection, except with more elaborate querying capability.


    //The “add” and “update” methods look identical to the save and update method of my original AccountDAO. The “remove” method differs
    // to the DAO’s delete method by taking an Account object rather than the userName (Account’s identifier).
    //
    // It you think the Repository as a Collection, this change makes a lot of sense.
    // You avoid to expose the type of Accounts identity to the Repository interface.
    //
    // It makes my life easy if I’d like to use long values to identify the accounts.

    void addAccount(Account account);
    void removeAccount(Account account);
    void updateAccount(Account account); // Think it as replace for set

    List query(AccountSpecification specification);

}
