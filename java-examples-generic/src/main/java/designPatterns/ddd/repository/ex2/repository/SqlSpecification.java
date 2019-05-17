package designPatterns.ddd.repository.ex2.repository;

/**
 * Created by DannyG on 05/05/2016.
 */
//To work with a sql backed AccountRepository implementation,
// my specifications need to implement SqlSpecification interface as well.

public interface SqlSpecification {
    String toSqlClauses();
}