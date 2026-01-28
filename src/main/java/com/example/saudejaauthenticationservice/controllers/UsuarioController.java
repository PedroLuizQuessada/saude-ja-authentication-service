package com.example.saudejaauthenticationservice.controllers;

import com.example.saudejaauthenticationservice.datasources.UsuarioDataSource;
import com.example.saudejaauthenticationservice.exceptions.NotFoundException;
import com.example.saudejaauthenticationservice.gateways.UsuarioGateway;
import com.example.saudejaauthenticationservice.usecases.GetCredenciaisUsuarioByEmailUseCase;
import dtos.responses.CredenciaisUsuarioResponse;

public class UsuarioController {
    private final UsuarioDataSource usuarioDataSource;

    public UsuarioController(UsuarioDataSource usuarioDataSource) {
        this.usuarioDataSource = usuarioDataSource;
    }

    public CredenciaisUsuarioResponse getCredenciaisUsuarioByEmailUseCase(String email) throws NotFoundException {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        GetCredenciaisUsuarioByEmailUseCase getCredenciaisUsuarioByEmailUseCase = new GetCredenciaisUsuarioByEmailUseCase(usuarioGateway);
        return getCredenciaisUsuarioByEmailUseCase.executar(email);
    }
}
