package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pets")
public class PetList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="TYPE")
	private String type;
	@Column(name="NAME")
	private String name;
	
	
	
	//PetList no arg constructor
	public PetList() {
		super();
	}
	
	//PetList constructor taking in type, name and owner
	public PetList(String type, String name) {
		super();
		this.type = type;
		this.name = name;
		
	}
	
	
	//Getters and Setters for id, type, name, and owner
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	//Method to return the Type, Name and Owner to the program as a readable String
	
	public String returnPetDetails() {
		return "Type: " + type + " Name: " + name;
	}

	@Override
	public String toString() {
		return "PetList [id=" + id + ", type=" + type + ", name=" + name + "]";
	}

}
