package ecom.ejb;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ControlLine")
public class ControlLine implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomLC;
	private float priceLC;// mbBigDecimal
	private String theme;
	@Column(name="IDCL")@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idCL;
	@OneToMany(mappedBy="controlLine") private Collection<Commande> commande;
	@ManyToMany(mappedBy="controlLines") private Collection<CreatePiece> createPieces ;
	@ManyToMany(mappedBy="controlLines") private Collection<OriginalPiece> originalPieces ;

	
	public ControlLine(String nomLC, float priceLC, String theme){
		this.nomLC=nomLC;
		this.priceLC=priceLC;
		this.theme=theme;
	}

	public ControlLine(){

	}

	public String getNomLC() {
		return nomLC;
	}

	public void setNomLC(String nomLC) {
		this.nomLC = nomLC;
	}

	public float getPriceLC() {
		return priceLC;
	}

	public void setPriceLC(float priceLC) {
		this.priceLC = priceLC;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}
