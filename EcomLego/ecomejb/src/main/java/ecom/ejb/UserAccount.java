package ecom.ejb;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "UserAccount")
public class UserAccount implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id private int idU;
	@NotNull@Pattern(regexp = ".+@.+\\..+", message = "wrong email format") private String mailU;
	@NotNull private String mdpU;
	@NotNull private String shippingAddress; //adresse de livraison
	@NotNull private String billingAddress; //adresse de facturation
	private String cellPhone;
	private String fixPhone;
	
	public UserAccount(String mailU){
		this.mailU = mailU;
	}

	public UserAccount(){
		
	}

	public String getMdpU() {
		return mdpU;
	}

	public void setMdpU(String mdpU) {
		this.mdpU = mdpU;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getFixPhone() {
		return fixPhone;
	}

	public void setFixPhone(String fixPhone) {
		this.fixPhone = fixPhone;
	}

	public int getIdU() {
		return idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
	}
	
	
	
	
}