package ecom.ejb.client;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {
    
    public static void main( String[] args ) {
        System.out.println( "Lancement en cours..." );
        

		Database dbq = new Database();
        System.out.println("\n/*******************************************/");
		System.out.println("\nCreate Administrator account");
		if(dbq.doAddUserAccount("admin@byl.com", "Pocsbla43", "admin address", "admin address", "0000000000", "0000000000")) {
			dbq.doAddAdministrator("admin","admin", "admin@byl.com");
			System.out.println("Admin create : admin@byl.com");
		} else {
			System.out.println("Admin already existe");
		}
		
		new JamesMail();
        new Shell();
    }
}
