package ecom.ejb.manage;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ecom.ejb.Catalogue;
import ecom.ejb.ControlLine;
import ecom.ejb.OriginalPiece;

public class ManageControlLine implements ManageControlLineRemote, Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceUnit(unitName="MyFactory")
	protected EntityManagerFactory em;

	@Override
	public ControlLine modifControlLine(String nomM) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControlLine addControlLine(String nomM, String theme, String user,
			byte picture) {
		// TODO Auto-generated method stub
		EntityManager ema = em.createEntityManager();

		ControlLine cl = new ControlLine();
		cl.setNomLC(nomM);
		cl.setTheme(theme);
		
		
		
		
		return null;
	}

	@Override
	public List<ControlLine> getAllControlLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeControlLine(String nomM) {
		// TODO Auto-generated method stub

	}

}
