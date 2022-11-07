package book.repository.impl;

import book.model.Book;
import book.repository.LibraryRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryJBDC implements LibraryRepository<Book> {
    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryJBDC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO book (id, title, author, years) VALUES (?, ?, ?, ?)",
                book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
    }

    @Override
    public int update(Book book) {
        return jdbcTemplate.update("UPDATE book SET title = ?,  author = ?, years = ?, WHERE id = ?",
                book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
    }

    @Override
    public Book findById(Long id) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", new BeanPropertyRowMapper<>(Book.class), id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Book> findByAuthor(String author) {
        try{
            return jdbcTemplate.query("SELECT * FROM book WHERE author = ?", new BeanPropertyRowMapper<>(Book.class), author);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", new  BeanPropertyRowMapper<>(Book.class));
    }


}
