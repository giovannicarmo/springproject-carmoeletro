package com.giovannicarmo.projetocurso.carmoeletro.repositories;

import com.giovannicarmo.projetocurso.carmoeletro.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer>{

    @Transactional(readOnly = true)
    List<State> findAllByOrderByName();
}
