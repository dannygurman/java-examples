package designPatterns.ddd.repository.ex2.repository;

import designPatterns.ddd.repository.ex2.Account;

/**
 * Created by DannyG on 05/05/2016.
 */
//We can even take a step further to composite Specifications together with
// ConjunctionSpecification and DisjunctionSpecification to perform more complicate queries.

public class AccountSpecificationByUserName implements AccountSpecification, HibernateSpecification {

    private String desiredUserName;

    public AccountSpecificationByUserName(String desiredUserName) {
        super();
        this.desiredUserName = desiredUserName;
    }

    @Override
    public boolean specified(Account account) {
        return account.hasUseName(desiredUserName);
    }

    @Override
    public HibernateCriterion toCriteria() {
        return HibernateRestriction.eq("userName", desiredUserName);
    }

}