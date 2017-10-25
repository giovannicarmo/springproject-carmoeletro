package com.giovannicarmo.projetocurso.carmoeletro.resources;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> list() {

        Category category1 = new Category(1, "Informatica");
        Category category2 = new Category(2,"Escritorio");

        List<Category> list = new ArrayList<>();
        list.add(category1);
        list.add(category2);

        return list;
    }
}
