package com.example.saudejaauthenticationservice.entidades;

import com.example.saudejaauthenticationservice.exceptions.BadArgumentException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Servico {
    private final String nome;
    private final String senha;

    public Servico(String nome, String senha) {
        String mensagemErro = "";

        try {
            validarNome(nome);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarSenha(senha);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        if (!mensagemErro.isEmpty())
            throw new BadArgumentException(mensagemErro);

        this.nome = nome;
        this.senha = senha;
    }

    private void validarNome(String nome) {
        if (Objects.isNull(nome))
            throw new BadArgumentException("O serviço deve possuir um nome.");
    }

    private void validarSenha(String senha) {
        if (Objects.isNull(senha))
            throw new BadArgumentException("O serviço deve possuir uma senha.");
    }
}
