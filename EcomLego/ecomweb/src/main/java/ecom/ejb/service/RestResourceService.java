package ecom.ejb.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import ecom.ejb.Model3D;
import ecom.ejb.OriginalPiece;
import ecom.ejb.manage.ManageCatalogueRemote;
import ecom.ejb.manage.ManageControlLineRemote;
import ecom.ejb.manage.ManageCreatePieceRemote;
import ecom.ejb.manage.ManageModel3DRemote;
import ecom.ejb.manage.ManageOriginalPieceRemote;
import ecom.ejb.manage.ManageUsersRemote;



@ApplicationPath("/resources")
@Path("rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestResourceService extends Application {

	
    ServiceRemote service;
    ManageUsersRemote manageUser;
    ManageCatalogueRemote manageCatalogue;
    ManageControlLineRemote manageControlLine;
    ManageCreatePieceRemote manageCreatePiece;
    ManageModel3DRemote manageModel3D;
    ManageOriginalPieceRemote manageOriginalPiece;
	
   public RestResourceService() throws NamingException {
	   InitialContext context = new InitialContext();
		service = (ServiceRemote) context.lookup("Service");
		manageUser = (ManageUsersRemote) context.lookup("ManageUser");
		manageCatalogue =(ManageCatalogueRemote) context.lookup("ManageCatalogue");
		//manageControlLine = (ManageControlLineRemote) context.lookup("ManageControlLine");
		manageCreatePiece = (ManageCreatePieceRemote) context.lookup("ManageCreatePiece");
		manageModel3D = (ManageModel3DRemote) context.lookup("ManageModel3D");
		manageOriginalPiece = (ManageOriginalPieceRemote) context.lookup("ManageOriginalPiece");
		
	}
   
   @GET
   @Path("test")
   public String getDate(){ //http://localhost:8080/ecom/resources/rest
	   System.out.println("test");
       return service.getCurrentDate().toString();
   }


   @POST
   @Path("users")
   public void createUser(UserJson user) 
   { 
	   user = UserJson.createOne();
	   MessageDigest md = null;
	   try {
		   md = MessageDigest.getInstance("SHA-1");
	   }
	   catch(NoSuchAlgorithmException e) {
		   throw new NullPointerException("NoSuchAlgorithm");
	   } 
	   user.mdp = bytesToHex(md.digest(user.mdp.getBytes()));
	   manageUser.addUserAccount(user.mail, user.mdp, user.adrLivraison, user.adrFacturation, user.numTel, user.numFix,user.rib);
	   manageUser.addUser(user.nom, user.prenom, user.mail);
	   System.out.println("User added");
   }
   
   /*@POST
   @Path("validate")
   public void isValidUser()
*/
   
   @GET
   @Path("products")
   public List<OriginalPiece> getAllProduits()
   {
	   return manageOriginalPiece.getAllOriginalPiece();
   }

   //Pocsbla43
   
   @GET
   @Path("products/byTheme/{theme}")
   public List<OriginalPiece> getProduitsByTheme(@PathParam("theme")String theme)
   {
	   List<OriginalPiece> l = manageOriginalPiece.getAllOriginalPieceByTheme(theme);
	   System.out.println(l.toString());
	   System.out.println(l.size());
	   return l;
   }
   
   @GET
   @Path("products/byColor/{color}")
   public List<OriginalPiece> getProduitsByColor(@PathParam("color")String color)
   {
	   return manageOriginalPiece.getAllOriginalPieceByColor(color);
   }
   
   @GET
   @Path("products/byName/{name}")
   public List<OriginalPiece> getProduitsByName(@PathParam("name")String name)
   {
	   return manageOriginalPiece.getAllOriginalPieceByColor(name);
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
