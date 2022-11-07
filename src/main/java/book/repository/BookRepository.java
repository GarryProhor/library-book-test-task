package book.repository;

import book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //...
    List<Book> findByTitleStartingWith(String query);

//    @Query(value = "SELECT * FROM book WHERE author = ?")
    List<Book> findByAuthor(String author);

//    @Query(value = "SELECT MAX(e.id) FROM Book e")
//    Long findMaxId();
}
