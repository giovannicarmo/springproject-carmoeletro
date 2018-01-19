package com.giovannicarmo.projetocurso.carmoeletro.resources;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Category;
import com.giovannicarmo.projetocurso.carmoeletro.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Category category = categoryService.find(id);
        return ResponseEntity.ok().body(category);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@RequestBody Category category) {
        category = categoryService.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
