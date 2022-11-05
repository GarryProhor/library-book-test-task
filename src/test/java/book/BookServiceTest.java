package book;

import book.model.Book;
import book.service.BookService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceTest {
    @Mock
    BookService bookService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllBooksTest() {
        int date = new Date().getYear();
        List<Book> list = new ArrayList<>();
        list.add(new Book(1L, "Title 1", "Author 1", date));
        list.add(new Book(2L, "Title 2", "Author 2", date));

        when(bookService.findAllBooks()).thenReturn(list);

        List<Book> books = bookService.findAllBooks();

        assertEquals(2, books.size());
        verify(bookService, times(1)).findAllBooks();
    }

    @Test
    public void getBookByIdTest() {

        int date = new Date().getYear();
        Book book = new Book(1L, "Title 1", "Author 1", date);

        when(bookService.findOne(1L)).thenReturn(book);

        Book book1 = bookService.findOne(1L);

        verify(bookService, times(1)).findOne(book1.getId());
    }

    @Test
    public void createBookTest() {

        int date = new Date().getYear();
        Book book = new Book(1L, "Title 1", "Author 1", date);

        Book bookCreated = bookService.save(book);

        verify(bookService, times(1)).save(bookCreated);
    }
    @Test
    public void deleteBookTest() {

        int date = new Date().getYear();
        Book book = new Book(1L, "Title 1", "Author 1", date);

        when(bookService.findOne(1L)).thenReturn(book);

        verify(bookService, times(1)).delete(1L);
    }

}
