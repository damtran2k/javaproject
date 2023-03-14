package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Product;

public class ListProductDao {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<Product> getAllProduct() {
		List<Product> list = new ArrayList<>();
		String query ="select * from products";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getString(7)));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Product> search(String txtSearch) {
		List<Product> list = new ArrayList<>();
		String query ="select * from products where product_name like ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%"+txtSearch+"%");
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getString(7)));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public Product getdetailProduct(String idSearch) {
		Product product = new Product();
		String query ="select * from products where product_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, idSearch);
			rs = ps.executeQuery();
			while (rs.next()) {
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getFloat(4));
				product.setScr(rs.getString(5));
				product.setType(rs.getString(6));
				product.setBrand(rs.getString(7));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
}
	

