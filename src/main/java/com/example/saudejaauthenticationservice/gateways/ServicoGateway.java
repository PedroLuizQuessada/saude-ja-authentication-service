package com.example.saudejaauthenticationservice.gateways;

import com.example.saudejaauthenticationservice.datasources.ServicoDataSource;
import com.example.saudejaauthenticationservice.dtos.ServicoDto;
import com.example.saudejaauthenticationservice.entidades.Servico;
import com.example.saudejaauthenticationservice.exceptions.NotFoundException;
import com.example.saudejaauthenticationservice.mappers.ServicoMapper;

public class ServicoGateway {
    private final ServicoDataSource servicoDataSource;

    public ServicoGateway(ServicoDataSource servicoDataSource) {
        this.servicoDataSource = servicoDataSource;
    }

    public Servico getServicoByNome(String nome) throws NotFoundException {
        ServicoDto servicoDto = servicoDataSource.getServicoByNome(nome);
        return ServicoMapper.toEntidade(servicoDto);
    }
}
