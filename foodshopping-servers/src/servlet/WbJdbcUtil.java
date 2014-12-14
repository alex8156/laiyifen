package servlet;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class WbJdbcUtil {
	
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

	//查询表shopping_category-商品种类表
	public static ResultSet querryCategory(Connection connection) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from shopping_category";
		return connection.createStatement().executeQuery(sql);
	}
	
	//查询表shopping_flavor_category-商品口味表
	public static ResultSet querryCategoryFlavor(Connection connection) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from shopping_flavor_category";
		return connection.createStatement().executeQuery(sql);
	}
	
	//查询表shopping_cate 经典
	public static ResultSet querryDefault(Connection connection,int categoryflavor_id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from shopping_cate where category_id = " +categoryflavor_id;
		return connection.createStatement().executeQuery(sql);
	}
	
	//查询表shopping_cate_track 美味足迹
		public static ResultSet querryFootPrint(Connection connection) throws Exception {
			// TODO Auto-generated method stub
			String sql = "select * from shopping_cate_track" ;
			return connection.createStatement().executeQuery(sql);
		}


	//查询表shopping_cate 口味
		public static ResultSet querryDefault2(Connection connection,int category_flavor_id) throws Exception {
			// TODO Auto-generated method stub
			String sql = "select * from shopping_cate where flavor_category_id = " +category_flavor_id;
			return connection.createStatement().executeQuery(sql);
		}	
	//查询表shopping_cate 经典-价格-降序
		public static ResultSet querryPrice(Connection connection,int category_id) throws Exception {
			// TODO Auto-generated method stub
			String sql = "select * from (select t.*,rownum r from (select * from SHOPPING_CATE where category_id =  "+category_id+" order by cate_price desc)t)";
			return connection.createStatement().executeQuery(sql);
		}
		//查询表shopping_cate 口味-价格-降序
		public static ResultSet querryPrice2(Connection connection,int flavor_category_id) throws Exception {
					// TODO Auto-generated method stub
					String sql = "select * from (select t.*,rownum r from (select * from SHOPPING_CATE where flavor_category_id =  "+flavor_category_id+" order by cate_price desc)t)";
					return connection.createStatement().executeQuery(sql);
				}
		//查询表shopping_cate 经典-销量-降序	
		public static ResultSet querrySales(Connection connection, int category_id) throws Exception {
			// TODO Auto-generated method stub
			String sql = "select * from (select t.*,rownum r from (select * from SHOPPING_CATE where category_id =  "+category_id+" order by cate_sale_count desc)t)";
			return connection.createStatement().executeQuery(sql);
		}
	
		//查询表shopping_cate 口味-销量-降序	
			public static ResultSet querrySales2(Connection connection, int flavor_category_id) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select * from (select t.*,rownum r from (select * from SHOPPING_CATE where flavor_category_id =  "+flavor_category_id+" order by cate_sale_count desc)t)";
				return connection.createStatement().executeQuery(sql);
			}
		//查询shopping_cate_track表	
			public static ResultSet querryCateTrack(Connection connection) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select shopping_cate.* from shopping_cate_track ,shopping_cate where shopping_cate_track.cate_id=shopping_cate.cate_id";
				return connection.createStatement().executeQuery(sql);
			}
		//查询shopping_cate_track表	
			public static ResultSet querryFlavorEat(Connection connection) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select shopping_cate.* from shopping_like_eat ,shopping_cate where shopping_like_eat.cate_id=shopping_cate.cate_id";
				return connection.createStatement().executeQuery(sql);
			}
			
		//删除shopping_like_eat表某条数据
			public static ResultSet deleteCate(Connection connection ,int cate_id) throws Exception {
				// TODO Auto-generated method stub
				String sql = "delete from shopping_like_eat where cate_id= " +cate_id;
				String querrySql = "select shopping_cate.* from shopping_like_eat ,shopping_cate where shopping_like_eat.cate_id=shopping_cate.cate_id";
				java.sql.Statement statement  = connection.createStatement();
				statement.execute(sql);
				return statement.executeQuery(querrySql);
			}
			
			//删除shopping_cate_track表某条数据
			public static ResultSet deleteFootCate(Connection connection ,int cate_id) throws Exception {
				// TODO Auto-generated method stub
				String sql = "delete from shopping_cate_track where cate_id= " +cate_id;
				String querrySql = "select shopping_cate.* from shopping_cate_track ,shopping_cate where shopping_cate_track.cate_id=shopping_cate.cate_id";
				java.sql.Statement statement  = connection.createStatement();
				statement.execute(sql);
				return statement.executeQuery(querrySql);
			}	
			
