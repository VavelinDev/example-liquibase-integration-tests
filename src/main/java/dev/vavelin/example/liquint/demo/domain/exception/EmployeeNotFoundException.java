package dev.vavelin.example.liquint.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

    private final DemoAppErrorCode code;

    public EmployeeNotFoundException(DemoAppErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public DemoAppErrorCode getCode() {
        return code;
    }
}
