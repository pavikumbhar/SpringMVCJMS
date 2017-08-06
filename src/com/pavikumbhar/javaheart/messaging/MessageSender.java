package com.pavikumbhar.javaheart.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.pavikumbhar.javaheart.model.Order;

@Component
public class MessageSender {

	@Autowired
	JmsTemplate jmsTemplateForQueueOne;

	public void sendMessage(final Order order) {

		jmsTemplateForQueueOne.send(new MessageCreator(){
				@Override
				public Message createMessage(Session session) throws JMSException{
					ObjectMessage objectMessage = session.createObjectMessage(order);
					return objectMessage;
				}
			});
	}

}
