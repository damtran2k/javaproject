package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import dao.LoginDao;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			request.getSession(true).invalidate();
			String regexMail ="^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
			String regex = "^.{9,}$";
			// lay du lieu tu form
			String userID = request.getParameter("username");
			String password = request.getParameter("password");
			String remember = request.getParameter("remember");
			LoginDao loginDao = new LoginDao();
			Account account = loginDao.checkLogin(userID, password);
			

			HttpSession session = request.getSession(true);
			if(account == null) {
				session.setAttribute("error","Wrong user or password /" );
				response.sendRedirect("login.jsp");	
			}else {
				session.setAttribute("user", userID);
				// dang nhap thanh cong chuyen den tran index
				
				if(remember!= null) {
					Cookie cookie = new Cookie("user", userID);
					cookie.setMaxAge(300);
					response.addCookie(cookie);
				
				}
				response.sendRedirect("admin/index.jsp");
			}
			
		} catch (NullPointerException e) {
			
			RequestDispatcher  rd = request.getRequestDispatcher("login.jsp");
		}catch (Exception ex) {
			response.getWriter().println(ex);
		}
		
	}

}
