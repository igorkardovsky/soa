import java.net.URI;
import java.util.List;

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


public class Test7 {

	public static void main(String[] args)  throws Exception {
		// start embedded Broker
		BrokerService broker1 = new BrokerService();
		broker1.setBrokerName("broker1");
		broker1.setPersistent(false);
		List<TransportConnector> nw1 = broker1.getTransportConnectors();
		for(TransportConnector nwc1:nw1){
			nwc1.setDiscoveryUri(new URI("multicast://default"));
		}
		NetworkConnector connector1 = broker1.addNetworkConnector("multicast://default");
		connector1.setDuplex(true);
		connector1.setName("con1");

		broker1.start();
		
		// start embedded Broker
		BrokerService broker2 = new BrokerService();
		broker2.setPersistent(false);
		broker2.setBrokerName("broker2");
		List<TransportConnector> nw2 = broker2.getTransportConnectors();
		for(TransportConnector nwc2:nw2){
			nwc2.setDiscoveryUri(new URI("multicast://default"));
		}		
		NetworkConnector connector2 = broker2.addNetworkConnector("multicast://default");
		connector2.setDuplex(true);
		connector2.setName("con2");
	
		broker2.start();
		
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
		cf.setBrokerURL("vm://broker1");
		Connection conn = cf.createConnection();
		conn.start();
		Session sess = conn.createSession(false,Session.CLIENT_ACKNOWLEDGE);
		Queue q = sess.createQueue("test1");
		TextMessage tm = sess.createTextMessage("Hello");
		MessageProducer mp = sess.createProducer(q);
		mp.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		mp.send(tm);
		
		cf = new ActiveMQConnectionFactory();
		cf.setBrokerURL("vm://broker2");
		conn = cf.createConnection();
		conn.start();
		sess = conn.createSession(false,Session.CLIENT_ACKNOWLEDGE);
		q = sess.createQueue("test1");
		MessageConsumer mc = sess.createConsumer(q);
		Message m = mc.receive(100);
		if(m!=null){
			TextMessage tm1 = (TextMessage)m;
			System.out.print(tm1.getText());
		}
		else
		{
			System.out.print("No message");
		}
		


	}

}
