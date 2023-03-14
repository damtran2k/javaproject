package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDao;
import dao.OrderDao;
import model.Cart;
import model.Order;
import model.Product;

/**
 * Servlet implementation class PayController
 */

public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public PayController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proCessRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		proCessRequest(request, response);
	}
protected void proCessRequest(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html;charset=UTF-8");
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("cart") == null) {
				session.setAttribute("cart",new Cart());
			}
			OrderDao dao = new OrderDao();
			Cart c = (Cart) session.getAttribute("cart");
			String username = request.getParameter("username");
			String discount = request.getParameter("discount");
			String address = request.getParameter("address");
		
			if (username == "" || address == "") {
				session.setAttribute("error", "please input name and address");
				response.sendRedirect("pay.jsp");
				
			}
				Order d = new Order(username, 2, discount, address,"",null );
				dao.insertOrder(d, c);
				
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println(e);
		}
		
	}

}
