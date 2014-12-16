package ecom.ejb.manage;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ecom.ejb.Model3D;
import ecom.ejb.OriginalPiece;
import ecom.ejb.Picture;

@Stateless(mappedName="ManageModel3D") 
public class ManageModel3D implements ManageModel3DRemote, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceUnit(unitName="MyFactory")
	protected EntityManagerFactory em;  

	@SuppressWarnings("unchecked")
	@Override
	public List<Model3D> checkModel3D(String nomM) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select m from Model3D m where m.nameM = :nameM");
		query.setParameter("nameM",nomM);
		List<Model3D> m = null;
		try{
			m = query.getResultList();
		}catch(NoResultException e){

		}
		ema.close();
		return m;
	}

	@Override
	public Model3D modifModel3D(String nomM) {
		// TODO Auto-generated method stub
		System.out.println("Cannot change nothing");
		return null;
	}

	@Override
	public Model3D addModel3D(String nomM, String theme, String user, byte picture, float price3D) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Picture p = new Picture();
		p.setNomP(nomM);
		p.setUserP(user);
		p.setPicture(picture);

		Model3D m = new Model3D();
		m.setNameCP(nomM);
		m.setPrice3D(price3D);
		m.setTheme3D(theme);

		m.setPicture(p);
		p.setModel3D(m);
		ema.persist(m);
		ema.close();
		return m;
	}

	@Override
	public Model3D checkInfoModel(String nomM, String id) {
		// TODO Auto-generated method stub 

		EntityManager ema = em.createEntityManager();
		Model3D m3d=null;
		List<Model3D> lm = checkModel3D(nomM);

		for(Model3D m : lm){
			System.out.println(m.getId());
		}


		for(Model3D m : lm){
			String s = String.valueOf(m.getId());
			if(s.equals(id)) {
				m3d=m;
				break;
			}
		}

		ema.close();
		return m3d;
	}

	@Override
	public void removeModel3D(String nomM) {
		// TODO Auto-generated method stub

		EntityManager ema = em.createEntityManager();
		Model3D m = checkInfoModel(nomM,"12");
		if(m != null){
			if(m.getPicture()!=null){
				ema.remove(m.getPicture());
			}
			if(m.getCommande()!=null){
				ema.remove(m.getCommande());
			}
			ema.remove(m);
		}
		ema.close();
	}

	@Override
	public List<Model3D> getAllModel3DByTheme(String theme) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select m from Model3D m where m.theme3D = :theme3D");
		query.setParameter("theme3D", theme);
		List<Model3D> op = null;
		try{
			op = query.getResultList();
		}catch(NoResultException e){
			ema.close();
			return null;
		}
		ema.close();
		return op;
	}

	@Override
	public List<Model3D> getAllModel3DByName(String name) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select m from Model3D m where m.nameM = :nameM");
		query.setParameter("nameM", name);
		List<Model3D> op = null;
		try{
			op = query.getResultList();
		}catch(NoResultException e){
			ema.close();
			return null;
		}
		ema.close();
		return op;
	}

}
