package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Category;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category find (Integer id){
        Category category = categoryRepository.findOne(id);
        return category;
    }
}