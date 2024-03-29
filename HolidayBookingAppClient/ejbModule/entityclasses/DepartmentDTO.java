package entityclasses;

import java.io.Serializable;

public class DepartmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idDep;
	private String nameDep;
	private String nameDepUpper;
	private int maxNPeople;

	public DepartmentDTO(int id, String name, String nameUpper, int maxPeople) {
		this.idDep = id;
		this.nameDep = name;
		this.nameDepUpper = nameUpper;
		this.maxNPeople = maxPeople;
	}

	public int getIdDep() {
		return idDep;
	}

	public void setIdDep(int idDep) {
		this.idDep = idDep;
	}

	public String getNameDep() {
		return nameDep;
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
