package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import context.DBContext;
import model.Cart;
import model.Order;
import model.Product;

public class OrderDao {
	public void insertOrder (Order o,Cart c) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = new DBContext().getConnection();
			List<Product> listProduct = c.getItem();
			double price  =c.getAmount();
			String sql = "INSERT INTO orders_details VALUES(?,?,?,?)";
			st =  conn.createStatement();
			rs = st.executeQuery("SELECT TOP 1 order_id FROM orders_details ORDER BY order_id DESC ");	
			int id = 0;
			while(rs.next()){
				id = rs.getInt("order_id");
			}
			id+=1;
			String sql2=
					 "INSERT INTO myOrder (user_mail, order_status,order_date,order_discount_code,order_address) VALUES(?,?,?,?,?) ";
					
			ps = conn.prepareStatement(sql2);
			ps.setString(1, o.getUserMail());
			//ps.setString(1, ""+id);
			ps.setString(2, o.getStatus()+"");
			Date date = new Date(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			String validate = dateFormat.format(date);
			ps.setDate(3,date);
			ps.setString(4, o.getDiscount());
			ps.setString(5,o.getAddress());
			ps.executeUpdate();
			ps.close();
			PreparedStatement pStatement = conn.prepareStatement(sql);
			for (Product product : listProduct) {
				pStatement.setString(1, ""+id);
				pStatement.setString(2,""+ product.getId());
				pStatement.setString(3, ""+1);
				pStatement.setDouble(4, price);
				pStatement.executeUpdate();
			}
			pStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
