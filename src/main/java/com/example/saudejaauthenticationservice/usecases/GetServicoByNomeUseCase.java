package com.example.saudejaauthenticationservice.usecases;

import com.example.saudejaauthenticationservice.entidades.Servico;
import com.example.saudejaauthenticationservice.exceptions.NotFoundException;
import com.example.saudejaauthenticationservice.gateways.ServicoGateway;

public class GetServicoByNomeUseCase {
    private final ServicoGateway servicoGateway;

    public GetServicoByNomeUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public Servico executar(String nome) throws NotFoundException {
        return servicoGateway.getServicoByNome(nome);
    }
}
