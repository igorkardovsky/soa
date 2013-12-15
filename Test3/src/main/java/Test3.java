import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;


public class Test3 {

	public static void main(String[] args) throws Exception {
		SimpleRegistry r = new SimpleRegistry();
		DefaultCamelContext dcc = new DefaultCamelContext(r );
		r.put("orderBean", new test3.OrderImpl());
		dcc.addRoutes(new RouteBuilder() {
            public void configure() {
                from("jetty:http://127.0.0.1:8080/service").to("cxfbean:orderBean");
            }
        });
		dcc.setTracing(true);
		dcc.start();
		//ProducerTemplate pt = dcc.createProducerTemplate();
		//pt.sendBody("direct:test", "Hello");
		//dcc.stop();

	}

}
