package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import bean.Account;

public class LoginDao {
	// ket noi sql sever
	Connection conn = null;
	// nem cau lenh query den sql sever
	PreparedStatement ps = null;
	// nhan ket qua tra ve
	ResultSet rs = null; 
	
	public LoginDao() {
	}

	public Account checkLogin(String mail,String pass) {

			String query ="select * from Account where user_mail=? and password =?";
			// mo ket noi den sql sever
			try {
				try {
					conn = new DBContext().getConnection();
				} catch (ClassNotFoundException e) {
					System.out.println("cant connect db2");
					e.printStackTrace();
				}
				// truyen cau lenh query vao sql sever
				ps = conn.prepareStatement(query);
				ps.setString(1, mail);
				ps.setString(2, pass);
				// excure qurey va nhan ket qua tra ve
				rs = ps.executeQuery(); 
				while (rs.next()) {
					Account acc = new Account(rs.getString(1),rs.getString(2));
					return acc;
				}
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			return null;
	}
		
	
}	
