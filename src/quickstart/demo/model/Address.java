package quickstart.demo.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity

public class Address implements Serializable {

	   
	@Id
	@Column(name = "address_id")
	private Long id;
	private String city;
	private String country;
	@Column(name = "province")
	private String stateOrProvince;
	@Column(name = "p_code")
	private String postalCode;
	private String street;
	private static final long serialVersionUID = 1L;

	public Address() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}   
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}   
	public String getStateOrProvince() {
		return this.stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}   
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}   
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", country=" + country + ", stateOrProvince=" + stateOrProvince
				+ ", postalCode=" + postalCode + ", street=" + street + "]";
	}
   
	
}
