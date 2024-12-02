
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

String ficheiroAutenticacao = "/Users/duartechen/eclipse-workspace/CD_FrontEnd_Soap/DadosUser.txt"; //Duarte
//String ficheiroAutenticacao = "C:\\\\Users\\\\akash\\\\eclipse-workspace\\\\CD_FrontEnd_Soap\\\\DadosUser.txt"; //Akash
	
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

@POST
@Path("/listarConsultas")
public String listarConsultas_Server(String idClient) throws MalformedURLException, RemoteException, NotBoundException {



	AddServerIntf addServerIntf = (AddServerIntf)Naming.lookup(addServerURL);
    

	return addServerIntf.listarConsultas(idClient);

}

@POST
@Path("/removerConsulta")
public String removerConsulta_Server(String idConsulta) throws MalformedURLException, RemoteException, NotBoundException {


	int idConsultaInteiro = Integer.parseInt(idConsulta);
	AddServerIntf addServerIntf = (AddServerIntf)Naming.lookup(addServerURL);
    

	return addServerIntf.removerConsulta(idConsultaInteiro);

}

@POST
@Path("/registar")
public String Registar(String response) throws IOException {
    // Path to the file where users will be saved
    
    File file = new File(ficheiroAutenticacao);
    
    
    
    String[] responseParts = response.split(";");
	
	String userName = responseParts[0];
	String password = responseParts[1];
    
    

    // Create the file if it does not exist
    if (!file.exists()) {
        boolean created = file.createNewFile();
        if (!created) {
            throw new IOException("Erro ao criar o arquivo DadosUser.txt");
        }
    }

    // Check if the user already exists
    if (usuarioExiste(userName)) {
        return "Usuário já existe!";
    } else {
        // Generate the hashed password
        String hashedPassword = hashPassword(password);

        // Get the next ID
        int nextId = getNextId(file);

        // Write the new user to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(nextId + ":" + userName + ":" + hashedPassword); // Save the user in id:userName:hashedPassword format
            writer.newLine();
        }
        return "Usuário registrado com sucesso!";
    }
}

// Method to hash a password using SHA-256
@POST
@Path("/hashPassword")
private String hashPassword(String password) {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException("Erro ao gerar hash da senha", e);
    }
}
// Método para verificar se o usuário já existe
@POST
@Path("/usuarioExiste")
private boolean usuarioExiste(String userName) throws IOException {
   
	try (BufferedReader reader = new BufferedReader(new FileReader(ficheiroAutenticacao))) {
    	
    	
    	String line;
        while ((line = reader.readLine()) != null) {
            // Verificar se o nome de usuário já existe (compara apenas o nome do usuário antes dos dois pontos)
            String storedUserName = line.split(":")[1]; // O username está no segundo campo após o ID
            if (storedUserName.equals(userName)) {
                return true; // Usuário encontrado
            }
        }
    }
    return false; // Usuário não encontrado
}

// Método para obter o próximo ID disponível (incrementando a partir do maior ID atual)
@POST
@Path("/getNextId")
private int getNextId(File file) throws IOException {
    int maxId = 0;

    // Ler todas as linhas do arquivo para determinar o maior ID
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String idStr = line.split(":")[0]; // O ID está no primeiro campo antes dos dois pontos
            try {
                int id = Integer.parseInt(idStr);
                maxId = Math.max(maxId, id); // Atualiza o maior ID encontrado
            } catch (NumberFormatException e) {
                // Ignora linhas que não possam ser convertidas para inteiro (caso haja alguma linha inválida)
            }
        }
    }

    return maxId + 1; // Retorna o próximo ID disponível
}

@POST
@Path("/autenticar")
public String autenticar(String response) throws IOException {
    // Read the file and verify if the username and hashed password match
    
	String[] responseParts = response.split(";");
	
	String userName = responseParts[0];
	String password = responseParts[1];
	
	
	try (BufferedReader reader = new BufferedReader(new FileReader(ficheiroAutenticacao))) { 
    
		String line;
        String hashedPassword = hashPassword(password); // Hash the input password for comparison

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            String storedId = parts[0];
            String storedUserName = parts[1];
            String storedPassword = parts[2];

            // If the username and hashed password match
            if (storedUserName.equals(userName) && storedPassword.equals(hashedPassword)) {
                return storedId; // Return the ID of the authenticated user
            }
        }
    }
    System.out.println("Credenciais erradas");
    return "0"; // Invalid username or password
}
		
		
		
		
	
}