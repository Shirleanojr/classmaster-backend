package dev.shirleano.classmaster_backend.services.usuario;

import dev.shirleano.classmaster_backend.domain.usuario.Usuario;
import dev.shirleano.classmaster_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;


//    public UserDetails retornaUsuarioPeloLogin(String email) {
//        return repository.findByLogin(email);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public void salvar(Usuario usuario) {
        String senhaHashed = gerarSenhaHashed(usuario.getSenha());
        usuario.setSenha(senhaHashed);
        this.repository.save(usuario);
    }

    private String gerarSenhaHashed(String senha) {
        String salto = BCrypt.gensalt();
        return BCrypt.hashpw(senha, salto);
    }
}
