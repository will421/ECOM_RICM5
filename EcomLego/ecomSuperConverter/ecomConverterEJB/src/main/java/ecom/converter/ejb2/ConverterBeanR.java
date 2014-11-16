package ecom.converter.ejb2;

import java.math.BigDecimal;

import javax.ejb.Remote;

@Remote
public interface ConverterBeanR {

    public BigDecimal dollarToYen(BigDecimal dollars); 
    
    public BigDecimal yenToEuro(BigDecimal yen);
	
	
}
