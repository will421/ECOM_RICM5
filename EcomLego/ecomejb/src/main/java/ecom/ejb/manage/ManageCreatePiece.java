package ecom.ejb.manage;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ecom.ejb.CreatePiece;
import ecom.ejb.Picture;

@Stateless(mappedName="ManageCreatePiece") 
public class ManageCreatePiece implements ManageCreatePieceRemote, Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceUnit(unitName="MyFactory")
	protected EntityManagerFactory em;  

	@SuppressWarnings("unchecked")
	@Override
	public List<CreatePiece> checkCreatePiece(String nomCP) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select cp from CreatePiece cp where cp.nameCP = :nameCP");
		query.setParameter("nameCP",nomCP);
		List<CreatePiece> m = null;
		try{
			m = query.getResultList();
		}catch(NoResultException e){

		}
		ema.close();
		return m;
	}

	@Override
	public CreatePiece modifCreatePiece(String nomCP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreatePiece addCreatePiece(String nomCP, String theme, String user,
			byte picture, float priceCP) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Picture p = new Picture();
		p.setNomP(nomCP);
		p.setThemeP(theme);
		p.setUserP(user);
		p.setPicture(picture);

		CreatePiece cp = new CreatePiece();
		cp.setNameCP(nomCP);
		cp.setPriceCP(priceCP);

		cp.setPicture(p);
		p.setCreatePiece(cp);
		ema.persist(cp);
		ema.close();
		return cp;
	}

	@Override
	public CreatePiece checkInfoCreatePiece(String nomCP, String id) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();
		CreatePiece cp = null;
		List<CreatePiece> lcp = checkCreatePiece(nomCP);

		for(CreatePiece c : lcp){
			System.out.println(c.getId());
		}

		for(CreatePiece c : lcp){
			String s = String.valueOf(c.getId());
			if(s.equals(id)) {
				cp=c;
				break;
			}
		}

		ema.close();
		return cp;
	}

	@Override
	public void removeCreatePiece(String nomCP, String id) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();
		CreatePiece cp = checkInfoCreatePiece(nomCP,id);
		if(cp != null){
			if(cp.getPicture()!=null){
				ema.remove(cp.getPicture());
			}
			if(cp.getControlLine()!=null){
				ema.remove(cp.getControlLine());
			}
			ema.remove(cp);
		}
		ema.close();
	}

}
