package ecom.ejb;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Commande")
public class Commande implements Serializable{

	private static final long serialVersionUID = 1L;
	@NotNull private boolean isPaid;
	@NotNull private float price;
	@NotNull private String date;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idCom;
	
	public Commande(float price, boolean isPaid, String date){
		this.price=price;
		this.isPaid=isPaid;
		this.date=date;
	}

	public Commande(){

	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}
}
