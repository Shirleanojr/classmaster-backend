package dev.shirleano.classmaster_backend.services.auth;

import dev.shirleano.classmaster_backend.domain.usuario.Usuario;
import dev.shirleano.classmaster_backend.dto.auth.AutenticacaoDto;
import dev.shirleano.classmaster_backend.dto.auth.RespostaTokenDto;
import dev.shirleano.classmaster_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;



    public RespostaTokenDto realizarLogin(AutenticacaoDto dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var authenticaion = manager.authenticate(authToken);
        var token = tokenService.gerarToken((Usuario) authenticaion.getPrincipal());
        return new RespostaTokenDto(dados.email(), token);
    }


}
