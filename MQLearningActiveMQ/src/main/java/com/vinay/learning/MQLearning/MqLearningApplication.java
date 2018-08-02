package com.vinay.learning.MQLearning;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;


@EnableJms
@SpringBootApplication
public class MqLearningApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(MqLearningApplication.class, args);
		Sender sender = context.getBean(Sender.class);
		
		sender.sendMessage("order-queue","Hello!");
	}
	
	
	@Bean
	public JmsListenerContainerFactory warehouseFactory(ConnectionFactory factory, 
			                             DefaultJmsListenerContainerFactoryConfigurer configurer){
		
		DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
		configurer.configure(containerFactory, factory);
		return containerFactory;
		
	}
	
	
	public ActiveMQConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory connectionFactory = 
				new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		
		return connectionFactory;
	}
	
	@Bean
    public JmsTemplate jmsTemplate(){
		return new JmsTemplate(connectionFactory());
	}
}
