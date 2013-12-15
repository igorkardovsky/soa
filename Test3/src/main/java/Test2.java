import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

//http://camel.apache.org/tracer.html
public class Test2 {

	public static void main(String[] args) throws Exception {
		DefaultCamelContext dcc = new DefaultCamelContext();
		dcc.addRoutes(new RouteBuilder() {
            public void configure() {
                from("direct:test").to("log:test?level=DEBUG&showAll=true&multiline=true");
            }
        });
		dcc.setTracing(true);
		dcc.start();
		ProducerTemplate pt = dcc.createProducerTemplate();
		pt.sendBody("direct:test", "Hello");
		dcc.stop();

	}

}
