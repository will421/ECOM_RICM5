package ecom.ejb;
import java.io.Serializable;
import java.util.ArrayList;
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
	@NotNull@Column(name="IDCAT")@Id private String idCat;
	@ManyToMany(mappedBy="catalogue") private Collection<OriginalPiece> originalPieces ;
	
	
	public void setOriginalPiece(OriginalPiece op){
		this.originalPieces.add(op);
	}
	
	public Collection<OriginalPiece> getOriginalPiece(){
		return originalPieces;
	}
	
	public Catalogue(){
		this.originalPieces = new ArrayList<>();
	}

	public String getDateCat() {
		return dateCat;
	}

	public void setDateCat(String dateCat) {
		this.dateCat = dateCat;
	}

	public String getRefCat() {
		return idCat;
	}

	public void setRefCat(String refCat) {
		this.idCat = refCat;
	}
	
}
