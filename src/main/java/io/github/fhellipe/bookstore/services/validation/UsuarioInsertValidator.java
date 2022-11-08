package io.github.fhellipe.bookstore.services.validation;

import io.github.fhellipe.bookstore.controller.exceptions.FieldMessage;
import io.github.fhellipe.bookstore.dto.UsuarioNewDTO;
import io.github.fhellipe.bookstore.enums.TipoUsuario;
import io.github.fhellipe.bookstore.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {

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

        // inclui cada teste na lista do framework utilizado para validações
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
