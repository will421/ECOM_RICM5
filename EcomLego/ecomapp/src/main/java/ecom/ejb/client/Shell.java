package ecom.ejb.client;

import java.util.Scanner;

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
		System.out.print("\nAdmin mode : Type your command : ");

		/*
		 * gerer le multicompte acces shell
		 * remove user
		 * creation au debut de la bdd
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
				System.out.print("\n Enter the user Firstname :\n > ");
				String firstname = sc.nextLine().toLowerCase(); //tout en minuscule
				System.out.print("\n Enter the user Lasttname :\n > ");
				String lastname = sc.nextLine().toLowerCase();
				System.out.print("\n Enter the user mail address with this format XXX@YYY.ZZZ :\n > ");
				String mail= sc.nextLine().toLowerCase();
				System.out.print("\n Enter the user password :\n > ");
				String password= sc.nextLine();
				System.out.print("\n Enter the user shipping address:\n > "); //livraison
				String shippingAddress= sc.nextLine();
				System.out.print("\n Enter the user billing address:\n > "); //facturation
				String billingAddress= sc.nextLine();
				System.out.print("\n Enter the user cell Phone:\n > ");
				int cellPhone = 0; 
				boolean verify = true;
				while(verify){
					try {
						cellPhone = Integer.parseInt(sc.nextLine());
						verify = false;
					} catch (NumberFormatException e) {
						System.out.println("Give the mobile phone :");
					}
				}
				verify=true;
				int fixPhone = 0;
				System.out.print("\n Enter the user fixe Phone:\n > ");
				while(verify){
					try {
						fixPhone = Integer.parseInt(sc.nextLine());
						verify = false;
					} catch (NumberFormatException e) {
						System.out.println("Give the fixe phone :");
					}
				}
				if(dbq.doAddUserAccount(mail, password, shippingAddress, billingAddress, cellPhone, fixPhone)) {
					int value = dbq.doAddUser(lastname,firstname, mail);
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
					int userCellPhone = 1 + (int)(Math.random() * ((999999999 - 1) + 1));
					int userFixePhone = 1 + (int)(Math.random() * ((999999999 - 1) + 1));
					if(dbq.doAddUserAccount(mail, userPassword, userShippingAddress, userBillingAddress, userCellPhone, userFixePhone)) {
						int value = dbq.doAddUser(userLastName,userFirstName, mail);
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
			default :
				System.out.print("\n\n ##### Commande inconnu : tapez /help pour plus d'information \n > ");
			}
		}
	}

	private void helpCommand() {
		// TODO Auto-generated method stub
		System.out.println("\n/*******************************************/");
		System.out.println("\n/************** HELP GUIDE *****************/");
		System.out.println("\n/*******************************************/");
		System.out.println("\n Liste commandes possible :");

		System.out.println("\n---> /addUser : permet d'ajouter un User");
		System.out.println("---> /checkUser : permet d'ajouter un User");
		System.out.println("---> /modifUser : permet de modifier un User");
		System.out.println("---> /checkInfoUser : permet de connaitre toutes les informations d'un User");
		System.out.println("---> /removeUser : permet de supprimer toutes les informations d'un User");
		System.out.println("---> /createBDD : permet de creer des users avec userAccount associe");
	}
}
