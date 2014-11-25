package ecom.ejb.client;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ecom.ejb.ManageUsersRemote;
import ecom.ejb.Users;


public class Database {
	
	ManageUsersRemote userService;
	
	public Database(){

		InitialContext ctx;
		try {
			ctx = new InitialContext();
			userService = (ManageUsersRemote) ctx.lookup("ManageUser");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Users doAddUser(String nom, String prenom){
		Users recup;
		recup = userService.addUser(nom, prenom);
		return recup;
	}
	
	public boolean doCheckusers(String nom, String prenom){
		boolean isAvailable;
		isAvailable = userService.checkUser(nom, prenom);
		return isAvailable;

	}

}
