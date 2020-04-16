package entityclasses;

import java.io.Serializable;

public class DepartmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idDep;
	private String nameDep;
	private int maxNPeople;

	public DepartmentDTO(int id, String name, int maxPeople) {
		this.idDep = id;
		this.nameDep = name;
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

	public int getMaxNPeople() {
		return maxNPeople;
	}

	public void setMaxNPeople(int maxNPeople) {
		this.maxNPeople = maxNPeople;
	}

}
