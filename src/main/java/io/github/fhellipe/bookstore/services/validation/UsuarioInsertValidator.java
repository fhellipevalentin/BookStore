package io.github.fhellipe.bookstore.services.validation;

import io.github.fhellipe.bookstore.controller.exceptions.FieldMessage;
import io.github.fhellipe.bookstore.dto.UsuarioNewDTO;
import io.github.fhellipe.bookstore.enums.TipoUsuario;
import io.github.fhellipe.bookstore.model.Usuario;
import io.github.fhellipe.bookstore.repositories.UsuarioRepository;
import io.github.fhellipe.bookstore.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public void initialize(UsuarioInsert ann) {
    }

    @Override   // @Valid
    public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        // testes de validações
        if (objDto.getTipo().equals(TipoUsuario.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoUsuario.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Usuario aux = repo.findByEmail(objDto.getEmail());
        if(aux != null) { // verifica se encontrou no banco um email igual no banco
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
