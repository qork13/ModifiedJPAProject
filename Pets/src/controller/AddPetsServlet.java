package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PetList;

/**
 * Servlet implementation class AddPetsServlet
 */
@WebServlet("/addPetsServlet")
public class AddPetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPetsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //This servlet is taking in type, name and owner from index.html
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String owner = request.getParameter("owner");
	
	//Send type, name and owner to constructor in PetList.java
		PetList li = new PetList(type, name, owner);
		
	//Sent record to PetListItemHelper to write to the mysql AWS database and commit the record
		PetListItemHelper dao = new PetListItemHelper();
		dao.insertPet(li);
		
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}

}
