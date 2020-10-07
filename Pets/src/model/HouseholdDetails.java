package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="household_details")
public class HouseholdDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="HOUSEHOLD_ID")
	private int id;
	@Column(name="HOUSEHOLD_NAME")
	private String householdName;
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="OWNER_ID")
	private Owner owner;
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinTable
	 (
	 name="pets_on_list",
	 joinColumns={ @JoinColumn(name="HOUSEHOLD_ID",
	referencedColumnName="HOUSEHOLD_ID") },
	 inverseJoinColumns={ @JoinColumn(name="PET_ID",
	referencedColumnName="ID", unique=true) }
	 )
	private List<PetList> listOfPets;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHouseholdName() {
		return householdName;
	}

	public void setHouseholdName(String householdName) {
		this.householdName = householdName;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<PetList> getListOfPets() {
		return listOfPets;
	}

	public void setListOfPets(List<PetList> listOfPets) {
		this.listOfPets = listOfPets;
	}

	
	@Override
	public String toString() {
		return "HouseholdDetails [id=" + id + ", householdName=" + householdName + ", owner=" + owner + ", listOfPets="
				+ listOfPets + "]";
	}

	public HouseholdDetails() {
		super();
	}
	
	public HouseholdDetails(int id, String householdName, Owner owner, List<PetList> listOfPets) {
		super();
		this.id = id;
		this.householdName = householdName;
		this.owner = owner;
		this.listOfPets = listOfPets;
	}
	
	public HouseholdDetails (String householdName, Owner owner, List<PetList> listOfPets) {
		super();
		this.householdName = householdName;
		this.owner = owner;
		this.listOfPets = listOfPets;
	}
	
	public HouseholdDetails(String householdName, Owner owner) {
		super();
		this.householdName = householdName;
		this.owner = owner;
	
	}

}
