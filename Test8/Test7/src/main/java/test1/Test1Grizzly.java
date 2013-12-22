package test1;
//https://jersey.java.net/documentation/latest/deployment.html#deployment.javase

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Test1Grizzly {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
	    ResourceConfig config = new ResourceConfig(MyResource.class);
	
	    HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri,config);
	    server.start();
	    //http://stackoverflow.com/questions/6860661/jersey-print-the-actual-request
	    //http://stackoverflow.com/questions/2332515/how-to-get-jersey-logs-at-server
	    Client c = ClientBuilder.newClient();
	    c.register(new LoggingFilter());

	    WebTarget target = c.target(baseUri);
	    String responseMsg = target.path("myresource").request().get(String.class);
	    System.out.println("Response:"+responseMsg);
	    System.in.read();

	    server.stop();
	}

}
