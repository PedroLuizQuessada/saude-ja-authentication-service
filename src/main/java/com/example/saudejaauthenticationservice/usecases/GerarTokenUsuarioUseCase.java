package com.example.saudejaauthenticationservice.usecases;

import com.example.saudejaauthenticationservice.gateways.TokenGateway;
import dtos.responses.CredenciaisUsuarioResponse;
import enums.TipoUsuarioEnum;

public class GerarTokenUsuarioUseCase {
    private final TokenGateway tokenGateway;

    public GerarTokenUsuarioUseCase(TokenGateway tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    public String executar(Long id, String senha, TipoUsuarioEnum tipo, String audiencia) {
        return tokenGateway.gerarTokenUsuario(new CredenciaisUsuarioResponse(id, senha, tipo), audiencia);
    }
}
