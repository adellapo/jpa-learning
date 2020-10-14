package quickstart.demo.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Access(AccessType.FIELD)
public class Employee {

	public static final String LOCAL_AREA_CODE = "54";

	@Id
	@Column
	private int id;
	private String name;
	private long salary;
	@Basic(fetch = FetchType.LAZY) // con esto indico que no se va cargar de forma automatica
	private String comments;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] picture;

	@Enumerated(EnumType.STRING)
	private EmployeeType type;

	@Temporal(TemporalType.DATE) // usado por usar java.util.Date en vez de java.sql.Date
	private Date dob;
	/*
	 * si se usa @Transient hay que usar si o si @Access y especificar el modo de
	 * acceso y especificar la columna a la que persiste
	 * 
	 * @Access(AccessType.PROPERTY) --> si es a un metodo
	 */
	@Transient
	private String phoneNum;

	public Employee() {
	}

	public Employee(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getPhoneNumber() {
		return phoneNum;
	}

	public void setPhoneNumber(String num) {
		this.phoneNum = num;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public EmployeeType getType() {
		return type;
	}

	public void setType(EmployeeType type) {
		this.type = type;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	/*
	 * Si el numero tiene un largo de 12 lo deja como esta Si el numero es <> 12 le
	 * agrega el 54 Ejemplo: Entrada: 1155853411 Salida: 541155853411
	 */
	@Access(AccessType.PROPERTY)
	@Column(name = "phone")
	protected String getPhoneNumberForDb() {
		if (phoneNum.length() == 12)
			return phoneNum;
		else
			return LOCAL_AREA_CODE + phoneNum;
	}

	protected void setPhoneNumberForDb(String num) {
		if (num.startsWith(LOCAL_AREA_CODE))
			phoneNum = num.substring(3);
		else
			phoneNum = num;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", comments=" + comments + ", phoneNum="
				+ phoneNum + "]";
	}

}
