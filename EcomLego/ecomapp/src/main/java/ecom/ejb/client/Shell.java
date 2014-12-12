package ecom.ejb.client;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;

import ecom.ejb.UserAccount;
import ecom.ejb.Users;

public class Shell {

	public Shell(){
		Database dbq = new Database();
		Scanner sc = new Scanner(System.in);
		System.out.println("\n/*******************************************/");
		System.out.println("\n/********* WELCOME TO SHELL PROMPT *********/");
		System.out.println("\n/*******************************************/");
		System.out.println("\n\n Thanks to use /createBDD to create admin account");
		System.out.println("\n/*******************************************/");
		System.out.print("\nAdmin mode : Type your command : ");

		/*
		 * gerer le multicompte acces shell
		 * 
		 * */

		while(true){
			System.out.print("\n > ");
			String command = sc.nextLine();
			switch(command){
			case "/help":
				helpCommand();
				break; 
			case "/addUser":
				Users user = null;
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
					String userFirstName = RandomStringUtils.random(5,true,false).toLowerCase();;
					String userLastName = RandomStringUtils.random(5,true,false).toLowerCase();;
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

				System.out.println("\n/*******************************************/");
				System.out.println("\nCreate Administrator account");
				if(dbq.doAddUserAccount("admin@byl.com", "Pocsbla43", "admin address", "admin address", "0000000000", "0000000000")) {
					dbq.doAddAdministrator("admin","admin", "admin@byl.com");
					System.out.println("Admin create : admin@byl.com");
				} else {
					System.out.println("Admin already existe");
				}
				
				System.out.println("\n/*******************************************/");
				System.out.println("\nCreate Validator account");
				if(dbq.doAddUserAccount("validator@byl.com", "valid38", "val address", "val address", "1111111111", "1111111111")) {
					dbq.doAddValidator("val","val", "validator@byl.com");
					System.out.println("Validator create : validator@byl.com");
				} else {
					System.out.println("Validator already existe");
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
			default :
				System.out.print("\n\n ##### Commande inconnu : tapez /help pour plus d'information");
			}
		}
	}

	private void helpCommand() {
		// TODO Auto-generated method stub
		System.out.println("\n/*******************************************/");
		System.out.println("\n/************** HELP GUIDE *****************/");
		System.out.println("\n/*******************************************/");
		System.out.println("\n Liste commandes possible :");

		System.out.println("\n---> /addUser : add one User");
		System.out.println("---> /checkUser : check if a User exist");
		System.out.println("---> /modifUser : modif one User");
		System.out.println("---> /checkInfoUser : show all information for one user");
		System.out.println("---> /removeUser : remove one user");
		System.out.println("---> /createBDD : add X user/userAccount, 1 admin and 1 validator");
		System.out.println("---> /exit : exit the shell");
	}
}
