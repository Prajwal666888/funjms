package com.prajwal.jms.hr;

import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class HRApp {

	public static void main(String[] args) throws NamingException {
		
		InitialContext init =new InitialContext();
		Topic top = (Topic) init.lookup("topic/empTopic");
		
		try (ActiveMQConnectionFactory amc= new ActiveMQConnectionFactory();
				JMSContext jms= amc.createContext();){
					Employee employee = new Employee();
					employee.setId(123);
					employee.setFname("Prajwal");
					employee.setLname("Hungi");
					employee.setDesignation("Software Engg");
					employee.setEmail("prajwal329@gmail.com");
					employee.setNumber(6912312);
					for(int i=0;i<10;i++) {
						jms.createProducer().send(top, employee);
							
					}
						System.out.println("Message sent");
		}
	}

}
