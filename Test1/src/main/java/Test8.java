import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.network.NetworkConnector;


public class Test8 {

	public static void main(String[] args) throws Exception {
		BrokerService broker1 = new BrokerService();
		broker1.setBrokerName("broker1");
		broker1.setPersistent(false);
		broker1.addConnector("tcp://127.0.0.1:8080");
		NetworkConnector connector1 = broker1.addNetworkConnector("static:(tcp://127.0.0.1:8081)");
		connector1.setDuplex(true);
		connector1.setName("con1");

		broker1.start();
		
		// start embedded Broker
		BrokerService broker2 = new BrokerService();
		broker2.setPersistent(false);
		broker2.addConnector("tcp://127.0.0.1:8081");
		broker2.setBrokerName("broker2");
		NetworkConnector connector2 = broker2.addNetworkConnector("static:(tcp://127.0.0.1:8080)");
		connector2.setDuplex(true);
		connector2.setName("con2");
	
		broker2.start();
		
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
		cf.setBrokerURL("tcp://127.0.0.1:8080");
		Connection conn = cf.createConnection();
		conn.start();
		Session sess = conn.createSession(false,Session.CLIENT_ACKNOWLEDGE);
		Queue q = sess.createQueue("test1");
		TextMessage tm = sess.createTextMessage("Hello");
		MessageProducer mp = sess.createProducer(q);
		mp.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		mp.send(tm);
		
		cf = new ActiveMQConnectionFactory();
		cf.setBrokerURL("tcp://127.0.0.1:8081");
		conn = cf.createConnection();
		conn.start();
		sess = conn.createSession(false,Session.CLIENT_ACKNOWLEDGE);
		q = sess.createQueue("test1");
		MessageConsumer mc = sess.createConsumer(q);
		Message m = mc.receive(100);
		if(m!=null){
			TextMessage tm1 = (TextMessage)m;
			System.out.print(tm1.getText());
			m.acknowledge();
		}
		else
		{
			System.out.print("No message");
		}

	}

}
