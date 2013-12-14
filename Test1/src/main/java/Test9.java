import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.network.NetworkConnector;


public class Test9 {

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

	}

}
