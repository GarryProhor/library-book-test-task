package book.repository;

import java.util.List;

public interface LibraryRepository<K> {
    int save(K k);
    int update(K k);
    K findById(Long id);
    List<K> findByAuthor(String author);
    int deleteById(Long id);
    List<K> findAll();

}
