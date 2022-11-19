package book.util;

import book.model.Book;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class CustomResponse<T> {

    private int code;

    private String message;

    private Collection<T> responseList;

    public CustomResponse(Collection<T> response, CustomStatus customStatus) {
        this.code = customStatus.getCode();
        this.message = customStatus.getMessage();
        this.responseList = response;
    }
}
