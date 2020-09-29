package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.PetList;

public class PetListItemHelper {
	
	static EntityManagerFactory emfactory =	Persistence.createEntityManagerFactory("Pets");

	//Backend method to add the pet to the database
	public void insertPet(PetList li) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();

		
	}
	
	//Backend method to query the database and return the list of pets
	public List<PetList> showAllPets(){
		EntityManager em = emfactory.createEntityManager();
		List<PetList> allPets = em.createQuery("Select p from PetList p").getResultList();
		
		return allPets;
	}
	
	//Backend method to query the database to find a pet and delete the pet from the database
	public void deletePet(PetList toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetList> typedQuery = em.createQuery("Select li from PetList li where"
				+ " li.type = :selectedType and li.name = :selectedName and li.owner = :selectedOwner", PetList.class);
		
		typedQuery.setParameter("selectedType", toDelete.getType());
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		
		typedQuery.setMaxResults(1);
		
		PetList result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	//Backend method to take PetList and find the ID to edit in the database
	public PetList searchForPetById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		PetList found = em.find(PetList.class, idToEdit);
		em.close();
		return	found;
	}

	//Backend method to update the selected item with the values user selected
	public void updatePetItem(PetList toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}

	//Backend method to find the pet by Type from the database and return it
	public List<PetList> searchForPetByType(String petType) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetList> typedQuery	= em.createQuery("select li from PetList li where"
				+ " li.type = :selectedType", PetList.class);
		typedQuery.setParameter("selectedType", petType);
		List<PetList>	foundPets = typedQuery.getResultList();
		em.close();
		return	foundPets;
		
	}

	//Backend method to find the pet by Name from the database and return it
	public List<PetList> searchForPetByName(String petName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetList>	typedQuery	=	em.createQuery("select	li	from PetList li where "
				+ " li.name	= :selectedName", PetList.class);
		typedQuery.setParameter("selectedName",	petName);
		List<PetList>	foundPets = typedQuery.getResultList();
		em.close();
		return	foundPets;
	}
	
	//Backend method to find the pet by Owner from the database and return it
	public List<PetList> searchForPetByOwner(String petOwner) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetList>	typedQuery	=	em.createQuery("select	li	from PetList li where "
				+ " li.owner = :selectedOwner", PetList.class);
		typedQuery.setParameter("selectedOwner", petOwner);
		List<PetList>	foundPets = typedQuery.getResultList();
		em.close();
		return	foundPets;
	}
	
	//Backend method to close the database connection
	public void cleanUp() {
		emfactory.close();
	}

}
