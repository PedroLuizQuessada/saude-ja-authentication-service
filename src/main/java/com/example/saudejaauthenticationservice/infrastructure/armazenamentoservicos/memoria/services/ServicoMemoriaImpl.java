package com.example.saudejaauthenticationservice.infrastructure.armazenamentoservicos.memoria.services;

import com.example.saudejaauthenticationservice.datasources.ServicoDataSource;
import com.example.saudejaauthenticationservice.dtos.ServicoDto;
import com.example.saudejaauthenticationservice.exceptions.NotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile("memoria")
public class ServicoMemoriaImpl implements ServicoDataSource {

    private static final Map<String, String> SERVICOS =
            Map.of(
                    "saude-ja-usuario-service", "$2a$10$GOXvcVxcQVusjGBKbBp0y.mdJYjU4/Pcey98Kz2K.feFn8co/WN9S",
                    "saude-ja-ficha-paciente-service", "$2a$10$aRls/I6JdCnWT9KYl7JFi.mMz84lxyBZJaqMYtEuTMO3/tiCpLjU6",
                    "saude-ja-posto-saude-service", "$2a$10$W/aHHFdttCYE9y9XjdahNOvfmEw.77pr3/tsCRctfq9wCr0KHHbMe",
                    "saude-ja-consulta-service", "$2a$10$rGR5.4bKMMHI.GGP4Agff.nPrVn1TXoStEZlli1wD5LudDBkQjB6a"
            );

    @Override
    public ServicoDto getServicoByNome(String nome) throws NotFoundException {
        if (SERVICOS.containsKey(nome))
            return new ServicoDto(nome, SERVICOS.get(nome));
        throw new NotFoundException(String.format("Serviço %s não encontrado", nome));
    }
}
