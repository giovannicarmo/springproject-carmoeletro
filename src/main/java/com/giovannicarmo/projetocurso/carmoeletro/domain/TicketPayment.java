package com.giovannicarmo.projetocurso.carmoeletro.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("paymentWithTicket")
public class TicketPayment extends Payment {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public TicketPayment() {
    }

    public TicketPayment(Integer id, PaymentState paymentState, Order order, Date dueDate, Date paymentDate) {
        super(id, paymentState, order);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
