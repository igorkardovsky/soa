package test3;


import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.frontend.ClientFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class OrderClient {

	public static void main(String[] args) {
		LoggingFeature f = new LoggingFeature();
		f.setPrettyLogging(true);

	
	/*	QName serviceName = new QName("http://test3/", "OrderIntreface"); 
		Service s = Service.create(serviceName);  
		QName portName = new QName("http://test3/", "OrderIntreface"); 
		s.addPort(portName, "http://schemas.xmlsoap.org/soap/", "http://127.0.0.1:8080/service");  
		
		OrderIntreface proxy = s.getPort(portName, OrderIntreface.class);
		org.apache.cxf.endpoint.Client client1 =
				org.apache.cxf.frontend.ClientProxy.getClient(proxy);
		client1.g
				org.apache.cxf.endpoint.Endpoint cxfEndpoint2 = client1.getEndpoint();
				org.apache.cxf.jaxws.support.JaxWsEndpointImpl endp = (org.apache.cxf.jaxws.support.JaxWsEndpointImpl)cxfEndpoint2;
				endp.getFeatures().add(f);*/
		
		JaxWsProxyFactoryBean pfb = new JaxWsProxyFactoryBean();
		pfb.setAddress("http://127.0.0.1:8080/service");
		pfb.setServiceClass(OrderIntreface.class);
		pfb.getFeatures().add(f);
		OrderIntreface proxy = (OrderIntreface)pfb.create();
		System.out.println(proxy.getOrderById(10).getDescription());
	}

}
