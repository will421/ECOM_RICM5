package ecom.ejb.client;

import java.io.Console;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

import ecom.ejb.UserAccount;
import ecom.ejb.Users;

public class Shell {

	@SuppressWarnings("resource")
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
					if(u!=null && u.getAdministrator()!=null){
						String adminpwd = new String (console.readPassword(" user> Enter a password : "));
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
							Pattern patternMail = Pattern.compile("^\\w+@[a-z]+\\.[a-z]{2,4}$");
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
					if(dbq.doAddUserAccount(mail, password, shippingAddress, billingAddress, cellPhone, fixPhone)) {
						dbq.doAddUser(lastname,firstname, mail);
						System.out.println("User "+ firstname + " "+ lastname + " create with mail address : "+mail);
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
						if(dbq.doAddUserAccount(mail, userPassword, userShippingAddress, userBillingAddress, userCellPhone, userFixePhone)) {
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
							RandomStringUtils.random(10,false,true))) {
						dbq.doAddAdministrator(adminName,adminName, adminName+"@byl.com");
						System.out.println("Admin create : "+adminName+"@byl.com");
					} else {
						System.out.println("Admin already existe");
					}
					break;
				default :
					System.out.print("\n\n ##### Commande inconnu : tapez /help pour plus d'information");
				}
			}
		}
	}

	private void helpCommandAdmin() {
		// TODO Auto-generated method stub
		System.out.println("\n/*******************************************/");
		System.out.println("\n/************** HELP GUIDE *****************/");
		System.out.println("\n/*******************************************/");
		System.out.println("\n Liste commandes possible :");

		System.out.println("\n---> /addUser : add one User");
		System.out.println("\n---> /addValidator : add one Admin");
		System.out.println("\n---> /addAdministrator : add one Valid");
		System.out.println("---> /checkUser : check if a User exist");
		System.out.println("---> /modifUser : modif one User");
		System.out.println("---> /checkInfoUser : show all information for one user");
		System.out.println("---> /removeUser : remove one user");
		System.out.println("---> /createBDD : add X user/userAccount, 1 admin and 1 validator");
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
		System.out.println("---> /checkInfoUser : show all information for one user");
		System.out.println("---> /checkUser : check if a User exist");
		System.out.println("---> /exit : exit the shell");
	}
}
