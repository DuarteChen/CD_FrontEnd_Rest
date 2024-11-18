
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Consumes("application/json")
@Produces("application/json")
public class CalcREST {
 
	@POST
	@Path("/add")	
    public String add(String data) {
    		  Integer number1=0, number2=0, number3=0;
    	      String[] info = data.split(",");
    	      number1=Integer.valueOf(info[0]);
    	      number2=Integer.valueOf(info[1]);
    	      number3=number1+number2;
    	      String result = ""+number3;
    	      return result;
    	
    }
}