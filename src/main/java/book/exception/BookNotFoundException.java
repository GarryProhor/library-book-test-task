package book.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super("Не удалось найти книгу с идентификатором " + id);
    }
}
