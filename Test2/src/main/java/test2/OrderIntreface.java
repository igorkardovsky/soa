package test2;
import javax.jws.WebService;

@WebService
public interface OrderIntreface {
	
	Order getOrderById(int id);

}
