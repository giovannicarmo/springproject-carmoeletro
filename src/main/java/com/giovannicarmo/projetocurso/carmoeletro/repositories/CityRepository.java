package com.giovannicarmo.projetocurso.carmoeletro.repositories;

import com.giovannicarmo.projetocurso.carmoeletro.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer>{

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM City obj WHERE obj.state.id = :stateID ORDER BY obj.name")
    List<City> findCities(@Param("stateID") Integer stateID);
}
