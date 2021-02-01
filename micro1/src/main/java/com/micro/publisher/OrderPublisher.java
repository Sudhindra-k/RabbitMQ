package com.micro.publisher;


import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.config.MessagingConfig;
import com.micro.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher{

	@Autowired
	private RabbitTemplate rt;
	
	
	@PostMapping("/{restaurantname}")
	public String bookorder(String order,@PathVariable String restaurantname){
		
		order = UUID.randomUUID().toString();
		
		OrderStatus orderstatus = new OrderStatus(order, "PROCESS","order is in process with"+ restaurantname);
		
		rt.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderstatus);
		
		return "Success";
		
	}
}
