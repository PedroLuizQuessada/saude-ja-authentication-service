package com.example.saudejaauthenticationservice.gateways;

import com.example.saudejaauthenticationservice.datasources.TokenDataSource;
import dtos.responses.CredenciaisUsuarioResponse;

public class TokenGateway {
    private final TokenDataSource tokenDataSource;

    public TokenGateway(TokenDataSource tokenDataSource) {
        this.tokenDataSource = tokenDataSource;
    }

    public String gerarTokenUsuario(CredenciaisUsuarioResponse credenciaisUsuarioResponse, String audiencia) {
        return tokenDataSource.gerarTokenUsuario(credenciaisUsuarioResponse, audiencia);
    }

    public String gerarTokenServico(String servico, String audiencia) {
        return tokenDataSource.gerarTokenServico(servico, audiencia);
    }
}
