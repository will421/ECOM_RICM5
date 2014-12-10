package ecom.ejb.client;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import manage.ManageUsersRemote;
import ecom.ejb.UserAccount;
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
	
	public void doAddUser(String nom, String prenom, String mail){
		userService.addUser(nom, prenom, mail);
	}
	
	public UserAccount doCheckusers(String mail){

		return userService.checkUser(mail);

	}
	
	public boolean doAddUserAccount(String mailU, String mdpU, String shippingAddress, String billingAddress, String cellPhone, String fixPhone){
		return userService.addUserAccount(mailU, mdpU, shippingAddress, billingAddress, cellPhone, fixPhone);
	}
	
	public Users doModifUsers(String mail, String nom){
		return userService.modifUser(mail, nom);
	}

	public UserAccount doCheckInfoUsers(String mailIU) {
		// TODO Auto-generated method stub
		return userService.checkInfoUser(mailIU);
	}

	public void doRemoveUser(String checkMail) {
		// TODO Auto-generated method stub
		userService.removeUser(checkMail);
	}

	public void doAddAdministrator(String nom, String prenom, String mail) {
		// TODO Auto-generated method stub
		userService.addAdministrator(nom, prenom, mail);
	}

	public void doAddValidator(String nom, String prenom, String mail) { 
		// TODO Auto-generated method stub
		userService.addValidator(nom, prenom, mail);
	}

}
