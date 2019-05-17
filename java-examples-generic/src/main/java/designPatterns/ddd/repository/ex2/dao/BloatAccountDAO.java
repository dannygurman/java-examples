package designPatterns.ddd.repository.ex2.dao;

import designPatterns.ddd.repository.ex2.Account;

import java.util.List;

/**
 * Created by DannyG on 05/05/2016.
 */

//The pattern has many questions unanswered however.
// What if I need to query a list of accounts having a specific last name?
// Am I allow to add a method to update only the email field of an account?
// What if I change to use a long id instead of userName?
// What exactly a DAO is responsible for?

//The problem of the DAO pattern is that it’s responsibility is not well-defined.
// Many people think it as a gateway to the database and add methods to it when they find potential
// new ways they’d like to talk to the database.
//
// Hence it is not uncommon to see a DAO getting bloated like the one below.


//Mocking the DAO interface becomes harder in unit test.//
// I need to implement more methods in the DAO even my particular test scenario only use one of them.

 //The DAO interface becomes more coupled to the fields of Account object.
// I have to change the interface and all its implementations if I change the type of fields those stored in Account.

//To make things even worse, I added two additional update methods to the DAO as well.
// They are the direct result of two new use cases which update different subset of the fields of an account.
//
// They seem like harmless optimisation and fit into the AccountDAO interface if I naively treat the interface as a
// gateway to the persistence store. Again, the DAO pattern and its class
// name “AccountDAO” is too loosely defined to stop me doing this.


public interface BloatAccountDAO {

    Account get(String userName);
    void create(Account account);
    void update(Account account);
    void delete(String userName);

    List getAccountByLastName(String lastName);
    List getAccountByAgeRange(int minAge, int maxAge);
    void updateEmailAddress(String userName, String newEmailAddress);
    void updateFullName(String userName, String firstName, String lastName);

}