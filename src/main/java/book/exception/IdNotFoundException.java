package book.exception;

import java.io.Serial;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 1L;

}
