package com.example.saudejaauthenticationservice.gateways;

import com.example.saudejaauthenticationservice.datasources.UsuarioDataSource;
import com.example.saudejaauthenticationservice.exceptions.NotFoundException;
import dtos.responses.CredenciaisUsuarioResponse;

public class UsuarioGateway {

    private final UsuarioDataSource usuarioDataSource;

    public UsuarioGateway(UsuarioDataSource usuarioDataSource) {
        this.usuarioDataSource = usuarioDataSource;
    }

    public CredenciaisUsuarioResponse getCredenciaisUsuarioByEmail(String email) throws NotFoundException {
        return usuarioDataSource.getCredenciaisUsuarioByEmail(email);
    }
}
