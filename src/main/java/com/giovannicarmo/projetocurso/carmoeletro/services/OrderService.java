package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Client;
import com.giovannicarmo.projetocurso.carmoeletro.domain.Order;
import com.giovannicarmo.projetocurso.carmoeletro.domain.OrderItem;
import com.giovannicarmo.projetocurso.carmoeletro.domain.TicketPayment;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.PaymentState;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.*;
import com.giovannicarmo.projetocurso.carmoeletro.security.UserSS;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.AuthorizationExcepition;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.ObjectNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Order find (Integer id){
        Order object = repository.findOne(id);
        if (object == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " Type: " + Order.class.getName());
        }
        return object;
    }

    public Order insert(Order object) {
        object.setId(null);
        object.setInstant(new Date());
        object.getPayment().setPaymentState(PaymentState.PENDENTE);
        object.getPayment().setOrder(object);
        if(object.getPayment() instanceof TicketPayment) {
            TicketPayment payment = (TicketPayment) object.getPayment();
            ticketService.compleerTicketPayment(payment, object.getInstant());
        }
        object = repository.save(object);
        paymentRepository.save(object.getPayment());
        for (OrderItem orderItem : object.getItems()) {
            orderItem.setAmount(0.0);
            orderItem.setPrice(productRepository.findOne(orderItem.getProduct().getId()).getPrice());
            orderItem.setOrder(object);
        }
        orderItemRepository.save(object.getItems());
        return object;
    }

    public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS userSS = UserService.authenticated();
        if(userSS == null) {
            throw new AuthorizationExcepition("Access denied!");
        }
        PageRequest pageRequest = new PageRequest(page,linesPerPage,Sort.Direction.valueOf(direction), orderBy);
        Client client = clientRepository.findOne(userSS.getId());
        return repository.findByClient(client, pageRequest);
    }
}
