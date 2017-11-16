package com.giovannicarmo.projetocurso.carmoeletro.repositories;

import com.giovannicarmo.projetocurso.carmoeletro.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
}
