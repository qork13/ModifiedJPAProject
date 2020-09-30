package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PetList;

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    //This servlet is called after a pet is selected in the pet-list.jsp and will choose whether to delete, edit or add
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PetListItemHelper dao = new PetListItemHelper();
		String act = request.getParameter("doThisToPet");
		String path = "/viewAllPetsServlet";
		
		//This if/else statement will take the action that comes in via the act variable and then search for the pet by ID
		//and either delete from the mysql/AWS database, take the user to edit-pet.jsp to update the record , or go to the index.html
		//to add a new record
		
		if(act.equals("delete")) {
			
			try {
			Integer tempId = Integer.parseInt(request.getParameter("id"));
			PetList deletePet = dao.searchForPetById(tempId);
			dao.deletePet(deletePet);
			} catch (NumberFormatException e) {
				System.out.println("Please select a pet");
			}
			
		}
			
		else if(act.equals("edit")) {
			try {
			Integer tempId = Integer.parseInt(request.getParameter("id"));
			PetList petToEdit = dao.searchForPetById(tempId);
			request.setAttribute("petToEdit", petToEdit);
			path = "/edit-pet.jsp";
			} catch (NumberFormatException e) {
				System.out.println("Please select a pet");
			}
		}
		
		else if
		(act.equals("add")) {
		path = "/index.html";
		}
		getServletContext().getRequestDispatcher(path).forward(request,response);
		}
	}


