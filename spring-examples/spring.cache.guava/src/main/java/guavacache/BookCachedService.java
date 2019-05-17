package guavacache;

/**
 * Created by dannyg on 1/2/2018.
 */
public interface BookCachedService {
    Book getByIsbn(String isbn);
}
