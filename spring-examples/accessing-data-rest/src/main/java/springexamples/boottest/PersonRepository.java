package springexamples.boottest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//PagingAndSortingRepository - Extension of CrudRepository to provide additional methods to retrieve
// entities using the pagination and sorting abstraction.

//Adding @RepositoryRestResource annotation to direct Spring MVC to create RESTful endpoints at /people

//Spring Data REST exposes a collection resource named after the uncapitalized,
// pluralized version of the domain class the exported repository is handling.
//
// Both the name of the resource and the path can be customized using the
// @RepositoryRestResource on the repository interface.

//@RepositoryRestResource is not required for a repository to be exported.
// It is only used to change the export details, such as using /people instead of the default value of /persons.

// @RepositoryRestResource  against  @RESTController
// RepositoryRestResource creates a HATEOAS service with Spring JPA.
// As you can see here adding this annotation and linking it to your Pojo you have a fully functional HATEOAS service without having to implement
// the repository method or the REST service methods
//If you add the @RestController then you have to implement each method that you want to expose
// on your own and also is does not export this to a HATEOAS format

//collectionResourceRel - The rel value to use when generating links to the collection resource.

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	List<Person> findByLastName(@Param("name") String name);

}
