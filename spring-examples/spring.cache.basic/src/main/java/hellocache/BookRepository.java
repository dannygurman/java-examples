package hellocache;

/**
 * Created by dannyg on 1/2/2018.
 */
public interface BookRepository {
    Book getByIsbn(String isbn);

}
