package ecom.ejb;

import javax.ejb.Remote;

@Remote
public interface ManageUsersRemote {
	boolean rightLogin(String email, String password);

	UserAccount checkUser(String nomC);

	Users addUser(String nom, String prenom);

	boolean addUserAccount(String mailU, String mdpU, String shippingAddress,
			String billingAddress, int cellPhone, int fixPhone);
}
