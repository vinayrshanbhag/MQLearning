package com.vinay.learning.MQLearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@JmsListener(destination="order-queue",containerFactory="warehouseFactory")
	public void sendMessage(String destination, String message){
		jmsTemplate.convertAndSend(destination,message);
	}
}
