package ecom.ejb.manage;

import javax.ejb.Remote;

import ecom.ejb.UserAccount;
import ecom.ejb.Users;

@Remote
public interface ManageUsersRemote {
	boolean rightLogin(String email, String password);

	UserAccount checkUser(String mailC);

	//int addUser(String nom, String prenom);

	boolean addUserAccount(String mailU, String mdpU, String shippingAddress,
			String billingAddress, String cellPhone, String fixPhone);
	
	boolean addUserAccountFirstAdmin(String mailU, String mdpU, String shippingAddress,
			String billingAddress, String cellPhone, String fixPhone);
	
	Users modifUser(String mail, String nom);

	void addUser(String nomC, String prenomC, String mailU);

	UserAccount checkInfoUser(String mailIU);

	void removeUser(String checkMail);

	void addAdministrator(String nom, String prenom, String mail);

	void addValidator(String nom, String prenom, String mail);
}
