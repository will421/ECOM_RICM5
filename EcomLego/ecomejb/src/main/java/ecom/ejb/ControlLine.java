package ecom.ejb;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ControlLine")
public class ControlLine implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomLC;
	private float priceLC;
	private String colorLC;
	private String theme;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idCom;
	
	public ControlLine(String nomLC, float priceLC, String colorLC, String theme){
		this.nomLC=nomLC;
		this.priceLC=priceLC;
		this.colorLC=colorLC;
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

	public String getColorLC() {
		return colorLC;
	}

	public void setColorLC(String colorLC) {
		this.colorLC = colorLC;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}