package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Category;
import com.giovannicarmo.projetocurso.carmoeletro.domain.Product;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.CategoryRepository;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.ProductRepository;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository repository;

    public Product find (Integer id){
        Product order = repository.findOne(id);
        if (order == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " Type: " + Product.class.getName());
        }
        return order;
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Category> categories =  categoryRepository.findAll(ids);
        return repository.findDistinctByNameIgnoreCaseContainingAndCategoriesIn(name, categories, pageRequest);
    }
}
