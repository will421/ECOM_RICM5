package ecom.ejb.client;

import java.util.Scanner;

import ecom.ejb.UserAccount;
import ecom.ejb.Users;

public class Shell {

	public Shell(){
		Database dbq = new Database();
		Scanner sc = new Scanner(System.in);
		System.out.println("\n/*******************************************/");
		System.out.println("\n/********* WELCOME TO SHELL PROMPT *********/");
		System.out.println("\n/*******************************************/");
		System.out.print("\nAdmin mode : Type your command :\n > ");

		while(true){
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
				String mail= sc.nextLine();
				System.out.print("\n Enter the user password :\n > ");
				String password= sc.nextLine();
				System.out.print("\n Enter the user shipping address:\n > "); //livraison
				String shippingAddress= sc.nextLine();
				System.out.print("\n Enter the user billing address:\n > "); //facturation
				String billingAddress= sc.nextLine();
				System.out.print("\n Enter the user cell Phone:\n > ");
				int cellPhone = sc.nextInt();
				sc.nextLine();
				System.out.print("\n Enter the user fix Phone:\n > ");
				int fixPhone = sc.nextInt();
				sc.nextLine();

				if(dbq.doAddUserAccount(mail, password, shippingAddress, billingAddress, cellPhone, fixPhone)) {
					int value = dbq.doAddUser(lastname,firstname, mail);
					System.out.println("User "+ firstname + " "+ lastname + " create");
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
					sc.nextLine();
				} else {
					System.out.println("this user do not exist");
					sc.nextLine();
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

		System.out.println("\n ---> /addUser : permet d'ajouter un User");
		System.out.println("\n ---> /checkUser : permet d'ajouter un User");
		System.out.println("\n ---> /modifUser : permet de modifier un User");
		System.out.println("\n ---> /checkInfoUser : permet de connaitre toutes les informations d'un User");
	}
}
