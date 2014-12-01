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
	@Column(name="USER_MAIL", unique=true)@NotNull@Pattern(regexp = ".+@.+\\..+", message = "wrong email format") private String mailU;
	@NotNull private String mdpU;
	@NotNull private String shippingAddress; //adresse de livraison
	@NotNull private String billingAddress; //adresse de facturation
	private int cellPhone;
	private int fixPhone;
	@OneToOne private Validator validator;
    @OneToOne private Administrator administrator;
	@OneToOne private Users client;
	
	
	public UserAccount(String mailU, String mdpU, String shippingAddress, String billingAddress, int cellPhone, int fixPhone){
		this.mailU = mailU;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.mdpU = mdpU;
		this.cellPhone = cellPhone;
		this.fixPhone = fixPhone;
	}
	
	public void setClient(Users user){
		this.client=user;
	}
	
	public Users getClient(){
		return client;
	}
	
	public void setAdministrator(Administrator admin){
		this.administrator=admin;
	}
	
	public Administrator getAdministrator(){
		return administrator;
	}
	
	public void setValidator(Validator val){
		this.validator=val;
	}
	
	public Validator getValidator(){
		return validator;
	}

	public UserAccount(){
		
	}

	public String getMdpU() {
		return mdpU;
	}

	public void setMdpU(String mdpU) {
		this.mdpU = mdpU;
	}
	
	public void setMailU(String mailU) {
		this.mailU = mailU;
	}
	
	public String getMailU(){
		return mailU;
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

	public int getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(int cellPhone2) {
		this.cellPhone = cellPhone2;
	}

	public int getFixPhone() {
		return fixPhone;
	}

	public void setFixPhone(int fixPhone2) {
		this.fixPhone = fixPhone2;
	}

	public long getIdU() {
		return this.idU;
	}

	public String toString(){
		if(client==null) return "No user create";
		else return client.getNameC()+" "+client.getPrenomC()+" "+client.getId()+" "+billingAddress+" "+shippingAddress
				+" "+cellPhone+" "+fixPhone+" "+mailU+" "+mdpU;
	}	
}