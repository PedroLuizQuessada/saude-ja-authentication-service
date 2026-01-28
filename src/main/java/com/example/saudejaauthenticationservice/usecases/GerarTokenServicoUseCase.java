package com.example.saudejaauthenticationservice.usecases;

import com.example.saudejaauthenticationservice.gateways.TokenGateway;

public class GerarTokenServicoUseCase {
    private final TokenGateway tokenGateway;

    public GerarTokenServicoUseCase(TokenGateway tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    public String executar(String servico, String audiencia) {
        return tokenGateway.gerarTokenServico(servico, audiencia);
    }
}
