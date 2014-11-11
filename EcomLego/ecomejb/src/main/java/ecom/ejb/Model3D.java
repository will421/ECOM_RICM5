package ecom.ejb;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Model3D")
public class Model3D implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idM;
	private String nameM;
	//sourceM
	@OneToMany(mappedBy="model3D") private Collection<Commande> commande;

	
	public Model3D(String nameM){
		this.nameM=nameM;
	}
	
	public Model3D(){
		
	}

	public String getNameCP() {
		return nameM;
	}

	public void setNameCP(String nameM) {
		this.nameM = nameM;
	}
	
	public long getId(){
		return this.idM;
	}
	

}
