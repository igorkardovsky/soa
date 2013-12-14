import org.apache.activemq.broker.BrokerService;
//http://activemq.apache.org/how-to-unit-test-jms-code.html

public class Test2 {

	public static void main(String[] args) throws Exception {
		// start embedded Broker
		BrokerService broker1 = new BrokerService();
		broker1.setBrokerName("broker1");
		broker1.setPersistent(false);
		broker1.start();
		
		// start embedded Broker
		BrokerService broker2 = new BrokerService();
		broker2.setPersistent(false);
		broker2.setBrokerName("broker2");
		broker2.start();

	}

}
