package com.example.saudejaauthenticationservice.datasources;

import com.example.saudejaauthenticationservice.dtos.ServicoDto;
import com.example.saudejaauthenticationservice.exceptions.NotFoundException;

public interface ServicoDataSource {
    ServicoDto getServicoByNome(String nome) throws NotFoundException;
}
