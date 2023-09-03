package dev.vavelin.example.liquint.demo.ui.handler;


import dev.vavelin.example.liquint.demo.domain.exception.DemoAppErrorCode;

public record ErrorResponse(DemoAppErrorCode code, String message) {
    @Override
    public String toString() {
        return String.valueOf(code);
    }
}