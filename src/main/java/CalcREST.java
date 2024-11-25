
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Consumes("application/json")
@Produces("application/json")
public class CalcREST {
 
	
	String addServerURL = "rmi://" + "192.168.128.2" + "/AddServer"; //Duarte
	//String addServerURL = "rmi://" + "192.168.56.101" + "/AddServer"; //Akash
	
	@POST
	@Path("/listarConsultas")	
    public String listarConsultasServer(String idClient) throws MalformedURLException, RemoteException, NotBoundException {
		
		AddServerIntf addServerIntf = (AddServerIntf)Naming.lookup(addServerURL);
		
		return addServerIntf.listarConsultas(idClient);
    	
    }

		
		
		
		
	
}