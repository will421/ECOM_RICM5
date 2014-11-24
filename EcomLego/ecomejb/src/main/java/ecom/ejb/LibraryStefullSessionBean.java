package ecom.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateful
public class LibraryStefullSessionBean implements LibraryStatefullSessionBeanRemote{


    @PersistenceUnit(unitName="User")
    protected EntityManagerFactory emf;
 
    public LibraryStefullSessionBean() {
 
    }
 
    public void addUser(String name, String prenom, int id) {
        Client client = new Client();
        client.setNameC(name);
        client.setPrenomC(prenom); 
		EntityManager em = emf.createEntityManager();

		em.persist(client);
    }

	@Override
	public Client getUser() {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		 
        Client client = (Client)em.find(Client.class, this);
 
        em.close();
 
        return client;
	}

}
