package com.giovannicarmo.projetocurso.carmoeletro.services.validation;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Client;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.ClientType;
import com.giovannicarmo.projetocurso.carmoeletro.dto.ClientNewDTO;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.ClientRepository;
import com.giovannicarmo.projetocurso.carmoeletro.resources.exception.FieldMessage;
import com.giovannicarmo.projetocurso.carmoeletro.services.validation.util.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

    @Autowired
    ClientRepository repository;

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
        Client aux = repository.findByEmail(objectDto.getEmail());
        if (aux != null)
            list.add(new FieldMessage("email", "Existent email."));
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
