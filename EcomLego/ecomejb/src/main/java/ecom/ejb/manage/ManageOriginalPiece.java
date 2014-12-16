package ecom.ejb.manage;

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
import javax.transaction.Transactional;

import ecom.ejb.Catalogue;
import ecom.ejb.OriginalPiece;


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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public OriginalPiece addOriginalPiece(String nomOP, String dateC, String refC) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		OriginalPiece op = new OriginalPiece();
		op.setNameCP(nomOP);

		Query query =  ema.createQuery("select c from Catalogue c where c.dateCat = :dateCat and c.idCat=:idCat");
		query.setParameter("dateCat",dateC);
		query.setParameter("idCat", refC);
		Catalogue c = null;
		try{
			c = (Catalogue) query.getSingleResult();
		}catch(NoResultException e){
			ema.close();
			return null;
		}

		if(c!=null){
			op.setCatalogue(c);
		 	c.setOriginalPiece(op);
		}

		ema.persist(op);
		ema.merge(op);
		ema.flush();
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
	//
	//	@Override
	//	public OriginalPiece addOriginalPiece2(String namePiece) {
	//		// TODO Auto-generated method stub
	//		System.out.println("BEFORE3");
	//
	//		EntityManager ema = em.createEntityManager();
	//		OriginalPiece op = new OriginalPiece();
	//		op.setNameCP(namePiece);
	//
	//		ema.persist(op);
	//		ema.close();
	//		return op;
	//	}

	@Override
	public OriginalPiece addOriginalPiece2(String namePiece, String dateCat,
			String referenceCat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OriginalPiece> getAllOriginalPiece() {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select op from OriginalPiece op");
		List<OriginalPiece> op = null;
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
