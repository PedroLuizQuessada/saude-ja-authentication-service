package com.example.saudejaauthenticationservice.infrastructure.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Autenticação inválida.");
    }
}
