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
	private String nameV;
	private String prenomV;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idV;
	
	public Client(String nameV){
		this.nameV =nameV;
	}

	public Client(){
		
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
		return idV;
	}

	public void setIdV(int idV) {
		this.idV = idV;
	}
	
	
	
	
}
