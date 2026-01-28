package com.example.saudejaauthenticationservice.mappers;

import com.example.saudejaauthenticationservice.dtos.ServicoDto;
import com.example.saudejaauthenticationservice.entidades.Servico;

public class ServicoMapper {
    private ServicoMapper() {}

    public static Servico toEntidade(ServicoDto servicoDto) {
        return new Servico(servicoDto.nome(), servicoDto.senha());
    }

    public static ServicoDto toDto(Servico servico) {
        return new ServicoDto(servico.getNome(), servico.getSenha());
    }
}
