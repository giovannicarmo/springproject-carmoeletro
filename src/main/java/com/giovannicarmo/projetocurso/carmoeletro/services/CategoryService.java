package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Category;
import com.giovannicarmo.projetocurso.carmoeletro.dto.CategoryDTO;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.CategoryRepository;
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
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category find (Integer id){
        Category object = repository.findOne(id);
        if (object == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " Type: " + Category.class.getName());
        }
        return object;
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Category insert (Category object) {
        object.setId(null);
        return repository.save(object);
    }

    public Category update(Category object) {
        Category newObject = find(object.getId());
        updateData(newObject, object);
        return repository.save(newObject);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new  DataIntegrityException("Don't possible delete a object with products!");
        }
    }

    public Category fromDTO(CategoryDTO objectDTO) {
        return new Category(objectDTO.getId(), objectDTO.getName());
    }

    private void updateData(Category newObject, Category object) {
        newObject.setName(object.getName());
    }
}
