package ecom.ejb.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	   MessageDigest md = null;
	   try {
		   md = MessageDigest.getInstance("SHA-1");
	   }
	   catch(NoSuchAlgorithmException e) {
		   throw new NullPointerException("NoSuchAlgorithm");
	   } 
	   user.mdp = bytesToHex(md.digest(user.mdp.getBytes()));
	   manageUser.addUserAccount(user.mail, user.mdp, user.adrLivraison, user.adrFacturation, user.numTel, user.numFix,user.rib);
	   manageUser.addUser(user.name, user.surname, user.mail);
	   System.out.println("User added");
   }

   
   final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
   public static String bytesToHex(byte[] bytes) {
       char[] hexChars = new char[bytes.length * 2];
       for ( int j = 0; j < bytes.length; j++ ) {
           int v = bytes[j] & 0xFF;
           hexChars[j * 2] = hexArray[v >>> 4];
           hexChars[j * 2 + 1] = hexArray[v & 0x0F];
       }
       return new String(hexChars);
   }
   
}
