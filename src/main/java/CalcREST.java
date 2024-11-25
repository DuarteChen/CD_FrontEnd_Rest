
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

@POST
@Path("/marcarConsulta")	
public String marcarConsultas_Server(String response) throws MalformedURLException, RemoteException, NotBoundException { //int dia, int mes, int ano, int hora, int clientID, int clinicaID, int especialidadeID
	
	String[] parts = response.split(";");
	
	int dia = Integer.parseInt(parts[0]);
	int mes = Integer.parseInt(parts[1]);
	int ano = Integer.parseInt(parts[2]);
	int hora = Integer.parseInt(parts[3]);
	int clientID = Integer.parseInt(parts[4]);
	int clinicaID = Integer.parseInt(parts[5]);
	int especialidadeID = Integer.parseInt(parts[6]);
	
	AddServerIntf addServerIntf = (AddServerIntf)Naming.lookup(addServerURL);

	//String marcarConsulta(int dia, int mes, int ano, int hora, int clientID, int clinicaID, int especialidadeID)
	return addServerIntf.marcarConsulta(dia, mes, ano, hora, clientID, clinicaID, especialidadeID);

}

@GET
@Path("/listarClinicas")
public String listarClinicas_Server() throws MalformedURLException, RemoteException, NotBoundException {
	   
	
	
	AddServerIntf addServerIntf = (AddServerIntf)Naming.lookup(addServerURL);
    

	return addServerIntf.listarClinicas();

}


@POST
@Path("/listarEspecialidades")
public String listarEspecialidades_Server(String idClinica) throws MalformedURLException, RemoteException, NotBoundException {


    // Parse the number
    int number = Integer.parseInt(idClinica.trim());


	AddServerIntf addServerIntf = (AddServerIntf)Naming.lookup(addServerURL);
    

	return addServerIntf.listarEspecialidades(number);

}

		
		
		
		
	
}