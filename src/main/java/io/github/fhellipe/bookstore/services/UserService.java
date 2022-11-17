package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

// classe que auxilia no retorno do usuario logado (autenticado)
public class UserService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }
}
