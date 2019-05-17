package guavacache;

/**
 * Created by dannyg on 1/2/2018.
 */
public class Book {

    private String isbn;
    private String title;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "guavacache.Book{" + "isbn='" + isbn + '\'' + ", title='" + title + '\'' + '}';
    }
}
