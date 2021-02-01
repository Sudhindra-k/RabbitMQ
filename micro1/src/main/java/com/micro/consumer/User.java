package com.micro.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.micro.config.MessagingConfig;
import com.micro.dto.OrderStatus;

@Component
public class User {

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumermsg(OrderStatus os) {
		System.out.println("Message received from queue :" + os);
	}
}
