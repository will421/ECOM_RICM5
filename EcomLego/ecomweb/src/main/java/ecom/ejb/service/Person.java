package ecom.ejb.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;



import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Person implements Serializable {
	
    @XmlElement
    String name;
    @XmlElement
	int age;
	
    
    
	@Override
	public String toString() {
		return name+":"+age;
	}
}

