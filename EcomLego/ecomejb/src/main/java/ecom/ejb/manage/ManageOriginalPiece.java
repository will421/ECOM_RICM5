package ecom.ejb.manage;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ecom.ejb.Catalogue;
import ecom.ejb.CreatePiece;
import ecom.ejb.OriginalPiece;
import ecom.ejb.Picture;
import ecom.ejb.UserAccount;
import ecom.ejb.Users;
@Stateless(mappedName="ManageOriginalPiece") 
public class ManageOriginalPiece implements ManageOriginalPieceRemote, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceUnit(unitName="MyFactory")
	protected EntityManagerFactory em;    

	@Override
	public OriginalPiece modifOriginalPiece(String nomOP, String idOP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OriginalPiece addOriginalPiece(String nomOP,String dateC) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Catalogue c = new Catalogue();
		c.setDateCat(dateC);

		OriginalPiece op = new OriginalPiece();
		op.setNameCP(nomOP);

		op.setCatalogue(c);
		ema.persist(op);
		ema.close();
		return op;
	}

	@Override
	public OriginalPiece checkInfoOriginalPiece(String nomOP) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select op from OriginalPiece op where op.namePO = :nameOP");
		query.setParameter("nameOP",nomOP);
		OriginalPiece op = null;
		try{
			op = (OriginalPiece) query.getSingleResult();
		}catch(NoResultException e){

		}
		ema.close();
		return op;
	}

	@Override
	public void removeOriginalPiece(String nomOP) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();
		OriginalPiece op = checkInfoOriginalPiece(nomOP);
		if(op != null){
			if(op.getControlLines()!=null){
				ema.remove(op.getControlLines());
			}
			ema.remove(op.getCatalogue());
			ema.remove(op);
		}
		ema.close();
	}

}
