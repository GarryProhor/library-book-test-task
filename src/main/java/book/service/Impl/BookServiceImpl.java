package book.service.Impl;

import book.model.Book;
import book.repository.BookRepository;
import book.service.BookService;
import book.util.CustomResponse;
import book.util.CustomStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository booksRepository;

    @Autowired
    public BookServiceImpl(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Cacheable("books")
    public CustomResponse<Book> findOne(Long id) {
        Book book = booksRepository.findById(id).orElseThrow();
        return new CustomResponse<>(Stream.of(book).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> findAllBooks(){
        List<Book> books = booksRepository.findAll();
        return new CustomResponse<>(books, CustomStatus.SUCCESS);
    }


    public CustomResponse<Book> findByAuthorName(String author){
        Book book = booksRepository.findByAuthor(author).orElseThrow();
        return new CustomResponse<>(Stream.of(book).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }

    @Transactional
    public CustomResponse<Book> addBook(Book book) {
        Book newBook = booksRepository.save(book);
        return new CustomResponse<>(Stream.of(newBook).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }

    @Transactional
    public CustomResponse<Book> update(Book book) {
        Book bookFound = booksRepository.findById(book.getId()).orElseThrow();
        bookFound.setTitle(book.getTitle());
        bookFound.setAuthor(book.getAuthor());
        bookFound.setYear(book.getYear());
        Book bookUpdate = booksRepository.save(bookFound);
        return new CustomResponse<>(Stream.of(bookUpdate).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }

    @Transactional
    public void delete(Long id) {
        booksRepository.findById(id).orElseThrow();
        booksRepository.deleteById(id);
        log.info("Книга с id - " + id + " успешно удалена");
    }

    //Продолжение следует... Наверное будет пагинация:)
    public List<Book> searchByTitle(String query) {
        return booksRepository.findByTitleStartingWith(query);
    }
    public List<Book> findAll(boolean sortByYear) {
        return sortByYear ? booksRepository.findAll(Sort.by("years")) : booksRepository.findAll();
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        return sortByYear ? booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("years"))).getContent()
               : booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }
}
