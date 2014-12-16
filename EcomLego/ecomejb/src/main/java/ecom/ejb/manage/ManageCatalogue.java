package ecom.ejb.manage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ecom.ejb.Catalogue;
import ecom.ejb.OriginalPiece;

@Stateless(mappedName="ManageCatalogue") 
public class ManageCatalogue implements ManageCatalogueRemote, Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceUnit(unitName="MyFactory")
	protected EntityManagerFactory em;  

	@Override
	public List<Catalogue> checkCatalogue(String dateC) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select c from Catalogue c where c.datCat = :datCat");
		query.setParameter("datCat",dateC);
		List<Catalogue> c = null;
		try{
			c = query.getResultList();
		}catch(NoResultException e){

		}
		ema.close();
		return c;
	}

	@Override
	public Catalogue modifCatalogue(String dateC, String refC) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Catalogue addCatalogue(String dateC, String refC) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Catalogue c = new Catalogue();
		c.setDateCat(dateC);
		c.setRefCat(refC);

		ema.persist(c);
		ema.flush();
		ema.close();
		return c;
	}
	
	public Collection<Catalogue> getCatalogueFromOriginalPiece(long id){
		EntityManager ema = em.createEntityManager();
		Query query =  ema.createQuery("select o from OriginalPiece o where o.idPO = :id");
		query.setParameter("id",id);
		OriginalPiece c =null;
		try{
			c= (OriginalPiece) query.getSingleResult();
		}catch(NoResultException e){
			ema.close();
			return null;
		}
		ema.close();		
		return c.getCatalogue();
	}

	@Override
	public Catalogue checkInfoCatalogue(String dateC, String refC) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		Query query =  ema.createQuery("select c from Catalogue c where c.dateCat = :dateCat and c.idCat=:idCat");
		query.setParameter("dateCat",dateC);
		query.setParameter("idCat", refC);
		Catalogue c = null;
		try{
			c = (Catalogue) query.getSingleResult();
		}catch(NoResultException e){

		}
		ema.close();
		return c;
	}

	@Override
	public void removeCatalogue(String dateC, String refC) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();
		Catalogue c = checkInfoCatalogue(dateC,refC);
		if(c != null){
			if(c.getOriginalPiece().isEmpty()){
				for(OriginalPiece op : c.getOriginalPiece()){
					ema.remove(op);
				}
			}
			ema.remove(c);
		}
		ema.close();
	}


}
