package manage;

import java.util.List;

import javax.ejb.Remote;

import ecom.ejb.CreatePiece;
@Remote
public interface ManageCreatePieceRemote {
	List<CreatePiece> checkCreatePiece(String nomCP);
	
	CreatePiece modifCreatePiece(String nomCP);

	CreatePiece addCreatePiece(String nomCP, String theme, String user, byte picture);

	CreatePiece checkInfoCreatePiece(String nomCP, String id);

	void removeCreatePiece(String nomM, String id);
}
