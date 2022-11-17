package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.model.Pedido;
import io.github.fhellipe.bookstore.model.Usuario;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);
    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);

    void sendNewPasswordEmail(Usuario usuario, String newPass);
}
