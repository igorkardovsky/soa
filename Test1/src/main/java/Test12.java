import java.net.URI;

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
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.network.NetworkConnector;

//http://activemq.apache.org/what-is-the-difference-between-discovery-multicast-and-zeroconf.html
public class Test12 {

	public static void main(String[] args) throws Exception {
		BrokerService broker1 = new BrokerService();
		broker1.setBrokerName("broker1");
		broker1.setPersistent(false);
		TransportConnector tc1 = new TransportConnector();
		tc1.setUri(new URI("tcp://127.0.0.1:8080"));
		tc1.setDiscoveryUri(new URI("multicast://default"));
		broker1.addConnector(tc1);

		broker1.start();
		

		
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
		//!!!!!!!!!!
		cf.setBrokerURL("discovery:(multicast://default)");
		Connection conn = cf.createConnection();
		conn.start();
		Session sess = conn.createSession(false,Session.CLIENT_ACKNOWLEDGE);
		Queue q = sess.createQueue("test1");
		TextMessage tm = sess.createTextMessage("Hello");
		MessageProducer mp = sess.createProducer(q);
		mp.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		mp.send(tm);
		
		cf = new ActiveMQConnectionFactory();
		//!!!!!!!!!!!!
		cf.setBrokerURL("discovery:(multicast://default)");
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
