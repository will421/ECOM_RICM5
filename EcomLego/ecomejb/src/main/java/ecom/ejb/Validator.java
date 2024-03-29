package ecom.ejb;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Validator")
public class Validator implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nameV;
	private String prenomV;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idV;
	@OneToOne(mappedBy="validator")private UserAccount useraccount;
	
	public Validator(String nameV, String prenomV){
		this.nameV = nameV;
		this.prenomV = prenomV;
	}

	public Validator(){
		
	}

	public String getNameV() {
		return nameV;
	}

	public void setNameV(String nameV) {
		this.nameV = nameV;
	}

	public String getPrenomV() {
		return prenomV;
	}

	public void setPrenomV(String prenomV) {
		this.prenomV = prenomV;
	}

	public long getIdV() {
		return this.idV;
	}
	
	public void setUserAccount(UserAccount ua){
		this.useraccount=ua;
	}
	
	public UserAccount getUserAccount(){
		return useraccount;
	}
}
