package ecom.ejb;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Picture")
public class Picture implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomP;
	private String userP;
	@NotNull private byte picture;
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE) private long idP;
	@OneToOne private CreatePiece createPiece;
	@OneToOne private Model3D model3D;
	
	
	public Picture(String nomP, String userP, byte picture){
		this.setNomP(nomP);
		this.setUserP(userP);
		this.setPicture(picture);
	}
	
	public Model3D getModel3D(){
		return this.model3D;
	}
	
	public void setModel3D(Model3D m){
		this.model3D=m;
	}
	
	public CreatePiece getCreatePiece(){
		return this.createPiece;
	}
	
	public void setCreatePiece(CreatePiece cp){
		this.createPiece=cp;
	}

	public Picture(){
		
	}

	public String getNomP() {
		return nomP;
	}

	public void setNomP(String nomP) {
		this.nomP = nomP;
	}

	public String getUserP() {
		return userP;
	}

	public void setUserP(String userP) {
		this.userP = userP;
	}

	public byte getPicture() {
		return picture;
	}

	public void setPicture(byte picture) {
		this.picture = picture;
	}
	
	public long getIdP(){
		return idP;
	}
	

}
