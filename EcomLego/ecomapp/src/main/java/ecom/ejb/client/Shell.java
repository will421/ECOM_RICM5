package ecom.ejb.client;

import java.io.Console;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

import ecom.ejb.Catalogue;
import ecom.ejb.CreatePiece;
import ecom.ejb.Model3D;
import ecom.ejb.OriginalPiece;
import ecom.ejb.UserAccount;
import ecom.ejb.Users;

public class Shell {

	public Shell(){
		Database dbq = new Database();
		boolean isAdmin =false;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n/*******************************************/");
		System.out.println("\n/********* WELCOME TO SHELL PROMPT *********/");
		System.out.println("\n/*******************************************/");
		System.out.print("\n\nUser mode : Type your command : ");

		/*
		 * gerer le multicompte acces shell
		 * 
		 * */

		while(true){

			if(!isAdmin){
				System.out.print("\n user> ");
				String command = sc.nextLine();
				Console console = System.console();
				switch(command){
				case "/help":
					helpCommandUser();
					break; 
				case "/adminMode":
					System.out.print("\n Enter the admin mail:\n > ");
					String checkMail = sc.nextLine();
					UserAccount u = dbq.doCheckusers(checkMail);
//					if(u!=null && u.getAdministrator()!=null){
					if(u!=null){
						String adminpwd = new String (console.readPassword(" > Enter a password : "));
						if(u.getMdpU().equals(DigestUtils.sha1Hex(adminpwd))){
							isAdmin=true;
							System.out.println("\n\n\n/*******************************************/");
							System.out.println("\n/********* WELCOME TO ADMIN PROMPT *********/");
							System.out.println("\n/*******************************************/");
						}
						else {
							System.out.print(" Wrong password");
						}
					} else {
						System.out.println(" this admin do not exist");
					}
					break;
				case "/exit" :
					System.out.println(" #### Thanks and Bye!");
					System.exit(0);
					break;
				case "/checkUser":
					System.out.print("\n Enter the user mail:\n > ");
					String checkMailnoAdmin = sc.nextLine();
					UserAccount uNoAdmin = dbq.doCheckusers(checkMailnoAdmin);

					if(uNoAdmin!=null){
						System.out.println("User exist : "+uNoAdmin);
					} else {
						System.out.println("this user do not exist");
					}
					break;
				case "/modifUser":
					System.out.print("\n Enter the user mail:\n > ");
					String mailC = sc.nextLine();
					System.out.println("Set lastname : ");
					String newLastName = sc.nextLine();
					Users myU = dbq.doModifUsers(mailC, newLastName);
					System.out.println("user prenom : "+myU.getPrenomC());
					System.out.println("user nom : "+myU.getNameC());
					break;
				case "/checkInfoUser":
					System.out.print("\n Enter the user mail:\n > ");
					String mailIU = sc.nextLine();
					UserAccount myIU = dbq.doCheckInfoUsers(mailIU);
					System.out.println("Info user : "+myIU);
					break;
				case "/checkInfoModel3D":
					System.out.print("\n Enter the Model3D name:\n > ");
					String nameModel = sc.nextLine();
					System.out.print("\n Enter the Model3D id:\n > ");
					String nameID = sc.nextLine();
					Model3D m = dbq.doCheckInfoModel3D(nameModel,nameID );
					if(m!=null) System.out.println("Info model : "+m);
					else System.out.println("this model dosn't exist");					
					break;
				case "/checkCreatePiece":
					System.out.print("\n Enter the piece name:\n >");
					String namePiece = sc.nextLine();
					List<CreatePiece> lcp=dbq.doCheckCreatePiece(namePiece);
					System.out.println("taille liste :"+lcp.size());
					if(!lcp.isEmpty()){
						for(CreatePiece cp : lcp){
							System.out.println("Info piece : "+cp);
						}
					}
					else System.out.println("No Piece with this name exist");
					break;
				case "/checkInfoCreatePiece":
					System.out.print("\n Enter the piece name:\n >");
					namePiece = sc.nextLine();
					System.out.print("\n Enter the piece id:\n >");
					nameID = sc.nextLine();
					CreatePiece cp = dbq.doCheckInfoPiece(namePiece, nameID);
					if(cp!= null){
						System.out.println(" Info piece : "+cp);
					} else System.out.println(" The piece name with the name "+namePiece+" doesn't exist.");
					break;
				case "/checkModel3D" :
					System.out.print("\n Enter the Model3D name:\n >");
					nameModel = sc.nextLine();
					List<Model3D> lm = dbq.doCheckModel3D(nameModel);
					if(lm!=null){
						for(Model3D lm2 : lm){
							System.out.println("Info model : "+lm2);
						}
					}
					else System.out.println("Model3D with name "+nameModel +" doesn't exist.");

					break;
				case "/checkInfoOriginalPiece":
					System.out.println("\n Enter the piece name :\n >");
					namePiece = sc.nextLine();
					OriginalPiece op=dbq.doCheckInfoOriginalPiece(namePiece);
					if(op!= null) {
						System.out.println(" Piece with name"+op);
						boolean searchCata = false;
						Catalogue currentC=null;
						//on recupere le catalogue associe
						for(Catalogue c : op.getCatalogue()){
							for(OriginalPiece op2 : c.getOriginalPiece()){
								if(namePiece==op2.getNameCP()) {
									searchCata=true;
									break;		
								}						
							}
							if(searchCata) {
								currentC=c;
								break;
							}
						}
						if(searchCata) System.out.println(" Catalogue ref :"+currentC.getRefCat()+"   catalogue date :"+currentC.getDateCat());
						else System.out.println("no catalogue exist"); //ne doit jamais arriver normalement
					}
					else System.out.println("Piece "+namePiece+" doesn't exist");
					break;
				default :
					System.out.print("\n\n ##### Unknow command : please see /help for more information");
				}	
			}
			else {
				System.out.print("\n admin> ");
				String command = sc.nextLine();
				switch(command){
				case "/help":
					helpCommandAdmin();
					break; 
				case "/addUser":
					boolean verify = true;
					System.out.print("\n Enter the user Firstname :\n > ");
					String firstname = sc.nextLine().toLowerCase(); //tout en minuscule
					System.out.print("\n Enter the user Lasttname :\n > ");
					String lastname = sc.nextLine().toLowerCase();
					System.out.print("\n Enter the user mail address with this format XXX@YYY.ZZZ :\n > ");
					String mail = null;
					while(verify){
						try {
							mail= sc.nextLine().toLowerCase();
							Pattern patternMail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
									+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
							Matcher matcher = patternMail.matcher (mail);
							if(matcher.matches()) {
								System.out.print("\n mail correct");
								verify=false;
							}
							else {
								System.out.println("\n Enter the user mail address with this format XXX@YYY.ZZZ :\n > ");
							}
						} catch (NumberFormatException e) {
							System.out.println("Give a correct mail address :");
						}
					}


					System.out.print("\n Enter the user password :\n > ");
					String password= sc.nextLine();
					System.out.print("\n Enter the user shipping address:\n > "); //livraison
					String shippingAddress= sc.nextLine();
					System.out.print("\n Enter the user billing address:\n > "); //facturation
					String billingAddress= sc.nextLine();
					System.out.print("\n Enter the user cell Phone with the syntaxe : XXXXXXXXXX (10 numbers):\n > ");
					String cellPhone = null; 
					verify = true;
					while(verify){
						try {
							cellPhone= sc.nextLine().toLowerCase();
							Pattern patternMail = Pattern.compile("\\d{10}");
							Matcher matcher = patternMail.matcher (cellPhone);
							if(matcher.matches()) {
								System.out.println("\n cellPhone correct");
								verify=false;
							}
							else {
								System.out.print("\n Enter the user cell Phone with the syntaxe : XXXXXXXXXX (10 numbers):\n > ");
							}
						} catch (NumberFormatException e) {
							System.out.println("Give a correct cellPhone address :");
						}
					}
					verify=true;
					String fixPhone = null;
					System.out.print("\n Enter the user fixe Phone:\n > ");
					while(verify){
						try {
							fixPhone= sc.nextLine().toLowerCase();
							Pattern patternMail = Pattern.compile("\\d{10}");
							Matcher matcher = patternMail.matcher (fixPhone);
							if(matcher.matches()) {
								System.out.println("\n fixPhone correct");
								verify=false;
							}
							else {
								System.out.print("\n Enter the user fixe Phone with the syntaxe : XXXXXXXXXX (10 numbers):\n > ");
							}
						} catch (NumberFormatException e) {
							System.out.println("Give a correct fixe Phone address :");
						}
					}
					System.out.print("\n Enter the user rib  XXXXXXXXXX (10 numbers):\n >:\n > ");
					String rib = null; 
					verify = true;
					while(verify){
						try {
							rib= sc.nextLine();
							
							Pattern patternMail = Pattern.compile("\\d{10}");
							Matcher matcher = patternMail.matcher(rib);
							if(matcher.matches()) {
								System.out.println("\n rib correct");
								verify=false;
							}
							else {
								System.out.print("\n Enter the user rib with the syntaxe : XXXXXXXXXX (10 numbers):\n > ");
							}
						} catch (NumberFormatException e) {
							System.out.println("Give a correct cellPhone address :");
						}
					}
					
					
					
					if(dbq.doAddUserAccount(mail, password, shippingAddress, billingAddress, cellPhone, fixPhone, rib)) {
						dbq.doAddUser(lastname,firstname, mail);
						System.out.println("User "+ firstname + " "+ lastname + " create with mail address : "+mail);
						new JamesMail(firstname, lastname, mail);
					} else {
						System.out.println("User "+ firstname + " "+ lastname+" already existe");
					}

					break;
				case "/checkUser":
					System.out.print("\n Enter the user mail:\n > ");
					String checkMail = sc.nextLine();
					UserAccount u = dbq.doCheckusers(checkMail);

					if(u!=null){
						System.out.println("User exist : "+u);
					} else {
						System.out.println("this user do not exist");
					}
					break;
				case "/modifUser":
					System.out.print("\n Enter the user mail:\n > ");
					String mailC = sc.nextLine();
					System.out.println("Set lastname : ");
					String newLastName = sc.nextLine();
					Users myU = dbq.doModifUsers(mailC, newLastName);
					System.out.println("user prenom : "+myU.getPrenomC());
					System.out.println("user nom : "+myU.getNameC());
					break;
				case "/checkInfoUser":
					System.out.print("\n Enter the user mail:\n > ");
					String mailIU = sc.nextLine();
					UserAccount myIU = dbq.doCheckInfoUsers(mailIU);
					System.out.println("Info user : "+myIU);
					break;
				case "/createBDD":
					System.out.print("\n How many user do u want to add : \n > ");
					int nbUser = 1;
					verify = true;
					String []listMail = {"gmail.com","laposte.com", "outlook.com", "hotmail.com", "polytech.com"};
					while(verify){
						try {
							nbUser = Integer.parseInt(sc.nextLine());
							verify = false;
						} catch (NumberFormatException e) {
							System.out.println("Give the number of user :");
						}
					}
					for(int i=0;i<nbUser;i++){
						String userFirstName = RandomStringUtils.random(5,true,false).toLowerCase();
						String userLastName = RandomStringUtils.random(5,true,false).toLowerCase();
						mail = userFirstName+"."+userLastName+"@"+listMail[i%listMail.length];
						String userPassword= RandomStringUtils.random(5,true,false);
						String userShippingAddress= RandomStringUtils.random(5,true,false);
						String userBillingAddress= RandomStringUtils.random(5,true,false);
						String userCellPhone = RandomStringUtils.random(10,false,true);
						String userFixePhone = RandomStringUtils.random(10,false,true);
						String userPrice = RandomStringUtils.random(10,false,true);
						if(dbq.doAddUserAccount(mail, userPassword, userShippingAddress, userBillingAddress, userCellPhone, userFixePhone, userPrice)) {
							dbq.doAddUser(userLastName,userFirstName, mail);
							System.out.println("User "+ userFirstName + " "+ userLastName + " create with mail address : "+mail);
						} else {
							System.out.println("User "+ userFirstName + " "+ userLastName+" already existe");
						}
					}
					break;
				case "/removeUser" :
					System.out.print("\n Enter the user mail:\n > ");
					checkMail = sc.nextLine();
					UserAccount ua = dbq.doCheckusers(checkMail);

					if(ua!=null){
						dbq.doRemoveUser(checkMail);
					} else {
						System.out.println("this user do not exist");
					}
				case "/exit" :
					System.out.println(" #### Thanks and Bye!");
					System.exit(0);
					break;
				case "/userMode" :
					System.out.println(" #### Thanks and Bye!");
					isAdmin=false;
					break;
				case "/addValidator":
					System.out.println("\n/*******************************************/");
					System.out.println("\nCreate Validator account");
					String validName = "valid"+RandomStringUtils.random(5,true,false).toLowerCase();
					if(dbq.doAddUserAccount(validName+"@byl.com", 
							"valid"+RandomStringUtils.random(5,true,false).toLowerCase(), 
							"valid"+RandomStringUtils.random(5,true,false).toLowerCase()+"address", 
							"valid"+RandomStringUtils.random(5,true,false).toLowerCase()+"address", 
							RandomStringUtils.random(10,false,true), 
							RandomStringUtils.random(10,false,true),
							RandomStringUtils.random(10,false,true))) {
						dbq.doAddValidator(validName, validName, validName+"@byl.com");
						System.out.println("Validator create : "+validName+"@byl.com");
					} else {
						System.out.println("Validator already existe");
					}
					break;
				case "/addAdministrator":
					System.out.println("\n/*******************************************/");
					System.out.println("\nCreate Administrator account");
					String adminName = "admin"+RandomStringUtils.random(5,true,false).toLowerCase();
					if(dbq.doAddUserAccount(adminName+"@byl.com", 
							"admin"+RandomStringUtils.random(5,true,false).toLowerCase(), 
							"admin"+RandomStringUtils.random(5,true,false).toLowerCase()+"address", 
							"admin"+RandomStringUtils.random(5,true,false).toLowerCase()+"address", 
							RandomStringUtils.random(10,false,true), 
							RandomStringUtils.random(10,false,true),
							RandomStringUtils.random(10,false,true))) {
						dbq.doAddAdministrator(adminName,adminName, adminName+"@byl.com");
						System.out.println("Admin create : "+adminName+"@byl.com");
					} else {
						System.out.println("Admin already existe");
					}
					break;
				case "/checkInfoModel3D":
					System.out.print("\n Enter the Model3D name:\n > ");
					String nameModel = sc.nextLine();
					System.out.print("\n Enter the Model3D id:\n > ");
					String nameID = sc.nextLine();
					Model3D m = dbq.doCheckInfoModel3D(nameModel,nameID );
					if(m!=null) System.out.println("Info model : "+m);
					else System.out.println("this model dosn't exist");					
					break;
				case "/addModel3D":
					System.out.print("\n Enter the Model3D name:\n >");
					nameModel = sc.nextLine();
					System.out.print("\n Enter the Model3D theme:\n >");
					String themeModel = sc.nextLine();
					System.out.print("\n Enter the Model3D user:\n >");
					String userModel = sc.nextLine();
					System.out.print("\n Enter the Model3D picture (in byte):\n >");
					byte pictureModel = sc.nextByte();
					//la photo est creer en meme temps que le model3D : il ne peut pas y avoir de photos volatille
					m = dbq.doAddModel3D(nameModel, themeModel, userModel, pictureModel);
					System.out.print("\n the model3D : "+m+" success");
					break;
				case "/checkModel3D" :
					System.out.print("\n Enter the Model3D name:\n >");
					nameModel = sc.nextLine();
					List<Model3D> lm = dbq.doCheckModel3D(nameModel);
					if(lm!=null){
						for(Model3D lm2 : lm){
							System.out.println("Info model : "+lm2);
						}
					}
					else System.out.println("Model3D with name "+nameModel +" doesn't exist.");

					break;
				case "/removeModel3D":
					System.out.print("\n Enter the Model3D name:\n >");
					nameModel = sc.nextLine();
					dbq.doRemoveModel3D(nameModel);
					break;
				case "/addCreatePiece":
					System.out.print("\n Enter the piece name:\n >");
					String namePiece = sc.nextLine();
					System.out.print("\n Enter the piece theme:\n >");
					String themePiece = sc.nextLine();
					System.out.print("\n Enter the piece user:\n >");
					String userPiece = sc.nextLine();
					System.out.print("\n Enter the piece picture (in byte):\n >");
					byte picturePiece = sc.nextByte();
					System.out.print("\n Enter the piece price:\n >");
					float pricePiece = sc.nextFloat();
					CreatePiece p= dbq.doAddCreatePiece(namePiece, themePiece, userPiece, picturePiece,pricePiece);
					if(p!=null) System.out.println(" New Piece create");
					else System.out.println(" Fail during the creation of your new piece");
					break;
				case "/checkCreatePiece":
					System.out.print("\n Enter the piece name:\n >");
					namePiece = sc.nextLine();
					List<CreatePiece> lcp=dbq.doCheckCreatePiece(namePiece);
					System.out.println("taille liste :"+lcp.size());
					if(!lcp.isEmpty()){
						for(CreatePiece cp : lcp){
							System.out.println("Info piece : "+cp);
						}
					}
					else System.out.println("No Piece with this name exist");
					break;
				case "/checkInfoCreatePiece":
					System.out.print("\n Enter the piece name:\n >");
					namePiece = sc.nextLine();
					System.out.print("\n Enter the piece id:\n >");
					nameID = sc.nextLine();
					CreatePiece cp = dbq.doCheckInfoPiece(namePiece, nameID);
					if(cp!= null){
						System.out.println(" Info piece : "+cp);
					} else System.out.println(" The piece name with the name "+namePiece+" doesn't exist.");
					break;
				case "/removeCreatePiece":
					System.out.print("\n Enter the Model3D name:\n >");
					namePiece = sc.nextLine();
					System.out.print("\n Enter the piece id:\n >");
					nameID = sc.nextLine();
					dbq.doRemoveCreatePiece(namePiece,nameID);
					break;
				case "/addOriginalPiece":
					System.out.print("\n Enter the piece name :\n > ");
					namePiece = sc.nextLine();
					System.out.print("\n Enter the date of the catalogue :\n > ");
					String dateCat = sc.nextLine();
					System.out.print("\n Enter the reference of the catalogue :\n > ");
					String referenceCat = sc.nextLine();
					OriginalPiece op = dbq.doAddOriginalPiece(namePiece,dateCat, referenceCat);
					
					break;
				case "/checkInfoOriginalPiece":
					System.out.print("\n Enter the piece name :\n >");
					namePiece = sc.nextLine();
					op=dbq.doCheckInfoOriginalPiece(namePiece);
					Collection<Catalogue >cataList = dbq.doGetCatalogueFromOriginalPiece(op.getId());

					if(cataList!=null){
						for(Catalogue c : cataList){
							System.out.println(" Catalogue ref :"+c.getRefCat()+" catalogue date :"+c.getDateCat());	
						}
					} else System.out.println("Catalogue Empty");
					
//					if(op!= null) {
//						System.out.println(" Piece with name : "+op.getNameCP());
//						//on recupere le catalogue associe
//						if(op.getCatalogue().isEmpty()){
//							System.out.println("list empty");
//						}else {
//							for(Catalogue c : op.getCatalogue()){
//								System.out.println(" Catalogue ref :"+c.getRefCat()+"   catalogue date :"+c.getDateCat());	
//							}
//						}
//					}
//					else System.out.println("Piece "+namePiece+" doesn't exist");
					break;
				case "/removeOriginalPiece":
					System.out.println("\n Enter the piece name :\n >");
					namePiece = sc.nextLine();
					dbq.doRemoveOriginalPiece(namePiece);
					break;



					//ADD IN HELP A PARTIR DE LAAAAAAAAAAAAA	
				case "/addCatalogue":
					System.out.print("\n Enter the date if the catalogue :\n >");
					String dateCatalogue = sc.nextLine();
					System.out.print("\n Enter the ref of the catalogue :\n >");
					String refCatalogue = sc.nextLine();
					dbq.doAddCatalogue(dateCatalogue, refCatalogue);
					break;
				case "/getAllOriginalPiece":
					List<OriginalPiece> lop = dbq.doGetAllOriginalPiece();
					if(lop!=null){
						for(OriginalPiece mop : lop){
							System.out.println(" OriginalPiece : "+mop.getId()+ " "+mop.getNameCP());
						}
					}
					break;
				default :
					System.out.print("\n\n ##### Unknow command : please see /help for more information");
				}
			}
		}
	}

