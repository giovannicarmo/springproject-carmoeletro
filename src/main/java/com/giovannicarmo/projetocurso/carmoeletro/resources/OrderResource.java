package com.giovannicarmo.projetocurso.carmoeletro.resources;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Order;
import com.giovannicarmo.projetocurso.carmoeletro.domain.Product;
import com.giovannicarmo.projetocurso.carmoeletro.resources.utils.URL;
import com.giovannicarmo.projetocurso.carmoeletro.security.UserSS;
import com.giovannicarmo.projetocurso.carmoeletro.services.OrderService;
import com.giovannicarmo.projetocurso.carmoeletro.services.UserService;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.AuthorizationExcepition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Order object = service.find(id);
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody Order object) {
        object = service.insert(object);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(object.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Order>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "instant") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction
    ) {
        Page<Order> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}
