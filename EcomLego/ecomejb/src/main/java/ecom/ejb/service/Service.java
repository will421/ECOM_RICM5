package ecom.ejb.service;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Service
 */
@Stateless
public class Service {

    public Date getCurrentDate(){
        return new Date();
    }
}
