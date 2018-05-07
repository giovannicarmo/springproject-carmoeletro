package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.City;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public List<City> findByState(Integer stateID) {
        return repository.findCities(stateID);
    }
}
