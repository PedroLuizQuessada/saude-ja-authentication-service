package com.example.saudejaauthenticationservice.infrastructure.input.api.controllers.token;

import com.example.saudejaauthenticationservice.controllers.TokenController;
import com.example.saudejaauthenticationservice.datasources.TokenDataSource;
import enums.TipoUsuarioEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/tokens")
@Tag(name = "Token API V1", description = "Versão 1 do controlador referente a tokens")
@Profile("api")
public class TokenControllerV1 {

    private final TokenController tokenController;

    public TokenControllerV1(TokenDataSource tokenDataSource) {
        this.tokenController = new TokenController(tokenDataSource);
    }

    @Operation(summary = "Gera token de acesso",
            description = "Requer autenticação",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Token gerado com sucesso",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping("/{audiencia}")
    public ResponseEntity<String> gerarToken(@AuthenticationPrincipal UserDetails userDetails,
                                                    @PathVariable("audiencia") String audiencia) {
        String token;
        String tipoToken;
        if (userDetails.getAuthorities().stream().findFirst().isPresent()) {
            log.info("Usuário {} gerando token", userDetails.getUsername());
            tipoToken = "Usuário";
            token = tokenController.gerarTokenUsuario(userDetails.getUsername(), userDetails.getPassword(),
                    TipoUsuarioEnum.valueOf(String.valueOf(userDetails.getAuthorities().stream().findFirst().get())), audiencia);
        }
        else {
            log.info("Serviço {} gerando token", userDetails.getUsername());
            tipoToken = "Serviço";
            token = tokenController.gerarTokenServico(userDetails.getUsername(), audiencia);
        }
        log.info("{} {} gerou token", tipoToken, userDetails.getUsername());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(token);
    }
}
