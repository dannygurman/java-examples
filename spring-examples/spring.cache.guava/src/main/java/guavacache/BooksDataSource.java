package guavacache;

/**
 * Created by dannyg on 1/3/2018.
 */
public interface BooksDataSource {

    Book getByIsbn(String isbn);
}
