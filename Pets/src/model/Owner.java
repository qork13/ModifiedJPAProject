
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="owner")
public class Owner {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OWNER_ID")
	private int id;
	@Column(name="OWNER_NAME")
	private String ownerName;
	
	public Owner() {
	super();
	// TODO Auto-generated constructor stub
	}
	public Owner(int id, String ownerName) {
	super();
	this.id = id;
	this.ownerName = ownerName;
	}
	public Owner(String ownerName) {
	super();
	this.ownerName = ownerName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	@Override
	public String toString() {
		return "Owner [id=" + id + ", ownerName=" + ownerName + "]";
	}
	
}
