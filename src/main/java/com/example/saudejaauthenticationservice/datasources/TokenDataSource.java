package com.example.saudejaauthenticationservice.datasources;

import dtos.responses.CredenciaisUsuarioResponse;

public interface TokenDataSource {
    String gerarTokenUsuario(CredenciaisUsuarioResponse credenciaisUsuarioResponse, String audiencia);
    String gerarTokenServico(String servico, String audiencia);
}
