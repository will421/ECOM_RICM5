package ecom.ejb.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import ecom.ejb.manage.ManageUsersRemote;



@ApplicationPath("/resources")
@Path("rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestResourceService extends Application {

	
    ServiceRemote service;
    ManageUsersRemote manageUser;
	
   public RestResourceService() throws NamingException {
	   InitialContext context = new InitialContext();
		service = (ServiceRemote) context.lookup("Service");
		manageUser = (ManageUsersRemote) context.lookup("ManageUser");
	}
   
   @GET
   @Path("test")
   public String getDate(){ //http://localhost:8080/ecom/resources/rest
       return service.getCurrentDate().toString();
   }
	
   
  @POST
  @Path("users")
  public void createUser(UserJson user) 
  {
	  manageUser.addUserAccount(user.mail, user.mdp, user.adrLivraison, user.adrFacturation, user.numTel, user.numFix,user.rib);
	  manageUser.addUser(user.name, user.surname, user.mail);
	  System.out.println("User added");
  }
   
   
}
