package jparepositoryexample.repository;

import java.util.List;

import jparepositoryexample.entity.Book;

public interface BookRepositoryCustom {

    List<Book> findBooksByAuthorNameAndTitle(String authorName, String title);
    
}
