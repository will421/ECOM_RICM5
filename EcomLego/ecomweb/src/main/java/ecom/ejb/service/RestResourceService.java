package ecom.ejb.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;



@ApplicationPath("/resources")
@Path("rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestResourceService extends Application {

	
    ServiceRemote service;
    //ManageUsersRemote manageUser;
	
   public RestResourceService() throws NamingException {
		service = (ServiceRemote) new InitialContext().lookup("Service");
	}
   
   @GET
   @Path("test")
   public String getDate(){ //http://localhost:8080/ecom/resources/rest
       return service.getCurrentDate().toString();
   }
	
}
