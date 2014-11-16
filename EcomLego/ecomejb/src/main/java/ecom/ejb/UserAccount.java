package ecom.ejb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "UserAccount")
public class UserAccount implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy = GenerationType.AUTO) private long idU;
	@NotNull@Pattern(regexp = ".+@.+\\..+", message = "wrong email format") private String mailU;
	@Column(name="USER_MAIL", unique=true)@NotNull private String mdpU;
	@NotNull private String shippingAddress; //adresse de livraison
	@NotNull private String billingAddress; //adresse de facturation
	private String cellPhone;
	private String fixPhone;
	@OneToOne private Validator validator;
	@OneToOne private Administrator administrator;
	@OneToOne private Client client;
	
	
	public UserAccount(String mailU, String mdpU, String shippingAddress, String billingAddress, String cellPhone, String fixPhone){
		this.mailU = mailU;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.mdpU = mdpU;
		this.cellPhone = cellPhone;
		this.fixPhone = fixPhone;
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

	public long getIdU() {
		return this.idU;
	}	
}