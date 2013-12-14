import java.net.URI;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.network.NetworkConnector;

//http://activemq.apache.org/networks-of-brokers.html

public class Test10 {

	public static void main(String[] args) throws Exception {
		BrokerService broker1 = new BrokerService();
		broker1.setBrokerName("broker1");
		broker1.setPersistent(false);
		TransportConnector tc1 = new TransportConnector();
		tc1.setUri(new URI("tcp://127.0.0.1:8080"));
		tc1.setDiscoveryUri(new URI("multicast://default"));
		broker1.addConnector(tc1);
		NetworkConnector connector1 = broker1.addNetworkConnector("multicast://default");
		//connector1.setDuplex(true);
		connector1.setName("con1");

		broker1.start();
		
		// start embedded Broker
		BrokerService broker2 = new BrokerService();
		broker2.setPersistent(false);
		TransportConnector tc2 = new TransportConnector();
		tc2.setUri(new URI("tcp://127.0.0.1:8081"));
		tc2.setDiscoveryUri(new URI("multicast://default"));
		broker2.addConnector(tc2);
		broker2.setBrokerName("broker2");
		NetworkConnector connector2 = broker2.addNetworkConnector("multicast://default");
		//connector2.setDuplex(true);
		connector2.setName("con2");
	
		broker2.start();
		
		
		// start embedded Broker
		BrokerService broker3 = new BrokerService();
		broker3.setPersistent(false);
		TransportConnector tc3 = new TransportConnector();
		tc3.setUri(new URI("tcp://127.0.0.1:8082"));
		tc3.setDiscoveryUri(new URI("multicast://default"));
		broker3.addConnector(tc3);
		broker3.setBrokerName("broker3");
		NetworkConnector connector3 = broker3.addNetworkConnector("multicast://default");
		//connector3.setDuplex(true);
		connector3.setName("con3");
	
		broker3.start();

	}

}
