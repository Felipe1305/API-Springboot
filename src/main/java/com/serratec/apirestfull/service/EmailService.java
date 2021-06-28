package com.serratec.apirestfull.service;

import org.springframework.mail.SimpleMailMessage;

import com.serratec.apirestfull.domain.Pedido;

public interface EmailService {
public void sendOrderConfirmationEmail(Pedido obj);

void sendEmail(SimpleMailMessage msg);
}
