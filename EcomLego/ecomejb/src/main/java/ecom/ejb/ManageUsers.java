package ecom.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ecom.ejb.Users;


@Stateless(mappedName="ManageUser") 
public class ManageUsers implements ManageUsersRemote, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceUnit(unitName="MyFactory")
	protected EntityManagerFactory em;    

	public Users addUser(String nomC, String prenomC){

		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select u from Users u where u.nameC = :nom AND u.prenomC = :prenom");
		query.setParameter("nom", nomC);
		query.setParameter("prenom", prenomC);

		Users u = null;
		try{
			query.getSingleResult();
		}catch(NoResultException e){
			u = new Users();
			u.setNameC(nomC);
			u.setPrenomC(prenomC);
			ema.persist(u);
		}
		ema.close();
		return u;
	}

	@Override
	public boolean checkUser(String nomC, String prenomC) {
		boolean exist = false;
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select u from Users u where u.nameC = :nom AND u.prenomC = :prenom");
		query.setParameter("nom", nomC);
		query.setParameter("prenom", prenomC);

		Users u = null;
		try{
			u = (Users) query.getSingleResult();
		}catch(NoResultException e){

		}
		if(u != null){
			exist = true;
		}
		ema.close();
		return exist;
	}    

	@SuppressWarnings("unchecked")
	public List<Users> getUsers(){
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select u from Users u");
		List<Users> usersList = null;
		try{
			usersList = query.getResultList();
		}catch(NoResultException e){
			usersList = null;
		}

		return usersList;
	}

	public int removeUser(String mail){
		EntityManager ema = em.createEntityManager();

		int result = 1;

		Query query =  ema.createQuery("select u from Users u where u.mailU = :mail");
		query.setParameter("mail", mail);

		Users u = null;
		try{
			u = (Users) query.getSingleResult();
		}catch(NoResultException e){

		}
		if(u != null){
			ema.remove(u);
			result = 0;
		}else{
			result = 1;
		}
		ema.close();

		return result;
	}

	@Override
	public boolean rightLogin(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
