package com.example.saudejaauthenticationservice.infrastructure.client.restclient.services;

import com.example.saudejaauthenticationservice.controllers.TokenController;
import com.example.saudejaauthenticationservice.datasources.TokenDataSource;
import com.example.saudejaauthenticationservice.datasources.UsuarioDataSource;
import com.example.saudejaauthenticationservice.exceptions.NotFoundException;
import com.example.saudejaauthenticationservice.infrastructure.exceptions.BadRequestException;
import dtos.responses.CredenciaisUsuarioResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Profile("restclient")
public class UsuarioRestClientImpl implements UsuarioDataSource {

    @Value("${spring.application.name}")
    private String nomeAplicacao;

    @Value("${usuario-service.base-url}")
    private String urlBase;

    @Value("${usuario-service.audiencia}")
    private String audiencia;
    private final RestClient client;
    private final TokenController tokenController;

    public UsuarioRestClientImpl(RestClient client, TokenDataSource tokenDataSource) {
        this.client = client;
        this.tokenController = new TokenController(tokenDataSource);
    }

    @Override
    public CredenciaisUsuarioResponse getCredenciaisUsuarioByEmail(String email) throws NotFoundException {
        return client.get()
                .uri(urlBase + "/api/v1/usuarios/" + email)
                .header("Authorization", "Bearer " + tokenController.gerarTokenServico(nomeAplicacao, audiencia))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) ->
                        {
                            if (res.getStatusCode().equals(HttpStatus.BAD_REQUEST))
                                throw new BadRequestException(res.getStatusText());
                            if (res.getStatusCode().equals(HttpStatus.NOT_FOUND))
                                throw new NotFoundException(String.format("Usuário %s não encontrado", email));
                            else
                                throw new RuntimeException("Falha no serviço de usuários (usuario-service).");
                        }
                )
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) ->
                        {
                            throw new RuntimeException("Falha no serviço de usuários (usuario-service).");
                        }
                )
                .toEntity(CredenciaisUsuarioResponse.class).getBody();
    }
}
