package com.prajwal.jms.wellness;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.prajwal.jms.hr.Employee;

public class WellnessApp {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext init =new InitialContext();
		Topic top = (Topic) init.lookup("topic/empTopic");
		
		try (ActiveMQConnectionFactory amc= new ActiveMQConnectionFactory();
				JMSContext jms= amc.createContext();){
				JMSConsumer consumer = jms.createSharedConsumer(top,"sharedConsumer");

				JMSConsumer consumer2 = jms.createSharedConsumer(top,"sharedConsumer");
				for(int i=0;i<10;i+=2) {
				Message message = consumer.receive();
				Employee employee = message.getBody(Employee.class);
				System.out.println("Employee1 "+employee.getFname());
			
				Message message2 = consumer.receive();
				Employee employee2 = message2.getBody(Employee.class);
				System.out.println("Employee 2"+employee2.getFname());
			
				
				}
			}
	}

}