	private void helpCommandAdmin() {
		// TODO Auto-generated method stub
		System.out.println("\n/*******************************************/");
		System.out.println("\n/************** HELP GUIDE *****************/");
		System.out.println("\n/*******************************************/");
		System.out.println("\n Commands list available :");

		System.out.println("\n");

		System.out.println("---> /addUser : add one User");
		System.out.println("---> /addValidator : add one Admin");
		System.out.println("---> /addAdministrator : add one Valid");
		System.out.println("---> /addModel3D : add one model3D with picture");
		System.out.println("---> /addCreatePiece : add one new piece with picture");
		System.out.println("---> /addOriginalPiece : add one Piece");
		System.out.println("---> /addCataloguee : add one Catalogue");

		System.out.println("\n");

		System.out.println("---> /checkUser : check if a User exist");
		System.out.println("---> /checkInfoUser : show all information for one user");
		System.out.println("---> /checkModel3D : show all Model3D with the same name");
		System.out.println("---> /checkInfoModel3D : show all information for one Model3D");
		System.out.println("---> /checkCreatePiece : show all piece with the same name");
		System.out.println("---> /checkInfoCreatePiece : show all information for one piece");
		System.out.println("---> /checkInfoOriginalPiece : show all information for one piece");
		System.out.println("---> /createBDD : add X random users and their userAccount");

		System.out.println("\n");

		System.out.println("---> /modifUser : modif one User");

		System.out.println("\n");

		System.out.println("---> /removeUser : remove one user");
		System.out.println("---> /removeModel3D : remove one Model3D");		
		System.out.println("---> /removeCreatePiece : remove one piece created");		
		System.out.println("---> /removeOriginalPiece : remove one piece created");		

		System.out.println("\n");

		System.out.println("---> /userMode : return to user mode");
		System.out.println("---> /exit : exit the shell");
	}

	private void helpCommandUser() {
		// TODO Auto-generated method stub
		System.out.println("\n/*******************************************/");
		System.out.println("\n/************** HELP GUIDE *****************/");
		System.out.println("\n/*******************************************/");
		System.out.println("\n Liste commandes possible :");

		System.out.println("---> /adminMode : go to admin mode");
		System.out.println("---> /checkUser : check if a User exist");
		System.out.println("---> /checkInfoUser : show all information for one user");
		System.out.println("---> /checkModel3D : show all Model3D with the same name");
		System.out.println("---> /checkInfoModel3D : show all information for one Model3D");
		System.out.println("---> /checkCreatePiece : show all piece with the same name");
		System.out.println("---> /checkInfoCreatePiece : show all information for one piece");
		System.out.println("---> /exit : exit the shell");
	}
}
