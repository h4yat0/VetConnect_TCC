package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExecptionNovos extends RuntimeException {

    public ExecptionNovos(String msg){super(msg);}
}
