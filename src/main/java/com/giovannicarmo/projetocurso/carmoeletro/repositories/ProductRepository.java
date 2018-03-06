package com.giovannicarmo.projetocurso.carmoeletro.repositories;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Category;
import com.giovannicarmo.projetocurso.carmoeletro.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{

    Page<Product> findDistinctByNameIgnoreCaseContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);
}
