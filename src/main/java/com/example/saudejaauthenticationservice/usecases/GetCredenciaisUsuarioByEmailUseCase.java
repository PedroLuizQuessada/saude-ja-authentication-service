package com.example.saudejaauthenticationservice.usecases;

import com.example.saudejaauthenticationservice.exceptions.NotFoundException;
import com.example.saudejaauthenticationservice.gateways.UsuarioGateway;
import dtos.responses.CredenciaisUsuarioResponse;

public class GetCredenciaisUsuarioByEmailUseCase {
    private final UsuarioGateway usuarioGateway;

    public GetCredenciaisUsuarioByEmailUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public CredenciaisUsuarioResponse executar(String email) throws NotFoundException {
        return usuarioGateway.getCredenciaisUsuarioByEmail(email);
    }
}
