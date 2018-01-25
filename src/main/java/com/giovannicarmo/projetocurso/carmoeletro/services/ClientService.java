package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Client;
import com.giovannicarmo.projetocurso.carmoeletro.dto.ClientDTO;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.ClientRepository;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.ClientRepository;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.DataIntegrityException;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client find (Integer id){
        Client object = repository.findOne(id);
        if (object == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " Type: " + Client.class.getName());
        }
        return object;
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Client insert (Client object) {
        object.setId(null);
        return repository.save(object);
    }

    public Client update(Client object) {
        Client newObject = find(object.getId());
        updateData(newObject, object);
        return repository.save(newObject);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Can't be excluded because there are related entities.");
        }
    }

    public Client fromDTO(ClientDTO objectDTO) {
        return new Client(objectDTO.getId(), objectDTO.getName(), objectDTO.getEmail(), null, null);
    }

    private void updateData(Client newObject, Client object) {
        newObject.setEmail(object.getEmail() );
        newObject.setName(object.getName());
    }
}
