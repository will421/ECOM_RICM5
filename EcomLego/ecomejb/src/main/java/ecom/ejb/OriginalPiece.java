package ecom.ejb;
import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "OriginalPiece")
public class OriginalPiece implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name="IDPO")@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idPO;
	
	@NotNull
	@ManyToMany
	@JoinTable(name = "ORIGINALPIECE_CATALOGUE", joinColumns=@JoinColumn(name="IDPO") , inverseJoinColumns=@JoinColumn(name="IDCAT"))
	private Collection<Catalogue> catalogue ;
	
	@ManyToMany
	@JoinTable(name = "ORIGINALPIECE_CONTROLLINES", joinColumns=@JoinColumn(name="IDPO") , inverseJoinColumns=@JoinColumn(name="IDCL"))
	private Collection<ControlLine> controlLines;
	
	private String namePO;
	private float pricePO;
	private String couleurOP;
	private String themeOP;
	
	
	public Collection<Catalogue> getCatalogue(){
		return catalogue;
	}
	
	public void setCatalogue(Catalogue catalogue){
		this.catalogue.add(catalogue);
	}
	
	public Collection<ControlLine> getControlLines(){
		return controlLines;
	}
	
	public void setControlLine(ControlLine controlLine){
		this.controlLines.add(controlLine);
	}
	
	public OriginalPiece(String namePO){
		this.namePO=namePO;
	}
	
	public OriginalPiece(){
		this.catalogue = new ArrayList<>();
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

	public float getPricePO() {
		return pricePO;
	}

	public void setPricePO(float pricePO) {
		this.pricePO = pricePO;
	}

	public String getCouleurOP() {
		return couleurOP;
	}

	public void setCouleurOP(String couleurOP) {
		this.couleurOP = couleurOP;
	}

	public String getThemeOP() {
		return themeOP;
	}

	public void setThemeOP(String themeOP) {
		this.themeOP = themeOP;
	}

}
