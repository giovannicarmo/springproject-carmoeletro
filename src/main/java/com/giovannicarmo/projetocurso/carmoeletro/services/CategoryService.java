package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Category;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.CategoryRepository;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.DataIntegrityException;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category find (Integer id){
        Category category = categoryRepository.findOne(id);
        if (category == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " Type: " + Category.class.getName());
        }
        return category;
    }

    public Category insert (Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        find(category.getId());
        return categoryRepository.save(category);
    }

    public void delete(Integer id) {
        find(id);
        try {
            categoryRepository.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new  DataIntegrityException("Don't possible delete a category with products!");
        }
    }
}
