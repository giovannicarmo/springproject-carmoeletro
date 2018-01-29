package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Address;
import com.giovannicarmo.projetocurso.carmoeletro.domain.City;
import com.giovannicarmo.projetocurso.carmoeletro.domain.Client;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.ClientType;
import com.giovannicarmo.projetocurso.carmoeletro.dto.ClientDTO;
import com.giovannicarmo.projetocurso.carmoeletro.dto.ClientNewDTO;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.AddressRepository;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.CityRepository;
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

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

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
        object = repository.save(object);
        addressRepository.save(object.getAddresses());
        return object;
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

    public Client fromDTO(ClientNewDTO objectDTO) {
        Client client = new Client (null, objectDTO.getName(), objectDTO.getEmail(), objectDTO.getCpfOrCnpj(),
                ClientType.toEnum(objectDTO.getType()));
        City city = cityRepository.findOne(objectDTO.getCityId());
        Address address = new Address(null, objectDTO.getPublicPlace(), objectDTO.getNumber(), objectDTO.getComplement(),
                objectDTO.getNeighborhood(), objectDTO.getZipCode(), client, city);
        client.getAddresses().add(address);
        client.getTelephones().add(objectDTO.getTelephone1());
        if(objectDTO.getTelephone2() != null)
            client.getTelephones().add(objectDTO.getTelephone2());
        if(objectDTO.getTelephone3() != null) client.getTelephones().add(objectDTO.getTelephone3());
        return client;
    }

    public Client fromDTO(ClientDTO objectDTO) {
        return new Client(objectDTO.getId(), objectDTO.getName(), objectDTO.getEmail(), null, null);
    }

    private void updateData(Client newObject, Client object) {
        newObject.setEmail(object.getEmail() );
        newObject.setName(object.getName());
    }
}
