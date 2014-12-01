package ecom.ejb;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
public class Users implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nameC;
	private String prenomC;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idC;
	@OneToOne private UserAccount useraccount;
	@ManyToOne private Commande commande;
	
	public Users(String nameC, String prenomC){
		this.setNameC(nameC);
		this.setPrenomC(prenomC);
	}

	public Users(){
		
	}
	
	public void setUserAccount(UserAccount ua){
		this.useraccount=ua;
	}
	
	public UserAccount getUserAccount(){
		return useraccount;
	}
	
	public String getNameC() {
		return nameC;
	}

	public void setNameC(String nameC) {
		this.nameC = nameC;
	}

	public String getPrenomC() {
		return prenomC;
	}

	public void setPrenomC(String prenomC) {
		this.prenomC = prenomC;
	}
	
	public long getId(){
		return this.idC;
	}

}
