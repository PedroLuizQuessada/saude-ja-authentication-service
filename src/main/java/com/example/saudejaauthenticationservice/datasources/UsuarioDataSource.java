package com.example.saudejaauthenticationservice.datasources;

import com.example.saudejaauthenticationservice.exceptions.NotFoundException;
import dtos.responses.CredenciaisUsuarioResponse;

public interface UsuarioDataSource {
    CredenciaisUsuarioResponse getCredenciaisUsuarioByEmail(String email) throws NotFoundException;
}
