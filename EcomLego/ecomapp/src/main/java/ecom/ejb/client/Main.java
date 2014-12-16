package ecom.ejb.client;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {
    
    public static void main( String[] args ) {
        System.out.println( "Lancement en cours..." );
        

		Database dbq = new Database();
//        System.out.println("\n/*******************************************/");
//		System.out.println("\nCreate Administrator account");
//		if(dbq.doAddUserAccountFirstAdmin("admin@byl.com", "32c074519f1b26f020a134a253ce3c6222674ec9", "admin address", "admin address", "0000000000", "0000000000")) {
//			dbq.doAddAdministrator("admin","admin", "admin@byl.com");
//			System.out.println("Admin create : admin@byl.com");
//		} else {
//			System.out.println("Admin already existe");
//		}
		
		//new JamesMail();
        new Shell();
    }
}
