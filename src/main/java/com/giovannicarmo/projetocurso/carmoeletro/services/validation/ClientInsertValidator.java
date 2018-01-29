package com.giovannicarmo.projetocurso.carmoeletro.services.validation;

import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.ClientType;
import com.giovannicarmo.projetocurso.carmoeletro.dto.ClientNewDTO;
import com.giovannicarmo.projetocurso.carmoeletro.resources.exception.FieldMessage;
import com.giovannicarmo.projetocurso.carmoeletro.services.validation.util.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
    @Override
    public void initialize(ClientInsert ann) {
    }
    @Override
    public boolean isValid(ClientNewDTO objectDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        if (objectDto.getType().equals(ClientType.PESSOAFISICA.getId()) && !BR.isValidCPF(objectDto.getCpfOrCnpj()))
            list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF!"));
        if (objectDto.getType().equals(ClientType.PESSOAJURIDICA.getId()) && !BR.isValidCNPJ(objectDto.getCpfOrCnpj()))
            list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ!"));
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
