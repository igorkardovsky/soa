import java.net.URI;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.network.NetworkConnector;
//http://activemq.apache.org/multicast-transport-reference.html

public class Test11 {

	public static void main(String[] args) throws Exception {
		BrokerService broker1 = new BrokerService();
		broker1.setBrokerName("broker1");
		broker1.setPersistent(false);
		TransportConnector tc1 = new TransportConnector();
		tc1.setUri(new URI("tcp://127.0.0.1:8080"));
		tc1.setDiscoveryUri(new URI("multicast://default?group=g1"));
		broker1.addConnector(tc1);
		NetworkConnector connector1 = broker1.addNetworkConnector("multicast://default?group=g1");
		//connector1.setDuplex(true);
		connector1.setName("con1");

		broker1.start();
		
		// start embedded Broker
		BrokerService broker2 = new BrokerService();
		broker2.setPersistent(false);
		TransportConnector tc2 = new TransportConnector();
		tc2.setUri(new URI("tcp://127.0.0.1:8081"));
		tc2.setDiscoveryUri(new URI("multicast://default?group=g1"));
		broker2.addConnector(tc2);
		broker2.setBrokerName("broker2");
		NetworkConnector connector2 = broker2.addNetworkConnector("multicast://default?group=g1");
		//connector2.setDuplex(true);
		connector2.setName("con2");
	
		broker2.start();
		
		
		// start embedded Broker
		BrokerService broker3 = new BrokerService();
		broker3.setPersistent(false);
		TransportConnector tc3 = new TransportConnector();
		tc3.setUri(new URI("tcp://127.0.0.1:8082"));
		tc3.setDiscoveryUri(new URI("multicast://default?group=g2"));
		broker3.addConnector(tc3);
		broker3.setBrokerName("broker3");
		NetworkConnector connector3 = broker3.addNetworkConnector("multicast://default?group=g2");
		//connector3.setDuplex(true);
		connector3.setName("con3");
	
		broker3.start();
		
		
		BrokerService broker4 = new BrokerService();
		broker4.setPersistent(false);
		TransportConnector tc4 = new TransportConnector();
		tc4.setUri(new URI("tcp://127.0.0.1:8083"));
		tc4.setDiscoveryUri(new URI("multicast://default?group=g2"));
		broker4.addConnector(tc4);
		broker4.setBrokerName("broker4");
		NetworkConnector connector4 = broker4.addNetworkConnector("multicast://default?group=g2");
		//connector3.setDuplex(true);
		connector4.setName("con4");
	
		broker4.start();

	}

}
