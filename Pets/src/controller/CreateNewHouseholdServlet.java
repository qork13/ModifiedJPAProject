package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HouseholdDetails;
import model.Owner;
import model.PetList;

/**
 * Servlet implementation class CreateNewHouseholdServlet
 */
@WebServlet("/createNewHouseholdServlet")
public class CreateNewHouseholdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewHouseholdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //This servlet will take in all the fields for input as well as the list and send them off to the methods and helper files for creation of the record
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Create a new Pet list
		PetListItemHelper lih = new PetListItemHelper();
		
		//Create variable for household name and set it to what was entered
		String householdName = request.getParameter("householdName");
		//Display field for household name
		System.out.println("Household Name: "+ householdName);
		
		//Create variable for owner name and set it to what was entered
		String ownerName = request.getParameter("ownerName");
		
		//Create new array of selected pets to display send off to the household helper for the record
		String[] selectedPets = request.getParameterValues("allPetsToAdd");
		List<PetList> selectedPetsInList = new ArrayList<PetList>();
		
		if (selectedPets != null && selectedPets.length > 0) {
			for(int i = 0; i<selectedPets.length; i++) {
				System.out.println(selectedPets[i]);
				PetList c = lih.searchForPetById(Integer.parseInt(selectedPets[i]));
				selectedPetsInList.add(c);
			}
		}
		
		//Create a new owner constructor in Owner.java and pass in owner name
		Owner owner = new Owner(ownerName);
		
		//Create a new household detail in HouseholdDetails.java and set householdName and owner
		HouseholdDetails hd = new HouseholdDetails(householdName, owner);
		//Set the chosen list of pets in the HouseholdDetails constructor
		hd.setListOfPets(selectedPetsInList);
		
		//Create a new Household details record and insert into the database
		HouseholdDetailsHelper hdh = new HouseholdDetailsHelper();
		hdh.insertNewHouseholdDetails(hd);
		System.out.println("Success!");
		System.out.println(hdh.toString());
		
		//Go back to view all households
		getServletContext().getRequestDispatcher("/viewAllHouseholdsServlet").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
