package ecom.ejb.manage;

import javax.ejb.Remote;

import ecom.ejb.OriginalPiece;


@Remote
public interface ManageOriginalPieceRemote {

	OriginalPiece modifOriginalPiece(String nomOP, String idOP);

	OriginalPiece addOriginalPiece(String nomOP, String dateC);

	OriginalPiece checkInfoOriginalPiece(String nomOP);
	
	void removeOriginalPiece(String nomOP);
}
