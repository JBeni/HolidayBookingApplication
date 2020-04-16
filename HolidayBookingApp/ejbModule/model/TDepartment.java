package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="T_DEPARTMENTS")
@NamedQuery(name="TDepartment.findAll", query="SELECT t FROM TDepartment t")
public class TDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	public TDepartment(int id, String name, String nameUpper, int maxPeople) {
		this.idDep = id;
		this.nameDep = name;
		this.nameDepUpper = nameUpper;
		this.maxNPeople = maxPeople;
	}

	public TDepartment() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DEP")
	private int idDep;

	@Column(name="DEP_NAME")
	private String nameDep;

	@Column(name="DEP_NAME_UPPER")
	private String nameDepUpper;

	@Column(name="MAX_N_PEOPLE")
	private int maxNPeople;

	public int getIdDep() {
		return this.idDep;
	}

	public void setIdDep(int idDep) {
		this.idDep = idDep;
	}

	public String getNameDep() {
		return this.nameDep;
	}

	public void setNameDep(String nameDep) {
		this.nameDep = nameDep;
	}

	public String getNameDepUpper() {
		return nameDepUpper;
	}

	public void setNameDepUpper(String nameDepUpper) {
		this.nameDepUpper = nameDepUpper;
	}

	public int getMaxNPeople() {
		return maxNPeople;
	}

	public void setMaxNPeople(int maxNPeople) {
		this.maxNPeople = maxNPeople;
	}

}
