package ecom.ejb.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;



import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UserJson implements Serializable {
	
	@XmlElement
	String mail;
	@XmlElement
	String mdp;
	@XmlElement
	String name;
	@XmlElement
	String surname;
	@XmlElement
	String numTel;
	@XmlElement
	String numFix;
	@XmlElement
	String adrLivraison;
	@XmlElement
	String adrFacturation;
	

	
}

