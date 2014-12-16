package ecom.ejb.client;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ecom.ejb.CreatePiece;
import ecom.ejb.Model3D;
import ecom.ejb.OriginalPiece;
import ecom.ejb.UserAccount;
import ecom.ejb.Users;
import ecom.ejb.manage.ManageCreatePieceRemote;
import ecom.ejb.manage.ManageModel3DRemote;
import ecom.ejb.manage.ManageOriginalPieceRemote;
import ecom.ejb.manage.ManageUsersRemote;


public class Database {


	ManageUsersRemote userService;
	ManageModel3DRemote model;
	ManageCreatePieceRemote createPiece;
	ManageOriginalPieceRemote originalPiece;

	public Database(){

		InitialContext ctx;
		try {
			ctx = new InitialContext();
			userService = (ManageUsersRemote) ctx.lookup("ManageUser");
			model = (ManageModel3DRemote) ctx.lookup("ManageModel3D");
			createPiece = (ManageCreatePieceRemote) ctx.lookup("ManageCreatePiece");
			originalPiece = (ManageOriginalPieceRemote) ctx.lookup("ManageOriginalPiece");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/*******************************************
	 * 	USER METHODS
	 ******************************************/


	public void doAddUser(String nom, String prenom, String mail){
		userService.addUser(nom, prenom, mail);
	}

	public UserAccount doCheckusers(String mail){
		return userService.checkUser(mail);
	}

	public boolean doAddUserAccount(String mailU, String mdpU, String shippingAddress, String billingAddress, String cellPhone, String fixPhone){
		return userService.addUserAccount(mailU, mdpU, shippingAddress, billingAddress, cellPhone, fixPhone);
	}
	

	public boolean doAddUserAccountFirstAdmin(String mailU, String mdpU, String shippingAddress, String billingAddress, String cellPhone, String fixPhone){
		return userService.addUserAccountFirstAdmin(mailU, mdpU, shippingAddress, billingAddress, cellPhone, fixPhone);
	}

	public Users doModifUsers(String mail, String nom){
		return userService.modifUser(mail, nom);
	}

	public UserAccount doCheckInfoUsers(String mailIU) {
		// TODO Auto-generated method stub
		return userService.checkInfoUser(mailIU);
	}

	public void doRemoveUser(String checkMail) {
		// TODO Auto-generated method stub
		userService.removeUser(checkMail);
	}

	public void doAddAdministrator(String nom, String prenom, String mail) {
		// TODO Auto-generated method stub
		userService.addAdministrator(nom, prenom, mail);
	}

	public void doAddValidator(String nom, String prenom, String mail) { 
		// TODO Auto-generated method stub
		userService.addValidator(nom, prenom, mail);
	}


	/*******************************************
	 * 	MODEL3D METHODS
	 ******************************************/

	public Model3D doCheckInfoModel3D(String nameModel, String id) {
		// TODO Auto-generated method stub
		return model.checkInfoModel(nameModel, id);
	}

	public Model3D doAddModel3D(String nomM, String theme, String user, byte picture){
		return model.addModel3D(nomM, theme, user, picture);
	}

	public List<Model3D> doCheckModel3D(String nomM){
		return model.checkModel3D(nomM);
	}

	public Model3D doModifModel3D(String nomM){
		return model.modifModel3D(nomM);
	}

	public void doRemoveModel3D(String nomM){
		model.removeModel3D(nomM);
	}


	/*******************************************
	 * 	CREATEPIECE METHODS
	 ******************************************/

	public List<CreatePiece> doCheckCreatePiece(String nomCP){
		return createPiece.checkCreatePiece(nomCP);
	}

	public CreatePiece doModifCreatePiece(String nomCP){
		return createPiece.modifCreatePiece(nomCP);
	}

	public CreatePiece doAddCreatePiece(String nomCP, String theme, String user, byte picture){
		return createPiece.addCreatePiece(nomCP, theme, user, picture);
	}

	public CreatePiece doCheckInfoPiece(String nomCP, String id){
		return createPiece.checkInfoCreatePiece(nomCP, id);
	}

	public void doRemoveCreatePiece(String nomM, String id){
		createPiece.removeCreatePiece(nomM,id);
	}


	/*******************************************
	 * 	ORIGINALPIECE METHODS
	 ******************************************/
	
	
	public OriginalPiece doModifOriginalPiece(String nomOP, String idOP){
		return originalPiece.modifOriginalPiece(nomOP, idOP);
	}

	public OriginalPiece doAddOriginalPiece(String nomOP, String dateC){
		return originalPiece.addOriginalPiece(nomOP,dateC);
	}

	public OriginalPiece doCheckInfoOriginalPiece(String nomOP){
		return originalPiece.checkInfoOriginalPiece(nomOP);
	}
	
	public void doRemoveOriginalPiece(String nomOP){
		originalPiece.removeOriginalPiece(nomOP);
	}


}
