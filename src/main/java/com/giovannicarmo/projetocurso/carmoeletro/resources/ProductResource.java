package com.giovannicarmo.projetocurso.carmoeletro.resources;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Product;
import com.giovannicarmo.projetocurso.carmoeletro.dto.CategoryDTO;
import com.giovannicarmo.projetocurso.carmoeletro.dto.ProductDTO;
import com.giovannicarmo.projetocurso.carmoeletro.resources.utils.URL;
import com.giovannicarmo.projetocurso.carmoeletro.services.ProductService;
import com.giovannicarmo.projetocurso.carmoeletro.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Product object = service.find(id);
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = service.search(nameDecoded , ids, page, linesPerPage, direction, orderBy);
        Page<ProductDTO> objectDTOS = list.map(object -> new ProductDTO(object));
        return ResponseEntity.ok().body(objectDTOS);
    }

}
