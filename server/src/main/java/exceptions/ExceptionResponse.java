package exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@Data
public class ExceptionResponse implements Serializable {

    private Date timestamp;
    private String message;
    private String details;
    private Integer status;
    private String error;

    public ExceptionResponse(Date timestamp, String message, String description) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = description;
    }



    public ExceptionResponse(Date timestamp, HttpStatus httpStatus, String message) {
        this.timestamp = timestamp;
        this.status = httpStatus.value();
        this.message = message;

    }
}
