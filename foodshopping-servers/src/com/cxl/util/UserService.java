package com.cxl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javabean.ShoppingCate;
import javabean.ShoppingCateComment;
import javabean.ShoppingCateDetail;
import javabean.ShoppingCateImage;
import javabean.ShoppingMainInfo;
import javabean.ShoppingSeckIll;



public class UserService implements UserServiceInterface {
	
	
	
	public UserService() {
		
	}

	public ArrayList<ShoppingCate> querryProduct(int maxRow,int minRow) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = ZyJdbcUtil.getConnection();
			ResultSet resultSet = ZyJdbcUtil.querryProduct(connection,maxRow,minRow);
			if (resultSet != null) {
				while (resultSet.next()) {
					int cate_id = resultSet.getInt("cate_id");
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getInt("cate_oldprice");
					double cate_price = resultSet.getInt("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count, cate_description, cate_oldprice, cate_price, cate_image_id, category_id, flavor_category_id, cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				ZyJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}
	//查询我的爱吃
	public ArrayList<ShoppingCate> querryFavorite(int maxRow,int minRow) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = ZyJdbcUtil.getConnection();
			ResultSet resultSet = ZyJdbcUtil.querryFavorite(connection,maxRow,minRow);
			if (resultSet != null) {
				while (resultSet.next()) {
					int cate_id = resultSet.getInt("cate_id");
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getInt("cate_oldprice");
					double cate_price = resultSet.getInt("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count, cate_description, cate_oldprice, cate_price, cate_image_id, category_id, flavor_category_id, cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				ZyJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}
	
	
	public ShoppingCateImage querryShoppingCateImage(int cateImageId) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		ShoppingCateImage shoppingCateImage = null;
		try {
			connection = ZyJdbcUtil.getConnection();
			ResultSet resultSet = ZyJdbcUtil.querryShoppingCateImage(connection,cateImageId);
			if (resultSet != null) {
				while (resultSet.next()) {
					int cate_image_id = resultSet.getInt("cate_image_id");
					String cate_image_path01 = resultSet.getString("cate_image_path01");
					String cate_image_path02 = resultSet.getString("cate_image_path02");
					String cate_image_path03 = resultSet.getString("cate_image_path03");
					String cate_image_path04 = resultSet.getString("cate_image_path04");
					shoppingCateImage = new ShoppingCateImage(cate_image_id, cate_image_path01, cate_image_path02, cate_image_path03, cate_image_path04);
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				ZyJdbcUtil.closeConnection(connection);
			}
		}
		
		return shoppingCateImage;
	}

	public int addToBasket(int user_id,String cate_name,double cate_price) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		
		try {
			connection = ZyJdbcUtil.getConnection();
			ResultSet resultSet = ZyJdbcUtil.querryShoppingBasket(connection,cate_name,user_id);
			if (resultSet != null&&resultSet.next()) {
				int shopping_basket_count = resultSet.getInt("shopping_basket_count")+1; //数量加1
				String shopping_basket_price = "￥"+ cate_price; //单价乘以数量
				Object params[] = {shopping_basket_price,shopping_basket_count,cate_name};
				ZyJdbcUtil.updateShoppingBasket(connection,params);
			}else {
				Object params[] = null;
					int shopping_basket_count = 1;
					String shopping_basket_image = null;
					ResultSet resultSet3 = ZyJdbcUtil.querryShopping_cate_image(connection,cate_name);
					if (resultSet3 != null&&resultSet3.next()) {
						shopping_basket_image = resultSet3.getString("cate_image_path01");
						String cate_price2 = "￥"+cate_price ;
						params = new Object[]{user_id,cate_name,cate_price2,shopping_basket_image,shopping_basket_count};
				}
				ZyJdbcUtil.insertShoppingBasket(connection,params);
			}
			
			ResultSet resultSetAfterAdd = ZyJdbcUtil.querryShoppingBasket(connection,null,user_id);
			int count = 0;
			while (resultSetAfterAdd != null&&resultSetAfterAdd.next()) {
				count += resultSetAfterAdd.getInt("shopping_basket_count");
			}
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			if (connection != null) {
				ZyJdbcUtil.closeConnection(connection);
			}
		}
	}
	
	//遍历数据
	public ShoppingCateDetail querryShoppingCateDetail(int cate_id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		ShoppingCateDetail shoppingCateDetail = null;
		ShoppingCateImage shoppingCateImage = null; 
		try {
			connection = ZyJdbcUtil.getConnection();
			ResultSet resultSet = ZyJdbcUtil.querryShoppingCateDetail(connection,cate_id);
			if (resultSet != null) {
				while (resultSet.next()) {
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getInt("cate_oldprice");
					double cate_price = resultSet.getInt("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					
					String cate_image_path01 = resultSet.getString("cate_image_path01");
					String cate_image_path02 = resultSet.getString("cate_image_path02");
					String cate_image_path03 = resultSet.getString("cate_image_path03");
					String cate_image_path04 = resultSet.getString("cate_image_path04");
					shoppingCateImage = new ShoppingCateImage(cate_image_id, cate_image_path01, cate_image_path02, cate_image_path03, cate_image_path04);
					
					
//					int cate_comment_id = resultSet.getInt("cate_comment_id");
//					String cate_comment_date = resultSet.getString("cate_comment_date");
//					String cate_user_name = resultSet.getString("cate_user_name");
//					String cate_comment_context = resultSet.getString("cate_comment_context");
//					int cate_star_level = resultSet.getInt("cate_star_level");
//					shoppingCateComment = new ShoppingCateComment(cate_comment_id,cate_id,cate_comment_date,cate_user_name,cate_comment_context,cate_star_level); 
					
					shoppingCateDetail = new ShoppingCateDetail(cate_id, cate_name, cate_inventory, cate_sale_count, cate_description, cate_oldprice, cate_price, cate_image_id, category_id, flavor_category_id, cate_sale_info, shoppingCateImage);
				}
			}
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (connection !=null ) {
				try {
					ZyJdbcUtil.closeConnection(connection);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return shoppingCateDetail;
	}

	public ArrayList<ShoppingCateComment> querryShoppingCateComment(int cate_id) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		ArrayList<ShoppingCateComment> shoppingCateComments = new ArrayList<ShoppingCateComment>();
		ShoppingCateComment shoppingCateComment = null;
		try {
			connection = ZyJdbcUtil.getConnection();
			ResultSet resultSet = ZyJdbcUtil.querryComment(connection, cate_id);
			if (resultSet != null) {
				while (resultSet.next()) {
					int cate_comment_id = resultSet.getInt("cate_comment_id");
					String cate_comment_date = resultSet.getDate("cate_comment_date").toString();
					String cate_user_name = resultSet.getString("cate_user_name");
					String cate_comment_context = resultSet.getString("cate_comment_context");
					int cate_star_level = resultSet.getInt("cate_star_level");
					shoppingCateComment = new ShoppingCateComment(cate_comment_id, cate_id, cate_comment_date, cate_user_name, cate_comment_context, cate_star_level);
					shoppingCateComments.add(shoppingCateComment);
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ZyJdbcUtil.closeConnection(connection);
		}
		return shoppingCateComments;
	}
	//限时抢购
	public ArrayList<ShoppingSeckIll> querryShoppingSeckIll() throws Exception {
		Connection connection = null;
		ArrayList<ShoppingSeckIll> shoppingSeckIllList = new ArrayList<ShoppingSeckIll>();
		ShoppingSeckIll shoppingSeckIll = null;
		try {
			connection = ZyJdbcUtil.getConnection();
			ResultSet resultSet = ZyJdbcUtil.querryShoppingSeckIll(connection);
			if (resultSet != null) {
				while (resultSet.next()) {
					int seckill_ID = resultSet.getInt("seckill_ID");
					int cate_id = resultSet.getInt("cate_id");
					long seckill_time = resultSet.getInt("seckill_time");
					shoppingSeckIll = new ShoppingSeckIll(seckill_ID, cate_id, seckill_time);
					shoppingSeckIllList.add(shoppingSeckIll);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ZyJdbcUtil.closeConnection(connection);
		}
		return shoppingSeckIllList;
	}
	public ArrayList<ShoppingCate> querryShoppingCate(String cate_name) throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = ZyJdbcUtil.getConnection();
			ResultSet resultSet = ZyJdbcUtil.querryShoppingCate(connection,cate_name);
			if (resultSet != null) {
				while (resultSet.next()) {
					int cate_id = resultSet.getInt("cate_id");
					cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getInt("cate_oldprice");
					double cate_price = resultSet.getInt("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count, cate_description, cate_oldprice, cate_price, cate_image_id, category_id, flavor_category_id, cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				ZyJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}
	public ArrayList<ShoppingMainInfo> querryShoppingMainInfo() throws Exception {
		Connection connection = null;
		ArrayList<ShoppingMainInfo> shoppingMainInfoList=new ArrayList<ShoppingMainInfo>();
		ShoppingMainInfo shoppingMainInfo = null;
		try {
			connection = ZyJdbcUtil.getConnection();
			ResultSet resultSet = ZyJdbcUtil.querryShoppingMainInfo(connection);
			if (resultSet != null) {
				while (resultSet.next()) {
					int ID = resultSet.getInt("ID");
					int cate_id = resultSet.getInt("cate_id");
					shoppingMainInfo = new ShoppingMainInfo(ID, cate_id);
					shoppingMainInfoList.add(shoppingMainInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ZyJdbcUtil.closeConnection(connection);
		}
		return shoppingMainInfoList;
	}
}
