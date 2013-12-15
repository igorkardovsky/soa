import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
//http://camel.apache.org/log.html
public class Test1 {

	public static void main(String[] args) throws Exception {
		DefaultCamelContext dcc = new DefaultCamelContext();
		dcc.addRoutes(new RouteBuilder() {
            public void configure() {
                from("direct:test").to("log:test?level=WARN&showAll=true&multiline=true");
            }
        });

		dcc.start();
		ProducerTemplate pt = dcc.createProducerTemplate();
		pt.sendBody("direct:test", "Hello");
		dcc.stop();

	}
}
