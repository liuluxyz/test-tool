package test_apacheMQ;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

public class MQClient {

	private static Logger log = Logger.getLogger( MQClient.class.getName() );
	
	final Map<String, Connection> queueListenerMap = Collections.synchronizedMap(new HashMap<String, Connection>());
	final Map<String, Long> msgLastTimeMap = Collections.synchronizedMap(new HashMap<String, Long>());
	final int msgReceiveTimeout = 30000;//ms
	
	public void startListener(final String queueName) {
		log.info("start listener : " + queueName);
		
		Thread t = new Thread () {
			public void run() {
				
				queueListenerMap.put(queueName, null);
				msgLastTimeMap.put(queueName, System.currentTimeMillis());
				
				ActiveMQConnectionFactory fty = new ActiveMQConnectionFactory("tcp://localhost:61616");
				
				try {
					final String clientID = queueName + " - clientID - " + new java.util.Date().toLocaleString();
					Connection con =fty.createConnection();
					con.setClientID(clientID);
					con.start();
					con.setExceptionListener(new ExceptionListener(){

						public void onException(JMSException e) {
							log.error("Connection JMSException : " + e.toString(), e);
						}
						
						}
					);
					
					queueListenerMap.put(queueName, con);
					
					final Session session = con.createSession( true, Session.AUTO_ACKNOWLEDGE );
					
					Topic queue = session.createTopic(queueName);
					
				    MessageConsumer consumer = session.createDurableSubscriber( queue, "" );
				    consumer.setMessageListener( new MessageListener() {

				    	public void onMessage(Message arg0) {
							TextMessage message =  ( (TextMessage) arg0);
							try {
								msgLastTimeMap.put(queueName, System.currentTimeMillis());
								
								String messageText = message.getText().trim();
								log.info("[" + clientID + "] receive msg : " + messageText);
								session.commit();
							} catch (JMSException e) {
								e.printStackTrace();
							} catch ( Exception e) {
								e.printStackTrace();
							}
						}
					});
					
				} catch (JMSException e) {
//					e.printStackTrace();
					log.error("connect fail : " + e.getMessage());
				}
			}
		};
		
		t.setName(queueName + " listener" + new java.util.Date().toLocaleString());
		t.start();
	}
	
	public void startDaemon() {
		Thread t = new Thread () {
			public void run() {
				while(true){
					log.info("daemon thread start to check queue connection...");
					Iterator<String> it = queueListenerMap.keySet().iterator();
					while(it.hasNext()){
						String queueName = it.next();
						log.info("daemon thread start to check queue connection : " + queueName);
						boolean timeout = isTimeout(queueName);
						
						if(timeout == true){
							log.info("timeout is true : " + queueName);
							closeConnection(queueName);
							startListener(queueName);
						}
					}
					
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.setName("my daemon thread");
		t.start();
	}
	
	public void closeConnection(String queueName) {
		log.info("close connection : " + queueName);
		Connection conn = null;
		try {
			conn = queueListenerMap.get(queueName);
			if(conn != null){
				conn.close();	
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn = null;
		}

	}
	
	private boolean isTimeout(String queueName){
		long msgLastTime = msgLastTimeMap.get(queueName);
		long interval = System.currentTimeMillis() - msgLastTime;
		
		if(interval > this.msgReceiveTimeout){
			return true;
		}
		return false;
	}
	
	
	private void sleep(long interval){
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] queueNames = new String[]{
				"foo.bar", 
				"foo.bar", 
//				"a", 
//				"b", 
//				"c", 
//				"d"
		};
		
		MQClient client = new MQClient();
		for(String queueName : queueNames){
			client.startListener(queueName);
			client.sleep(1000);
		}
	
		client.startDaemon();
		
	}

}
