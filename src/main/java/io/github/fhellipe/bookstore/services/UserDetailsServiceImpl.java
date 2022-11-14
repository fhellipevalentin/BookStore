package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.model.Usuario;
import io.github.fhellipe.bookstore.repositories.UsuarioRepository;
import io.github.fhellipe.bookstore.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Serviço que permite a busca pelo nome do usuario
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usr = usuarioRepository.findByEmail(username);
        if (usr == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserSS(usr.getId(), usr.getEmail(), usr.getSenha(), usr.getPerfis());
    }
}
