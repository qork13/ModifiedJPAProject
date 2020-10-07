package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.HouseholdDetails;

public class HouseholdDetailsHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Pets");
	
			//method to insert the new household into the databases from what was sent from HouseHoldDetails
			public void insertNewHouseholdDetails(HouseholdDetails h) {
				EntityManager em = emfactory.createEntityManager();
				em.getTransaction().begin();
				em.persist(h);
				em.getTransaction().commit();
				em.close();
			}
			
			//Method to fetch and send households in database
			public List<HouseholdDetails> getHouseholds() {
				EntityManager em = emfactory.createEntityManager();
				List<HouseholdDetails> allDetails = em.createQuery("SELECT h FROM HouseholdDetails h").getResultList();
				return allDetails;
			}
			
			//Method to choose the record in the database and make sure it matches what was chosen to delete
			public void deleteList(HouseholdDetails toDelete) {
				// TODO Auto-generated method stub
				EntityManager em = emfactory.createEntityManager();
				
				em.getTransaction().begin();
				TypedQuery<HouseholdDetails> typedQuery = em.createQuery("select detail from HouseholdDetails detail "
						+ "where detail.id = :selectedId", HouseholdDetails.class);
				// Substitute parameter with actual data from the toDelete item
				typedQuery.setParameter("selectedId", toDelete.getId());
				
				// we only want one result
				typedQuery.setMaxResults(1);
				
				// get the result and save it into a new list item
				HouseholdDetails result = typedQuery.getSingleResult();
				
				// remove it
				em.remove(result);
				em.getTransaction().commit();
				em.close();
				}
			
				//Method to search database for Household by ID and return it
				public HouseholdDetails searchForHouseholdDetailsById(Integer tempId) {
				EntityManager em = emfactory.createEntityManager();
				em.getTransaction().begin();
				HouseholdDetails found = em.find(HouseholdDetails.class, tempId);
				em.close();
				return found;
				}
				
				//Method to update the record that was sent from the household details helper
				public void updateHousehold(HouseholdDetails toEdit) {
					EntityManager em = emfactory.createEntityManager();
					em.getTransaction().begin();
					em.merge(toEdit);
					em.getTransaction().commit();
					em.close();
					}
		}


