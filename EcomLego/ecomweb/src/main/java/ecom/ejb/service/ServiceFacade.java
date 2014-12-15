package ecom.ejb.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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

/**
 * Session Bean implementation class ServiceFacade
 */

//@Stateless
@ApplicationPath("/resources")
@Path("hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiceFacade extends Application {

	
    ServiceRemote service;
	
   public ServiceFacade() throws NamingException {
		service = (ServiceRemote) new InitialContext().lookup("Service");
	}
    
    
    @GET
    public String getDate(){ //http://localhost:8080/ecomweb/resources/hello
        return service.getCurrentDate().toString();
    }

    @GET
    @Path("test/{id}") //http://localhost:8080/ecomweb/resources/hello/test/william.bobo@e.ujf-grenoble.fr
    public String getAlone(@PathParam("id")String s){
    	return s;
    }
    
    @GET
    @Path("{id}/{id2}") //http://localhost:8080/ecomweb/resources/hello/truc/muche
    public String getInt(@PathParam("id")String s,@PathParam("id2")String s2){
    	return s+"/"+s2;
    }
    
    @GET
    @Path("{id}-{id2}") //http://localhost:8080/ecomweb/resources/hello/truc-5
    public String getSomething(@PathParam("id")String s,@PathParam("id2")Long id){
    	return s+"/"+new Long(id).toString();
    }
    
    
    @GET
    @Path("person") //http://localhost:8080/ecomweb/resources/hello/person
    public Person getPerson(){
    	System.out.println("Rage");
    	Person p = new Person("name",5);
    	System.out.println("Rage2");
    	return p;
    }
    
    @POST
    @Path("post1")
    public Person savePerson(Person person) 
    //Faire une methode post sur http://localhost:8080/ecomweb/resources/hello/post1 avec {"age":6,"name":"lionel"}
    //J'ai pu le tester avec un plugin chrome
    {
    	System.out.println(person.toString());
    	person.name = person.name+"Response";
    	return person;
    }
    
}
