package com.cxl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class ZyJdbcUtil {
	
	//连接数据库的几个属性
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL ="jdbc:oracle:thin:@192.168.1.242:1521:ORCL";
	private static final String USER_NAME = "shopping";
	private static final String PASSWORD ="shopping";  
	
	//加载驱动和连接数据库
	public static Connection getConnection() throws Exception {
		// TODO Auto-generated method stub
		Class.forName(DRIVER_NAME);
		return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
	}



	//关闭数据库连接
	public static void closeConnection(Connection connection) throws Exception{
		if(null !=connection){
			connection.close();
			connection =null;
		}
	}


	public static ResultSet querryProduct(Connection connection,int maxRow,int minRow) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from (select t.*,rownum r from (select * from SHOPPING_CATE order by cate_sale_count desc)t) where r<=? and r>=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setObject(1,maxRow);
		statement.setObject(2, minRow);
		return statement.executeQuery();
	}
	
	public static ResultSet querryFavorite(Connection connection,int maxRow,int minRow) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from (select t.*,rownum r from (select SHOPPING_CATE.* from SHOPPING_CATE,SHOPPING_LIKE_EAT where SHOPPING_CATE.CATE_ID=SHOPPING_LIKE_EAT.CATE_ID)t) where r<=? and r>=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setObject(1,maxRow);
		statement.setObject(2, minRow);
		return statement.executeQuery();
	}



	public static ResultSet querryShoppingCateImage(Connection connection,
			int cateImageId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from SHOPPING_CATE_IMAGE where cate_image_id = " +cateImageId;
		return connection.createStatement().executeQuery(sql);
	}



	public static ResultSet querryShoppingBasket(Connection connection,
			String shopping_basket_name,int user_id) throws SQLException {
		String sql;
		// TODO Auto-generated method stub
		if (shopping_basket_name !=null) {
			sql = "select * from SHOPPING_SHOPPING_BASKET where shopping_basket_name = "+"'"+shopping_basket_name+"'"+" and user_id="+user_id;
		} else {
			sql = "select * from SHOPPING_SHOPPING_BASKET where user_id="+user_id;
		}
		return connection.createStatement().executeQuery(sql);
	}



	public static void updateShoppingBasket(Connection connection,Object params[]) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update SHOPPING_SHOPPING_BASKET set shopping_basket_price = ?,shopping_basket_count = ? where shopping_basket_name = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			statement.setObject(i+1, params[i]);
		}
		statement.execute();
	}



	public static ResultSet querryShoppingCate(Connection connection, int cateId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from SHOPPING_CATE where cate_id = " +cateId ;
		return connection.createStatement().executeQuery(sql);
	}



	public static ResultSet querryShopping_cate_image(Connection connection,
			String cate_name) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from SHOPPING_CATE_IMAGE where cate_image_id = (select cate_image_id from SHOPPING_CATE where cate_name = " + "'"+cate_name+"'"+")";
		
		return connection.createStatement().executeQuery(sql);
	}



	public static void insertShoppingBasket(Connection connection,
			Object[] pramas) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into shopping_shopping_basket(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) values(?,?,?,?,?)";
//		String sql = "insert into SHOPPING_SHOPPING_BASKET values(1,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < pramas.length; i++) {
			statement.setObject(i+1, pramas[i]);
		}
		statement.execute();
	}



	public static ResultSet querryShoppingCateDetail(Connection connection,int cate_id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select t1.*,t2.*  from SHOPPING_CATE t1,SHOPPING_CATE_IMAGE t2  where t1.cate_image_id= t2.cate_image_id and t1.cate_id ="+cate_id;
		
		return connection.createStatement().executeQuery(sql);
	}



	public static ResultSet querryComment(Connection connection, int cate_id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from SHOPPING_CATE_COMMENT where cate_id = "+cate_id;
		return connection.createStatement().executeQuery(sql);
	}
	public static ResultSet querryShoppingSeckIll(Connection connection) throws Exception {
		String sql = "select * from shopping_seckill ";
		return connection.createStatement().executeQuery(sql);
	}

	public static ResultSet querryShoppingCate(Connection connection, String cate_name ) throws Exception {
		String sql = "select * from SHOPPING_CATE where cate_name  = '" +cate_name +"'" ;
		return connection.createStatement().executeQuery(sql);
	}
	public static ResultSet querryShoppingMainInfo(Connection connection) throws Exception {
		String sql = "select * from shopping_maininfo ";
		return connection.createStatement().executeQuery(sql);
	}

}
