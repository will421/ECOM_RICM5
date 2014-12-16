package ecom.ejb.manage;

import java.util.List;

import javax.ejb.Remote;

import ecom.ejb.Catalogue;
import ecom.ejb.OriginalPiece;


@Remote
public interface ManageOriginalPieceRemote {

	OriginalPiece modifOriginalPiece(String nomOP, String idOP);

	OriginalPiece addOriginalPiece(String nomOP, String dateC, String refC, String colorPiece, String themePiece, float pricePiece);

	OriginalPiece checkInfoOriginalPiece(String nomOP);
	
	void removeOriginalPiece(String nomOP);

	OriginalPiece addOriginalPiece2(String namePiece, String dateCat,
			String referenceCat);
	
	List<OriginalPiece> getAllOriginalPiece();
	List<OriginalPiece> getAllOriginalPieceByTheme(String theme);
	List<OriginalPiece> getAllOriginalPieceByColor(String color);
	List<OriginalPiece> getAllOriginalPieceByName(String name);
	
}
