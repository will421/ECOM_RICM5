package ecom.ejb;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Catalogue")
public class Catalogue implements Serializable{

	private static final long serialVersionUID = 1L;
	private String dateCat;
	@NotNull@Column(name="IDCAT")@Id private int idCat;
	@ManyToMany(mappedBy="catalogue") private Collection<OriginalPiece> originalPieces ;
	
	public Catalogue(String dateCat){
		this.setDateCat(dateCat);
	}
	
	public void setOriginalPiece(OriginalPiece op){
		this.originalPieces.add(op);
	}
	
	public Collection<OriginalPiece> getOriginalPiece(){
		return originalPieces;
	}
	
	public Catalogue(){
		
	}

	public String getDateCat() {
		return dateCat;
	}

	public void setDateCat(String dateCat) {
		this.dateCat = dateCat;
	}

	public int getRefCat() {
		return idCat;
	}

	public void setRefCat(int refCat) {
		this.idCat = refCat;
	}
	
}
