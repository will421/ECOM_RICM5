package ecom.ejb;

import javax.ejb.Remote;

@Remote
public interface ManageUsersRemote {
	boolean rightLogin(String email, String password);

	boolean checkUser(String nomC, String prenomC);

	Users addUser(String nom, String prenom);
}
