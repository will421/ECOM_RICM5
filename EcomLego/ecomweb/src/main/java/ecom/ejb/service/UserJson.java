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
	String nom;
	@XmlElement
	String prenom;
	@XmlElement
	String numTel;
	@XmlElement
	String numFix;
	@XmlElement
	String adrLivraison;
	@XmlElement
	String adrFacturation;
	@XmlElement
	String rib;
	
	public static UserJson createOne()
	{
		UserJson user = new UserJson();
		user.mail = "toto@tutu.tata";
		user.mdp = "tralala";
		user.nom ="toto";
		user.prenom = "truc";
		user.numTel = "numTel";
		user.numFix = "numFix";
		user.adrLivraison = "adrLivraison";
		user.adrFacturation = "adrFacturation";
		user.rib = "rib";
		
		return user;
	}
	
}

