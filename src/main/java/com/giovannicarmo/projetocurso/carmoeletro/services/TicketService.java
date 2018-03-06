package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.TicketPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TicketService {

    public void compleerTicketPayment(TicketPayment payment, Date orderInstant) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderInstant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setDueDate(calendar.getTime());
    }
}
