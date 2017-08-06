package com.pavikumbhar.javaheart.messaging;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.pavikumbhar.javaheart.model.InventoryResponse;
import com.pavikumbhar.javaheart.service.OrderService;

@Component
public class MessageReceiver implements MessageListener{
	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	private static final String ORDER_RESPONSE_QUEUE = "jms/destinationTwo";
	
	@Autowired
	OrderService orderService;
	
	
	 ObjectMessage msgObj = null;
	
	
	public void receiveMessage(final Message<InventoryResponse> message) throws JMSException {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		MessageHeaders headers =  message.getHeaders();
		LOG.info("Application : headers received : {}", headers);
		
		InventoryResponse response = message.getPayload();
		LOG.info("Application : response received : {}",response);
		
		orderService.updateOrder(response);	
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}





	@Override
	public void onMessage(javax.jms.Message message) {
		try {
		if (message instanceof ObjectMessage) {
			msgObj = (ObjectMessage) message;
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
			//MessageHeaders headers =  message.getHeaders();
			//LOG.info("Application : headers received : {}", headers);
			if(msgObj.getObject() instanceof InventoryResponse){
			InventoryResponse response =(InventoryResponse) msgObj.getObject();
			System.out.println("Application : response received : {}"+response);
					orderService.updateOrder(response);	
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
			}else{
				
			}
		}
		 } catch (Exception ex) {
			 LOG.debug( ex.getMessage());
	}
		
	}
}
