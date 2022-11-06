package book.service;

import book.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    Book findOne(Long id);

    List<Book> findAllBooks();

    Book save(Book book);

    Book update(Book book);

    void delete(Long id);

    List<Book> findAll(boolean sortByYear);


    List<Book> searchByTitle(String query);

    List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear);
}
