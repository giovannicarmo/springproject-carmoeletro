package com.giovannicarmo.projetocurso.carmoeletro.domain;

import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.PaymentState;

import javax.persistence.Entity;

@Entity
public class CardPayment extends Payment {
    private static final long serialVersionUID = 1L;

    private Integer instalmentsNumber;

    public CardPayment() {
    }

    public CardPayment(Integer id, PaymentState paymentState, Order order, Integer instalmentsNumber) {
        super(id, paymentState, order);
        this.instalmentsNumber = instalmentsNumber;
    }

    public Integer getInstalmentsNumber() {
        return instalmentsNumber;
    }

    public void setInstalmentsNumber(Integer instalmentsNumber) {
        this.instalmentsNumber = instalmentsNumber;
    }
}
