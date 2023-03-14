package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDao;
import model.Cart;
import model.Product;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartController() {
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
		doGet(request, response);
	}
	protected void proCessRequest(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);
		// lay thong tin tu nguoi dung
		String idd = request.getParameter("id");
		String action = request.getParameter("action");
		try {
			// kiem tra cart
			if(action != null && action.equalsIgnoreCase("add")) {
				if(session.getAttribute("cart") == null) {
					session.setAttribute("cart", new Cart());
				}
				int id = Integer.parseInt(idd);
				// lay san pham tuong ung voi so id
				Product product = new ListProductDao().getdetailProduct(""+id);
				// them san pham vao session
				Cart cart = (Cart) session.getAttribute("cart");
				cart.add(new Product(product.getId(),product.getName()
						,product.getDescription(),product.getPrice(),product.getScr(),product.getType(),product.getBrand(),1));
			
			// truong hop nguoi dung xoa san pham
			}else if (action != null && action.equalsIgnoreCase("remove")) {
				int id = Integer.parseInt(idd);
				Cart cart = (Cart) session.getAttribute("cart");
				//xóa san pham ra khoi session
				cart.remove(id);
			}
			response.sendRedirect("cart.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
