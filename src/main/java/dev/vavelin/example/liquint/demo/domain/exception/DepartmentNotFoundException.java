package dev.vavelin.example.liquint.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends RuntimeException {

    private final DemoAppErrorCode code;

    public DepartmentNotFoundException(DemoAppErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public DemoAppErrorCode getCode() {
        return code;
    }
}
