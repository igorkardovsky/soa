import org.apache.activemq.broker.BrokerService;

//http://activemq.apache.org/how-do-i-embed-a-broker-inside-a-connection.html

public class Test3 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BrokerService broker = new BrokerService();
		 
		// configure the broker
		broker.addConnector("tcp://localhost:61616");
		 
		broker.start();
	}

}
