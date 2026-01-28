package com.example.saudejaauthenticationservice.infrastructure.token.jwt.services;

import com.example.saudejaauthenticationservice.datasources.TokenDataSource;
import dtos.responses.CredenciaisUsuarioResponse;
import enums.TipoTokenEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

@Service
@Profile("jwt")
public class TokenJwtImpl implements TokenDataSource {

    @Value("${spring.application.name}")
    private String nomeAplicacao;

    @Value("${jwt.token.expiration-time}")
    private Long tempoExpiracao;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Override
    public String gerarTokenUsuario(CredenciaisUsuarioResponse credenciaisUsuarioResponse, String audiencia) {
        Instant agora = Instant.now();

        var claims = JwtClaimsSet.builder()
                .issuer(nomeAplicacao)
                .issuedAt(agora)
                .expiresAt(agora.plusSeconds(tempoExpiracao))
                .audience(Collections.singletonList(audiencia))
                .subject(credenciaisUsuarioResponse.email())
                .claim("tipo_token", TipoTokenEnum.USUARIO)
                .claim("authorities", credenciaisUsuarioResponse.tipo())
                .id(UUID.randomUUID().toString())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public String gerarTokenServico(String servico, String audiencia) {
        Instant now = Instant.now();

        var claims = JwtClaimsSet.builder()
                .issuer(nomeAplicacao)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(tempoExpiracao))
                .audience(Collections.singletonList(audiencia))
                .subject(servico)
                .claim("tipo_token", TipoTokenEnum.SERVICO)
                .id(UUID.randomUUID().toString())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
