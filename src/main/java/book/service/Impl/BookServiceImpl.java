package book.service.Impl;

import book.exception.BookNotFoundException;
import book.model.Book;
import book.repository.BookRepository;
import book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository booksRepository;

    @Autowired
    public BookServiceImpl(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Cacheable("books")
    public Book findOne(Long id) {
        log.info("Поиск книги с id - " + id);
        return booksRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }


    public List<Book> findAllBooks(){
        log.info("Поиск всех книг");
        return booksRepository.findAll();
    }

    public List<Book> searchByTitle(String query) {
        return booksRepository.findByTitleStartingWith(query);
    }

    @Transactional
    public Book save(Book book) {
        log.info("Книга " + book.getTitle() + " сохранена. Автор книги " + book.getAuthor());
        return booksRepository.save(book);
    }

    @Transactional
    public Book update(Long id) {
        log.info("Редактирование книги - " + id);
        Book book = booksRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        return booksRepository.save(book);
    }

    @Transactional
    public void delete(Long id) {
        booksRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        booksRepository.deleteById(id);
        log.info("Книга с id - " + id + " успешно удалена");
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(Sort.by("years"));
        else
            return booksRepository.findAll();
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("years"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }
}
