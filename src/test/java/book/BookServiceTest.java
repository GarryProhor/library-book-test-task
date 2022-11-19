package book;

import book.model.Book;
import book.service.BookService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Ignore
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
        List<Book> list = new ArrayList<>();
        list.add(new Book(1L, "Title 1", "Author 1", 2022));
        list.add(new Book(2L, "Title 2", "Author 2", 2021));

        when(bookService.findAllBooks()).thenReturn(list);

        List<Book> books = bookService.findAllBooks();

        assertEquals(2, books.size());
        verify(bookService, times(1)).findAllBooks();
    }

    @Test
    public void getBookByIdTest() {
        Book book = new Book(1L, "Title 1", "Author 1", 2022);

        when(bookService.findOne(1L)).thenReturn(book);

        Book book1 = bookService.findOne(1L);

        verify(bookService, times(1)).findOne(book1.getId());
    }

    @Test
    public void createAndUpdateBookTest() {
        Book book = new Book(1L, "Title 1", "Author 1", 2022);
        when(bookService.addBook(book)).thenReturn(book);

        book.setTitle("Title 2");
        book.setAuthor("Author 2");
        book.setYear(2021);
        when(bookService.findOne(1L)).thenReturn(book);
    }
}
