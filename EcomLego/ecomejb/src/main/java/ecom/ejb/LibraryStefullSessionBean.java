package ecom.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateful
public class LibraryStefullSessionBean implements LibraryStatefullSessionBeanRemote{


    @PersistenceUnit(unitName="MyFactory")
    protected EntityManagerFactory emf;
 
    public LibraryStefullSessionBean() {
 
    }
 
    public void addUser(String name, String prenom, int id) {
        Users client = new Users();
        client.setNameC(name);
        client.setPrenomC(prenom); 
		EntityManager em = emf.createEntityManager();

		em.persist(client);
    }

	@Override
	public Users getUser() {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		 
        Users client = (Users)em.find(Users.class, this);
 
        em.close();
 
        return client;
	}

}