//================================================
	//商品种类图片
	public static ResultSet querryShoppingCategoryImage(Connection connection,
			int categorymage) throws Exception {
	String sql = "select * from SHOPPING_CATEGORY_IMAGE where category_image= " +categorymage;
	return connection.createStatement().executeQuery(sql);
	}
	
	//种类id
	public static ResultSet querryShoppingCategory(Connection connection, int categoryid) throws Exception {
		String sql = "select * from SHOPPING_CATEGORY where category_id = " +categoryid ;
		return connection.createStatement().executeQuery(sql);
	}
		
		
	//商品种类描述
	public static ResultSet querryShoppingCategoryDescription(Connection connection, int categorydescription) throws Exception {
		String sql = "select * from SHOPPING_CATEGORY where category_description= " +categorydescription ;
		return connection.createStatement().executeQuery(sql);
	}
		


	public static ResultSet querryShopping_category_image(Connection connection,
			String category_image) throws Exception {
		String sql = "select * from SHOPPING_CATEGORY_IMAGE where category_image= (select category_image from SHOPPING_CATEGORY where category_image = " +"'"+category_image+"'"+")" ;
		return connection.createStatement().executeQuery(sql);
	}
//=====================================================
	//口味图片
	public static ResultSet querryShoppingCategoryFlavorImage(Connection connection,
			int flavormage) throws Exception {
		String sql = "select * from FLAVOR_CATEGORY_IMAGE where category_image= " +flavormage;
		return connection.createStatement().executeQuery(sql);
	}
	
	//口味id
	public static ResultSet querryShoppingFlavorCategory(Connection connection, int flavorcategoryid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from SHOPPING_FLAVOR_CATEGORY where flavor_category_id = " +flavorcategoryid ;
		return connection.createStatement().executeQuery(sql);
	}
		
	//口味描述
	public static ResultSet querryShoppingFlavorCategoryDescription(Connection connection, int flavorcategorydescription) throws Exception {
		String sql = "select * from SHOPPING_FLAVOR_CATEGORY where flavor_category_description= " +flavorcategorydescription ;
		return connection.createStatement().executeQuery(sql);
	}	

	public static ResultSet querryFlavor_category_image(Connection connection,
			String flavor_category_image) throws Exception {
		String sql = "select * from FLAVOR_CATEGORY_IMAGE where flavor_category_image= (select flavor_category_image from SHOPPING_FLAVOR_CATEGORY where flavor_category_image = " +"'"+flavor_category_image+"'"+")" ;
		return connection.createStatement().executeQuery(sql);
	}
//====================================================
	
	
	
	
	
//====================================	
	public static ResultSet querryShoppingBasket(Connection connection,
			String shopping_basket_name) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from SHOPPING_SHOPPING_BASKET where shopping_basket_name = "+"'"+shopping_basket_name+"'";
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


	public static void insertShoppingBasket(Connection connection,
			Object[] pramas) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into SHOPPING_SHOPPING_BASKET values(1,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < pramas.length; i++) {
			statement.setObject(i+1, pramas[i]);
		}
		statement.execute();
	}

	public static void insertCateId(Connection connection,int cate_id)throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into shopping_cate_track(cate_id) values("+cate_id+")";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.execute();
	}


}
