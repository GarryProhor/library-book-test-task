package book.service;

import book.model.Book;
import book.util.CustomResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    CustomResponse<Book> findOne(Long id);

    CustomResponse<Book> findAllBooks();

    CustomResponse<Book> addBook(Book book);

    CustomResponse<Book> update(Book book);

    void delete(Long id);

    CustomResponse<Book> findByAuthorName(String author);

    List<Book> findAll(boolean sortByYear);


    List<Book> searchByTitle(String query);

    List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear);
}
