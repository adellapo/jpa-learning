package quickstart.demo.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-10-13T19:29:47.690-0300")
@StaticMetamodel(Employee.class)
public class Employee_ {
	public static volatile SingularAttribute<Employee, Integer> id;
	public static volatile SingularAttribute<Employee, String> name;
	public static volatile SingularAttribute<Employee, Long> salary;
	public static volatile SingularAttribute<Employee, String> comments;
	public static volatile SingularAttribute<Employee, byte[]> picture;
	public static volatile SingularAttribute<Employee, String> phoneNumberForDb;
	public static volatile SingularAttribute<Employee, EmployeeType> type;
	public static volatile SingularAttribute<Employee, Date> dob;
}
