package ecom.ejb;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Administrator")
public class Administrator implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nameA;
	private String prenomA;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idA;
	@OneToOne(mappedBy="administrator") private UserAccount useraccount;
	
	public Administrator(String nameA, String prenomA){
		this.nameA = nameA;
		this.prenomA = prenomA;
	}

	public Administrator(){
		
	}

	public String getPrenomA() {
		return prenomA;
	}

	public void setPrenomA(String prenomA) {
		this.prenomA = prenomA;
	}

	public String getNameA() {
		return nameA;
	}

	public void setNameA(String nameA) {
		this.nameA = nameA;
	}
	
	public long getId(){
		return this.idA;
	}
}
