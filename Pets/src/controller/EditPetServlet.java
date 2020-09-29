package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PetList;

/**
 * Servlet implementation class EditPetServlet
 */
@WebServlet("/editPetServlet")
public class EditPetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    //This servlet will take in the information entered on the edit-pet.jsp and update the records in the mysql/AWS table
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PetListItemHelper dao = new PetListItemHelper();
				
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String owner = request.getParameter("owner");
				
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		PetList petToUpdate = dao.searchForPetById(tempId);
		petToUpdate.setType(type);
		petToUpdate.setName(name);
		petToUpdate.setOwner(owner);
		dao.updatePetItem(petToUpdate);
				
		getServletContext().getRequestDispatcher("/viewAllPetsServlet").forward(request, response);
	}

}
