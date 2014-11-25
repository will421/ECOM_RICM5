import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ecom.cart.ejb.Cart;


public class CartClient2 {

	@EJB
	private static Cart instance;
	
    private EJBContainer ec;
    private Context ctx;
	
    
    //@Before
    public void setUp() {
        //ec = EJBContainer.createEJBContainer();
    	//ctx = ec.getContext();
    	/*try {
    		Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
    		Properties props = new Properties();
    		props.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.enterprise.naming.SerialInitContextFactory");
    		props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");

    		// glassfish default port value will be 3700,
    		props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

    		ctx = new InitialContext();
    	} catch (NamingException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}*/
    }

   //@After
    public void tearDown() {
        if (ec != null) {
            ec.close();
        }
    }
    
    public void run()
    {
		
        //Cart instance = null;
		/*try {
			instance = (Cart) ctx.lookup("java:global/classes/Cart");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        String expResult = "Greetings!";
        System.out.println(instance.getContents());
		
		
    }
    
    
	public static void main(String[] args) {
		CartClient2 cc = new CartClient2();
		cc.setUp();
		cc.run();
		cc.tearDown();
	}

}
