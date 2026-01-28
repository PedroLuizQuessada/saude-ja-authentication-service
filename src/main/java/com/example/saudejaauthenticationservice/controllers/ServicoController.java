package com.example.saudejaauthenticationservice.controllers;

import com.example.saudejaauthenticationservice.datasources.ServicoDataSource;
import com.example.saudejaauthenticationservice.dtos.ServicoDto;
import com.example.saudejaauthenticationservice.entidades.Servico;
import com.example.saudejaauthenticationservice.exceptions.NotFoundException;
import com.example.saudejaauthenticationservice.gateways.ServicoGateway;
import com.example.saudejaauthenticationservice.mappers.ServicoMapper;
import com.example.saudejaauthenticationservice.usecases.GetServicoByNomeUseCase;

public class ServicoController {
    private final ServicoDataSource servicoDataSource;

    public ServicoController(ServicoDataSource servicoDataSource) {
        this.servicoDataSource = servicoDataSource;
    }

    public ServicoDto getServicoByNomeUseCase(String nome) throws NotFoundException {
        ServicoGateway servicoGateway = new ServicoGateway(servicoDataSource);
        GetServicoByNomeUseCase useCase = new GetServicoByNomeUseCase(servicoGateway);
        Servico servico = useCase.executar(nome);
        return ServicoMapper.toDto(servico);
    }
}
