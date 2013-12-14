import org.apache.activemq.broker.BrokerService;
// http://activemq.apache.org/how-to-unit-test-jms-code.html

public class Test1 {

	public static void main(String[] args) throws Exception {
		// start embedded Broker
		BrokerService broker = new BrokerService();
		broker.setPersistent(false);
		broker.start();
	}

}
