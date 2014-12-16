package ecom.ejb.manage;

import java.util.List;
import javax.ejb.Remote;
import ecom.ejb.Model3D;

@Remote
public interface ManageModel3DRemote {

	List<Model3D> checkModel3D(String nomM);
	
	Model3D modifModel3D(String nomM);

	Model3D addModel3D(String nomM, String theme, String user, byte picture, float price3D);

	Model3D checkInfoModel(String nomM, String id);

	void removeModel3D(String nomM);
	
	List<Model3D> getAllModel3DByTheme(String theme);
	
	List<Model3D> getAllModel3DByName(String name);
}
