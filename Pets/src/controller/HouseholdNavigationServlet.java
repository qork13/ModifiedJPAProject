package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HouseholdDetails;

/**
 * Servlet implementation class HouseholdNavigationServlet
 */
@WebServlet("/householdNavigationServlet")
public class HouseholdNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HouseholdNavigationServlet() {
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
	//This servlet controls the edit, add, and delete forwarding once on the view all households page
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HouseholdDetailsHelper dao = new HouseholdDetailsHelper();
		String act = request.getParameter("doThisToHousehold");
		
		if (act == null) {
		// no button has been selected
				getServletContext().getRequestDispatcher("/viewAllHouseholdsServlet").forward(request, response);
			} 
		else if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				HouseholdDetails householdToDelete = dao.searchForHouseholdDetailsById(tempId);
				dao.deleteList(householdToDelete);
			} 
			catch (NumberFormatException e) {
				System.out.println("Forgot to click a button");
			} 
			finally {
				getServletContext().getRequestDispatcher("/viewAllHouseholdsServlet").forward(request, response);
			}
		} 
		
		else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				HouseholdDetails householdToEdit = dao.searchForHouseholdDetailsById(tempId);
				
				request.setAttribute("householdToEdit", householdToEdit);
				
				PetListItemHelper daoForPets = new PetListItemHelper();
				request.setAttribute("allPets", daoForPets.showAllPets());
					if(daoForPets.showAllPets().isEmpty()){
						request.setAttribute("allPets", " ");
					}
				getServletContext().getRequestDispatcher("/edit-household.jsp").forward(request, response);
				} 
			catch (NumberFormatException e) {
				getServletContext().getRequestDispatcher("/viewAllHouseholdsServlet").forward(request, response);
			}
		}
		
		else if (act.equals("add")) {
			PetListItemHelper daoForPets = new PetListItemHelper();
			request.setAttribute("allPets", daoForPets.showAllPets());
			
				if(daoForPets.showAllPets().isEmpty()){
					request.setAttribute("allPets", " ");
				}
		getServletContext().getRequestDispatcher("/new-household.jsp").forward(request, response);
		}
	}

}
