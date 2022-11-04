package book.controller;

import book.model.Book;
import book.service.BookService;
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
    HttpEntity<?> getBooksById(@PathVariable Long id) {
        if (bookService.findOne(id) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.findOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        if (bookService.findAllBooks() == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        return  ResponseEntity.ok(bookService.save(book));
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id) {
        if (bookService.findOne(id) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.ok(bookService.update(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") Long id) {
        if (bookService.findOne(id) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(bookService.delete(id));
    }
}


