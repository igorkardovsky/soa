package test3;

public class OrderServer {

	public static void main(String[] args) throws Exception {
		javax.xml.ws.Endpoint.publish("http://127.0.0.1:8080/service", new OrderImpl());

	}

}
