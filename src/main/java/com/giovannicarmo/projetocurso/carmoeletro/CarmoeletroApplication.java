package com.giovannicarmo.projetocurso.carmoeletro;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Category;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CarmoeletroApplication implements CommandLineRunner{

    @Autowired
    private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(CarmoeletroApplication.class, args);
	}


    @Override
    public void run(String... strings) throws Exception {

        Category category1 = new Category(null, "Informatica");
        Category category2 = new Category(null,"Escritorio");

        categoryRepository.save(Arrays.asList(category1, category2));
    }
}
