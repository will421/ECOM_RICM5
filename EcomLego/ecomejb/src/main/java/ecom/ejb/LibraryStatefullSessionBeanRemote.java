package ecom.ejb;

import javax.ejb.Remote;

@Remote
public interface LibraryStatefullSessionBeanRemote {
	void addUser(String nomU, String prenomU, int idU);
	Client getUser();
}
