package ecom.ejb.service;

import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
public interface ServiceRemote {

    public Date getCurrentDate();
	
}
