package com.leonardo.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.leonardo.cursomc.model.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
}
