import java.rmi.Remote;
import java.rmi.RemoteException;
public interface AddServerIntf extends Remote {
  String listarConsultas(String idClient) throws RemoteException;
  String marcarConsulta(int dia, int mes, int ano, int hora, int clientID, int clinicaID, int especialidadeID) throws RemoteException;
  String removerConsulta(int idConsulta) throws RemoteException;
  
  String listarClinicas() throws RemoteException;
  String listarEspecialidades(int idClinica) throws RemoteException;
  
}
