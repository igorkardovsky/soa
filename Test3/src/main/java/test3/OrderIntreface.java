package test3;
import javax.jws.WebService;
//http://fusesource.com/docs/esbent/7.1/camel_cxf/ImplWs-JavaFirst-Annotate.html

@WebService
public interface OrderIntreface {
	
	Order getOrderById(int id);

}
