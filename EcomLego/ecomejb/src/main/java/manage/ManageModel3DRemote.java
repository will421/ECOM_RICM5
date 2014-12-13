package manage;

import java.util.List;

import javax.ejb.Remote;

import ecom.ejb.Model3D;

@Remote
public interface ManageModel3DRemote {

	List<Model3D> checkModel3D(String nomM);

	//int addUser(String nom, String prenom);
	
	Model3D modifModel3D(String nomM);

	Model3D addModel3D(String nomM, String theme, String user, byte picture);

	Model3D checkInfoModel(String nomM, String id);

	void removeModel3D(String nomM);
}
