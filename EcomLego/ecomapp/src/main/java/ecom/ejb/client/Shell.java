package ecom.ejb.client;

import java.util.Scanner;

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
				String firstname = sc.nextLine();
				System.out.print("\n Enter the user Lasttname :\n > ");
				String lastname = sc.nextLine();
				System.out.print("\n Enter the user mail address :\n > ");
				String mail= sc.nextLine();
				System.out.print("\n Enter the user password :\n > ");
				String password= sc.nextLine();
				System.out.print("\n Enter the user shipping address:\n > "); //livraison
				String shippingAddress= sc.nextLine();
				System.out.print("\n Enter the user billing address:\n > "); //facturation
				String billingAddress= sc.nextLine();
				System.out.print("\n Enter the user billing address:\n > ");
				String cellPhone = sc.nextLine();
				System.out.print("\n Enter the user fixPhone:\n > ");
				String fixPhone = sc.nextLine();
				user = dbq.doAddUser(firstname,lastname);
				//UserAccount(String mailU, String mdpU, String shippingAddress, String billingAddress, String cellPhone, String fixPhone){

				break;
			case "/checkUser":
				System.out.print("\n Enter the user Firstname :\n > ");
				String firstname2 = sc.nextLine();
				System.out.print("\n Enter the user Lasttname :\n > ");
				String lastname2 = sc.nextLine();
				if(dbq.doCheckusers(firstname2, lastname2)) System.out.println("User exist");
				else System.out.println("User "+ firstname2 + " "+ lastname2+" do not exist");
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





	}
}
