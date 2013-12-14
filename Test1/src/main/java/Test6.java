import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;


//http://docs.oracle.com/javaee/1.4/api/javax/jms/Connection.html
//http://docs.oracle.com/javaee/1.4/api/javax/jms/DeliveryMode.html
//http://docs.oracle.com/javaee/1.4/api/javax/jms/MessageProducer.html
//http://docs.oracle.com/javaee/1.4/api/javax/jms/Session.html
	
public class Test6 {

	public static void main(String[] args) throws Exception {
		// start embedded Broker
		BrokerService broker = new BrokerService();
		broker.setBrokerName("broker");
		broker.setPersistent(false);
		broker.start();
		
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
		cf.setBrokerURL("vm://broker");
		Connection conn = cf.createConnection();
		Session sess = conn.createSession(false,Session.CLIENT_ACKNOWLEDGE);
		Queue q = sess.createQueue("test1");
		TextMessage tm = sess.createTextMessage("Hello");
		MessageProducer mp = sess.createProducer(q);
		mp.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		mp.send(tm);
		

	}

}
