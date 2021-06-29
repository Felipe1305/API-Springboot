package com.serratec.apirestfull.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.serratec.apirestfull.domain.Cliente;
import com.serratec.apirestfull.domain.Pedido;

public interface EmailService {
public void sendOrderConfirmationEmail(Pedido obj);

void sendEmail(SimpleMailMessage msg);

public void sendOrderConfirmationHtmlEmail(Pedido obj);

void sendHtmlEmail(MimeMessage msg);

void sendNewPasswordEmail(Cliente cliente, String newPass);

}

