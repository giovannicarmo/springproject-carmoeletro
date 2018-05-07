package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.State;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository repository;

    public List<State> findAll () {
        return repository.findAllByOrderByName();
    }
}
