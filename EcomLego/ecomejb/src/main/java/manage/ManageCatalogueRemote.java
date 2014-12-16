package manage;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import ecom.ejb.Catalogue;
import ecom.ejb.OriginalPiece;

@Remote
public interface ManageCatalogueRemote {

	/**
	 * liste des catalogues de meme dates
	 * @param dateC
	 * @return la liste des catalogue ayant la mm date
	 */
	List<Catalogue> checkCatalogue(String dateC);

	/**
	 * modifier la date et la ref d un catalogue
	 * @param dateC
	 * @param refC
	 * @return le catalogue modifier
	 */
	Catalogue modifCatalogue(String dateC, String refC);

	/**
	 * ajout d un catalogue
	 * @param dateC
	 * @param refC
	 * @return le catalogue ajoute
	 */
	Catalogue addCatalogue(String dateC, String refC);

	/**
	 * retourne un catalogue particulier
	 * @param dateC
	 * @param refC
	 * @return le catalogue modifie
	 */
	Catalogue checkInfoCatalogue(String dateC, String refC);


	/**
	 * supprime un catalogue
	 * @param dateC
	 * @param refC
	 */
	void removeCatalogue(String dateC, String refC);
	
	public Collection<Catalogue> getCatalogueFromOriginalPiece(long id);

}
