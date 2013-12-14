import java.net.URI;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;

//http://activemq.apache.org/how-do-i-embed-a-broker-inside-a-connection.html

public class Test4 {

	public static void main(String[] args) throws Exception {
		BrokerService broker = new BrokerService();
		 
		TransportConnector connector = new TransportConnector();
		connector.setUri(new URI("tcp://localhost:61616"));
		broker.addConnector(connector);
		broker.start();

	}

}
