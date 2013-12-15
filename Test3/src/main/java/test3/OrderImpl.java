package test3;

import javax.jws.WebService;

@WebService(endpointInterface="test3.OrderIntreface")
public class OrderImpl implements OrderIntreface{

	public Order getOrderById(int id) {
		// TODO Auto-generated method stub
		Order o = new Order();
		o.setId(id);
		o.setDescription("Test class");
		return o;
	}

}
