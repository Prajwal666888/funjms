package com.prajwal.jms.payroll;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.prajwal.jms.hr.Employee;

public class PayrollApp {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext init =new InitialContext();
		Topic top = (Topic) init.lookup("topic/empTopic");
		
		try (ActiveMQConnectionFactory amc= new ActiveMQConnectionFactory();
				JMSContext jms= amc.createContext();){
				JMSConsumer consumer = jms.createConsumer(top);
				Message message = consumer.receive();
				Employee employee = message.getBody(Employee.class);
				System.out.println(employee.getFname());
		}
	}

}
