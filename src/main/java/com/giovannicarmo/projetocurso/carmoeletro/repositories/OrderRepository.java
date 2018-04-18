package com.giovannicarmo.projetocurso.carmoeletro.repositories;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Client;
import com.giovannicarmo.projetocurso.carmoeletro.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer>{

    @Transactional(readOnly = true)
    Page<Order> findByClient(Client client, Pageable pageable);
}
