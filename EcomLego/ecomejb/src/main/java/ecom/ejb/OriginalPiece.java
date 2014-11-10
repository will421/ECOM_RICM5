package ecom.ejb;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OriginalPiece")
public class OriginalPiece implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idPO;
	private String namePO;
	
	public OriginalPiece(String namePO){
		this.namePO=namePO;
	}
	
	public OriginalPiece(){
		
	}

	public String getNameCP() {
		return namePO;
	}

	public void setNameCP(String namePO) {
		this.namePO = namePO;
	}
	
	public long getId(){
		return this.idPO;
	}

}
