package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.model.Usuario;
import io.github.fhellipe.bookstore.repositories.UsuarioRepository;
import io.github.fhellipe.bookstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private Random random = new Random();

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void sendNewPassword(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw  new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();
        usuario.setSenha(bCryptPasswordEncoder.encode(newPass));

        usuarioRepository.save(usuario);
        emailService.sendNewPasswordEmail(usuario, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i=0; i<10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (random.nextInt(10) + 48);
        }
        else if (opt == 1) { // gera letra maiuscula
            return (char) (random.nextInt(26) + 65);
        }
        else { // gera letra minuscula
            return (char) (random.nextInt(26) + 97);
        }
    }

}
