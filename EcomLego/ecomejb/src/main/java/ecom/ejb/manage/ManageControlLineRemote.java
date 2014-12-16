package ecom.ejb.manage;

import java.util.List;

import javax.ejb.Remote;

import ecom.ejb.ControlLine;

@Remote
public interface ManageControlLineRemote {
	
	ControlLine modifControlLine(String nomM);

	ControlLine addControlLine(String nomM, String theme, String user, byte picture);

	List<ControlLine> getAllControlLine();
	
	void removeControlLine(String nomM);
}
