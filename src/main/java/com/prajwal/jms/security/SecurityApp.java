package com.prajwal.jms.security;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.prajwal.jms.hr.Employee;

public class SecurityApp {

	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
		
		InitialContext init =new InitialContext();
		Topic top = (Topic) init.lookup("topic/empTopic");
		
		try (ActiveMQConnectionFactory amc= new ActiveMQConnectionFactory();
				JMSContext jms= amc.createContext();){
				jms.setClientID("securityApp");
				JMSConsumer consumer = jms.createDurableConsumer(top, "subscription");
				consumer.close();
				Thread.sleep(6000);
				 consumer = jms.createDurableConsumer(top, "subscription");
//				JMSConsumer consumer = jms.createConsumer(top);
				Message message = consumer.receive();
				Employee employee = message.getBody(Employee.class);
				System.out.println(employee.getFname());
				consumer.close();
				jms.unsubscribe("unsubscribe");
		}
	}

}
