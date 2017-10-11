package test_apacheMQ;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TestMQFailover {

	public static void startListener2() {
		
		Thread t = new Thread () {
			public void run() {
				try {
				ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
	                         ActiveMQConnection.DEFAULT_USER,
	                         ActiveMQConnection.DEFAULT_PASSWORD,
	                         "tcp://localhost:61616");
//							 "failover:(tcp://localhost:61616)?maxReconnectAttempts=-1&maxReconnectDelay=60000&startupMaxReconnectAttempts=5");
//							"failover:(tcp://localhost:61616)?maxReconnectAttempts=-1&initialReconnectDelay=30000");
//	                         "failover:(tcp://localhost:61616,tcp://localhost:61616)");
	         Connection connection = connectionFactory.createConnection();
	         connection.start();
	         
	         
	         final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
	         Destination destination = session.createQueue("foo.bar");
	         MessageConsumer consumer = session.createConsumer(destination);
				
				    consumer.setMessageListener( new MessageListener() {

				    	public void onMessage(Message arg0) {
							// TODO Auto-generated method stub
							TextMessage message =  ( (TextMessage) arg0);
							try {
								String messageText = message.getText().trim();
//							    log.info( "Receive msg: from " + client.getQueueName() + ", " +  messageText );
//						        processMessage( messageText, client);
								System.out.println(messageText);
							   session.commit();
							} catch (JMSException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch ( Exception e) {
								e.printStackTrace();
//								log.error( "JMS receive message error ." );
							}
							
						}
					});
					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					System.out.println("JMS error : " + e.toString());
				}
				
//				log.info( client.toString() + ", started" );

			}
		};
		
		t.start();
		
		
		System.out.println("start:" + new Date().toLocaleString());
		
		while(true){
			if(t.isAlive()){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				System.out.println("end: " + new Date().toLocaleString());
				break;
			}
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
