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
 * Servlet implementation class EditHouseholdDetailsServlet
 */
@WebServlet("/editHouseholdDetailsServlet")
public class EditHouseholdDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditHouseholdDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//This servelet will create new records in the Household, PetList and Owner helpers and get the records parameters and set them in the new constructor
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//New records created
		HouseholdDetailsHelper dao = new HouseholdDetailsHelper();
		PetListItemHelper plih = new PetListItemHelper();
		OwnerHelper oh = new OwnerHelper();
		
		//Find the household by id and set new HouseholdDetails constructor
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		HouseholdDetails householdToUpdate = dao.searchForHouseholdDetailsById(tempId);
		//Create new variables and set them to what was entered
		String newHouseholdName = request.getParameter("householdName");
		String ownerName = request.getParameter("ownerName");
		
		//find or add the new owner
		Owner newOwner = oh.findOwner(ownerName);
	
		try {
		//items are selected in list to add
		String[] selectedItems = request.getParameterValues("allPetsToAdd");
		List<PetList> selectedPetsInList = new ArrayList<PetList>();
		
		for (int i = 0; i < selectedItems.length; i++) {
			System.out.println(selectedItems[i]);
			PetList c = plih.searchForPetById(Integer.parseInt(selectedItems[i]));
			selectedPetsInList.add(c);
			}
		householdToUpdate.setListOfPets(selectedPetsInList);
		} 
		
		catch (NullPointerException ex) {
		// no items selected in list - set to an empty list
			List<PetList> selectedPetsInList = new ArrayList<PetList>();
			householdToUpdate.setListOfPets(selectedPetsInList);
			}
		
		householdToUpdate.setHouseholdName(newHouseholdName);
		householdToUpdate.setOwner(newOwner);
		dao.updateHousehold(householdToUpdate);
		getServletContext().getRequestDispatcher("/viewAllHouseholdsServlet").forward(request, response);
		}
	}


