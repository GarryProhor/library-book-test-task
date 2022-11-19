package book.controller;

import book.model.Book;
import book.service.BookService;
import book.util.CustomResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("books")
public class BookController {

    final private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}")
    public @ResponseBody
    CustomResponse<Book> getBooksById(@PathVariable Long id) {
        return bookService.findOne(id);
    }

    @GetMapping
    public CustomResponse<Book> getBooks(){
        return bookService.findAllBooks();
    }

    @GetMapping("author/{author}")
    public CustomResponse<Book> getBookByAuthorName(
            @PathVariable("author") String author){
        return bookService.findByAuthorName(author);
    }

    @PostMapping
    public CustomResponse<Book> createBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("{id}")
    public CustomResponse<Book> updateBook(@Valid @RequestBody Book book) {
               return bookService.update(book);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") Long id) {
        if (bookService.findOne(id) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            bookService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}


