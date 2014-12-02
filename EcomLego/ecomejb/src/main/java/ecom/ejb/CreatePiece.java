package ecom.ejb;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CreatePiece")
public class CreatePiece implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name="IDCP")@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idCP;
	
	@NotNull@ManyToMany
	@JoinTable(name = "CREATEPIECE_CONTROLLINES", joinColumns=@JoinColumn(name="IDCP") , inverseJoinColumns=@JoinColumn(name="IDCL"))
	private Collection<ControlLine> controlLines;
	
	private String nameCP;
	
	public CreatePiece(String nameCP){
		this.nameCP=nameCP;
	}
	
	public CreatePiece(){
		
	}

	public String getNameCP() {
		return nameCP;
	}

	public void setNameCP(String nameCP) {
		this.nameCP = nameCP;
	}
	
	public long getId(){
		return this.idCP;
	}

}
