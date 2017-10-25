package com.giovannicarmo.projetocurso.carmoeletro.repositories;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
}
