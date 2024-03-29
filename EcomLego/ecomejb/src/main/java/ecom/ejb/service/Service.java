package ecom.ejb.service;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Service
 */
@Stateless(mappedName="Service")
public class Service implements Serializable ,ServiceRemote {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Date getCurrentDate(){
        return new Date();
    }
}
