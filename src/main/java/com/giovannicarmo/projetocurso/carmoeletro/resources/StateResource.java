package com.giovannicarmo.projetocurso.carmoeletro.resources;

import com.giovannicarmo.projetocurso.carmoeletro.domain.City;
import com.giovannicarmo.projetocurso.carmoeletro.domain.State;
import com.giovannicarmo.projetocurso.carmoeletro.dto.CityDTO;
import com.giovannicarmo.projetocurso.carmoeletro.dto.StateDTO;
import com.giovannicarmo.projetocurso.carmoeletro.services.CityService;
import com.giovannicarmo.projetocurso.carmoeletro.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

    @Autowired
    private StateService service;

    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StateDTO>> findAll() {
        List<State> list = service.findAll();
        List<StateDTO> listDTO = list.stream().map(object -> new StateDTO(object)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/{stateID}/cities", method = RequestMethod.GET)
    public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateID) {
        List<City> list = cityService.findByState(stateID);
        List<CityDTO> listDTO = list.stream().map(object -> new CityDTO(object)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
