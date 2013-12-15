import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

//http://camel.apache.org/how-do-i-add-a-component.html

public class Test4 {

	public static void main(String[] args) throws Exception {
		SimpleRegistry r = new SimpleRegistry();
		DefaultCamelContext dcc = new DefaultCamelContext(r );
		org.apache.camel.component.jms.JmsComponent component = new org.apache.camel.component.jms.JmsComponent();
		component.setConnectionFactory(new org.apache.activemq.ActiveMQConnectionFactory("vm://localhost?broker.persistent=false"));
		dcc.addComponent("jms1", component);
		r.put("orderBean", new test3.OrderImpl());
		dcc.addRoutes(new RouteBuilder() {
            public void configure() {
                from("jetty:http://127.0.0.1:8080/service").to("jms1:queue:orderService");
                from("jms1:queue:orderService").to("cxfbean:orderBean");
            }
        });
		dcc.setTracing(true);
		dcc.start();
		//ProducerTemplate pt = dcc.createProducerTemplate();
		//pt.sendBody("direct:test", "Hello");
		//dcc.stop();

	}

}
