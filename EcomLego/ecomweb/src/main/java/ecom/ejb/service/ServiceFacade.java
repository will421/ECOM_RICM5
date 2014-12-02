package ecom.ejb.service;

import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/**
 * Session Bean implementation class ServiceFacade
 */

@ApplicationPath("/resources")
@Path("hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiceFacade extends Application {

    //@EJB
    //Service service;
	
    @GET
    public String getDate(){
    	return "truc";
        //return service.getCurrentDate().toString();
    }

}
