package com.giovannicarmo.projetocurso.carmoeletro.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.PaymentState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private PaymentState paymentState;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;

    public Payment() {
    }

    public Payment(Integer id, PaymentState paymentState, Order order) {
        super();
        this.id = id;
        this.paymentState = paymentState;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}