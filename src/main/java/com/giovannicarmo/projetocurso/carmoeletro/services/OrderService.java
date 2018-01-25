package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Order;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.OrderRepository;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order find (Integer id){
        Order order = orderRepository.findOne(id);
        if (order == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " Type: " + Order.class.getName());
        }
        return order;
    }
}
