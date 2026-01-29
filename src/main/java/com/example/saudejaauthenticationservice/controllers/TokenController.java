package com.example.saudejaauthenticationservice.controllers;

import com.example.saudejaauthenticationservice.datasources.TokenDataSource;
import com.example.saudejaauthenticationservice.gateways.TokenGateway;
import com.example.saudejaauthenticationservice.usecases.GerarTokenServicoUseCase;
import com.example.saudejaauthenticationservice.usecases.GerarTokenUsuarioUseCase;
import enums.TipoUsuarioEnum;

public class TokenController {
    private final TokenDataSource tokenDataSource;

    public TokenController(TokenDataSource tokenDataSource) {
        this.tokenDataSource = tokenDataSource;
    }

    public String gerarTokenUsuario(Long id, String senha, TipoUsuarioEnum tipo, String audiencia) {
        TokenGateway tokenGateway = new TokenGateway(tokenDataSource);
        GerarTokenUsuarioUseCase useCase = new GerarTokenUsuarioUseCase(tokenGateway);
        return useCase.executar(id, senha, tipo, audiencia);
    }

    public String gerarTokenServico(String servico, String audiencia) {
        TokenGateway tokenGateway = new TokenGateway(tokenDataSource);
        GerarTokenServicoUseCase useCase = new GerarTokenServicoUseCase(tokenGateway);
        return useCase.executar(servico, audiencia);
    }
}
