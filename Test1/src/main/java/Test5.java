//http://activemq.apache.org/how-do-i-embed-a-broker-inside-a-connection.html

import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.network.NetworkConnector;
import org.apache.activemq.security.JaasAuthenticationPlugin;


public class Test5 {

	public static void main(String[] args) throws Exception {
		BrokerService broker = new BrokerService();
		broker.setBrokerName("fred");
		broker.setUseShutdownHook(false);
		//Add plugin
		broker.setPlugins(new BrokerPlugin[]{new JaasAuthenticationPlugin()});
		//Add a network connection
		NetworkConnector connector = broker.addNetworkConnector("static://"+"tcp://somehost:61616");
		connector.setDuplex(true);
		broker.addConnector("tcp://localhost:61616");
		broker.start();

	}

}
