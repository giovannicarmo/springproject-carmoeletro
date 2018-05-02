package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Address;
import com.giovannicarmo.projetocurso.carmoeletro.domain.City;
import com.giovannicarmo.projetocurso.carmoeletro.domain.Client;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.ClientType;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.Profile;
import com.giovannicarmo.projetocurso.carmoeletro.dto.ClientDTO;
import com.giovannicarmo.projetocurso.carmoeletro.dto.ClientNewDTO;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.AddressRepository;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.CityRepository;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.ClientRepository;
import com.giovannicarmo.projetocurso.carmoeletro.security.UserSS;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.AuthorizationExcepition;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.DataIntegrityException;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${image.profile.size}")
    private Integer size;

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client find (Integer id){

        UserSS userSS = UserService.authenticated();
        if (userSS == null || !userSS.hasRole(Profile.ADIMIN) && !id.equals(userSS.getId())) {
            throw new AuthorizationExcepition("Access denied!");
        }

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

    @Transactional
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
                ClientType.toEnum(objectDTO.getType()), passwordEncoder.encode(objectDTO.getPassword()));
        City city = new City(objectDTO.getCityId(), null, null);
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
        return new Client(objectDTO.getId(), objectDTO.getName(), objectDTO.getEmail(), null, null, null);
    }

    private void updateData(Client newObject, Client object) {
        newObject.setEmail(object.getEmail() );
        newObject.setName(object.getName());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {

        UserSS userSS = UserService.authenticated();
        if (userSS == null) {
            throw new AuthorizationExcepition("Access denied!");
        }

        BufferedImage jpgImage = imageService.getJpjImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);

        String fileName = prefix + userSS.getId() + ".jpg";

        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
