package io.github.fhellipe.bookstore.services.validation;

import io.github.fhellipe.bookstore.controller.exceptions.FieldMessage;
import io.github.fhellipe.bookstore.dto.UsuarioDTO;
import io.github.fhellipe.bookstore.model.Usuario;
import io.github.fhellipe.bookstore.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioUpdateValidator implements ConstraintValidator<UsuarioUpdate, UsuarioDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UsuarioRepository repo;

    @Override
    public void initialize(UsuarioUpdate ann) {
    }

    @Override
    public boolean isValid(UsuarioDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();
        // testes de validações
        Usuario aux = repo.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        // inclui cada teste na lista do framework utilizado para validações
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
