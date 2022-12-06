package io.github.fhellipe.bookstore.controller;

import io.github.fhellipe.bookstore.dto.EmailDTO;
import io.github.fhellipe.bookstore.security.JWTUtil;
import io.github.fhellipe.bookstore.security.UserSS;
import io.github.fhellipe.bookstore.services.AuthService;
import io.github.fhellipe.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    AuthService authService;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        assert user != null;
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
        authService.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }

}
