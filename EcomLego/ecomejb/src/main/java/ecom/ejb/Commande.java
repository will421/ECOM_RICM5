package ecom.ejb;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Commande")
public class Commande implements Serializable{

	private static final long serialVersionUID = 1L;
	@NotNull private boolean isPaid;
	@NotNull private float price;
	@NotNull private Date date;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idCom;
	@OneToMany(mappedBy="commande") private Collection<Users> client;
	@ManyToOne private ControlLine controlLine;
	@ManyToOne private Model3D model3D;
	
	public Commande(float price, boolean isPaid, Date date){
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

	public Date getDate() {
		return date;
	}
	
	public long getId(){
		return this.idCom;
	}
}
