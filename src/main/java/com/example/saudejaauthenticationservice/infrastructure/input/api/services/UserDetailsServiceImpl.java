package com.example.saudejaauthenticationservice.infrastructure.input.api.services;

import com.example.saudejaauthenticationservice.controllers.ServicoController;
import com.example.saudejaauthenticationservice.controllers.UsuarioController;
import com.example.saudejaauthenticationservice.datasources.ServicoDataSource;
import com.example.saudejaauthenticationservice.datasources.UsuarioDataSource;
import com.example.saudejaauthenticationservice.dtos.ServicoDto;
import com.example.saudejaauthenticationservice.exceptions.NotFoundException;
import com.example.saudejaauthenticationservice.infrastructure.exceptions.UnauthorizedException;
import dtos.responses.CredenciaisUsuarioResponse;
import enums.TipoUsuarioEnum;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Profile("api")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ServicoController servicoController;
    private final UsuarioController usuarioController;

    public UserDetailsServiceImpl(ServicoDataSource servicoDataSource, UsuarioDataSource usuarioDataSource) {
        this.servicoController = new ServicoController(servicoDataSource);
        this.usuarioController = new UsuarioController(usuarioDataSource);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        String nome;
        String senha;
        TipoUsuarioEnum tipoUsuario = null;
        try {
            ServicoDto servicoDto = servicoController.getServicoByNomeUseCase(username);
            nome = servicoDto.nome();
            senha = servicoDto.senha();
        }
        catch (NotFoundException ex) {
            try {
                CredenciaisUsuarioResponse credenciaisUsuarioResponse = usuarioController.getCredenciaisUsuarioByEmailUseCase(username);
                nome = credenciaisUsuarioResponse.email();
                senha = credenciaisUsuarioResponse.senha();
                tipoUsuario = credenciaisUsuarioResponse.tipo();
            }
            catch (NotFoundException ex1) {
                throw new UnauthorizedException();
            }
        }
        return new org.springframework.security.core.userdetails.User(nome, senha,
                Objects.isNull(tipoUsuario) ? new ArrayList<>() : List.of(new SimpleGrantedAuthority(String.valueOf(tipoUsuario))));
    }
}
