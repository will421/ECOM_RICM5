package ecom.ejb;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nameC;
	private String prenomC;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idC;
	
	public Client(String nameC, String prenomC){
		this.setNameC(nameC);
		this.setPrenomC(prenomC);
	}

	public Client(){
		
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
