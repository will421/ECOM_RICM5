package ecom.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int addUser(String nomC, String prenomC, String mailU){
		EntityManager ema = em.createEntityManager();
		/*Users u = null;
		u = new Users();
		u.setNameC(nomC);
		u.setPrenomC(prenomC);		
		ema.persist(u);
		ema.close();
		return u;*/

		Query query =  ema.createQuery("select u from UserAccount u where u.mailU = :mail");
		query.setParameter("mail",mailU);
		UserAccount a = null;
		try{
			a = (UserAccount) query.getSingleResult();
		}catch(NoResultException e){
			return 2;
		}
		Users u = new Users();
		u.setNameC(nomC);
		u.setPrenomC(prenomC);		
		u.setUserAccount(a);
		a.setClient(u);
		ema.persist(u);
		ema.close();
		return 0;
	}

	@Override
	public UserAccount checkUser(String mailU) {
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select u from UserAccount u where u.mailU = :mail");
		query.setParameter("mail",mailU);

		UserAccount ua = null;
		try{
			ua = (UserAccount) query.getSingleResult();
		}catch(NoResultException e){

		}
		ema.close();
		return ua;
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

	@Override
	public boolean addUserAccount(String mailU, String mdpU,
			String shippingAddress, String billingAddress, int cellPhone,
			int fixPhone) {
		// TODO Auto-generated method stub

		boolean isCreate = false;

		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select ua from UserAccount ua where ua.mailU = :mail");
		query.setParameter("mail", mailU);

		UserAccount ua = null;
		try{
			ua = (UserAccount) query.getSingleResult();
		}catch(NoResultException e){
			isCreate = true;
			ua = new UserAccount();
			ua.setBillingAddress(billingAddress);
			ua.setCellPhone(cellPhone);
			ua.setFixPhone(fixPhone);
			ua.setMdpU(mdpU);
			ua.setShippingAddress(shippingAddress);
			ua.setMailU(mailU);

			ema.persist(ua);
		}
		ema.close();
		return isCreate;


	}

	@Override
	public Users modifUser(String mail, String nom) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select u from UserAccount u where u.mailU = :mail");

		query.setParameter("mail",mail);

		UserAccount ua = null;
		try{
			ua = (UserAccount) query.getSingleResult();
			ua.getClient().setNameC(nom);
		}catch(NoResultException e){
			System.out.println("modif user fail");
		}
		ema.merge(ua.getClient());
		ema.close();
		return ua.getClient();
	}

	@Override
	public UserAccount checkInfoUser(String mailIU) {
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select u from UserAccount u where u.mailU = :mail");

		query.setParameter("mail",mailIU);

		UserAccount ua = null;
		try{
			ua = (UserAccount) query.getSingleResult();
		}catch(NoResultException e){
			System.out.println("checkInfoUser fail");
		}
		ema.close();
		return ua;
	}


}
