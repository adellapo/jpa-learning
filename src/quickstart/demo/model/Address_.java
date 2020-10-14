package quickstart.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-10-01T12:25:46.403-0300")
@StaticMetamodel(Address.class)
public class Address_ {
	public static volatile SingularAttribute<Address, Long> id;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> country;
	public static volatile SingularAttribute<Address, String> stateOrProvince;
	public static volatile SingularAttribute<Address, String> postalCode;
	public static volatile SingularAttribute<Address, String> street;
}
