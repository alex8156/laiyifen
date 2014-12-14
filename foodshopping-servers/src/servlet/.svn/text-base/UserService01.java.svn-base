package servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cxl.util.ZyJdbcUtil;

import javabean.ShoppingCate;
import javabean.ShoppingCategory;
import javabean.ShoppingCategoryImage;
import javabean.ShoppingFlavor;




public class UserService01 {
	
	
	
	public UserService01() {
		
	}

	public ArrayList<ShoppingCategory> querryCategory() throws Exception {
		ArrayList<ShoppingCategory> shoppingCategorys = new ArrayList<ShoppingCategory>();
		Connection connection = null;
		ShoppingCategory shoppingCategory = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querryCategory(connection);
			if (resultSet != null) {
				while (resultSet.next()) {
					//种类
					int category_id = resultSet.getInt("category_id");
					String category_name = resultSet.getString("category_name");
					String category_description = resultSet.getString("category_description");
					String category_image = resultSet.getString("category_image");
					shoppingCategory = new ShoppingCategory(category_id, category_name, category_description, category_image);
					shoppingCategorys.add(shoppingCategory);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCategorys;
	}

	public ArrayList<ShoppingFlavor> querryCategoryFlavor() throws Exception {
		ArrayList<ShoppingFlavor> shoppingFlavors = new ArrayList<ShoppingFlavor>();
		Connection connection = null;
		ShoppingFlavor shoppingFlavor = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querryCategoryFlavor(connection);
			if (resultSet != null) {
				while (resultSet.next()) {
					//口味
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String flavor_category_name = resultSet.getString("flavor_category_name");
					String flavor_category_description = resultSet.getString("flavor_category_description");
					String flavor_category_image = resultSet.getString("flavor_category_image");
					shoppingFlavor = new ShoppingFlavor(flavor_category_id, flavor_category_name, flavor_category_description, flavor_category_image);
					shoppingFlavors.add(shoppingFlavor);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingFlavors;
	}
	
	public ArrayList<ShoppingCate> querryDefault(int categoryflavor_id) throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querryDefault(connection,categoryflavor_id);
			if (resultSet != null) {
				while (resultSet.next()) {
					
					int cate_id = resultSet.getInt("cate_id");
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getDouble("cate_oldprice");
					double cate_price = resultSet.getDouble("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					//这里的categoryflavor_id等于category_id
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count,cate_description,cate_oldprice,cate_price,
							cate_image_id,category_id,flavor_category_id,cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}

	public ArrayList<ShoppingCate> querryDefault2(int categoryflavor_id) throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querryDefault2(connection,categoryflavor_id);
			if (resultSet != null) {
				while (resultSet.next()) {
					
					int cate_id = resultSet.getInt("cate_id");
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getDouble("cate_oldprice");
					double cate_price = resultSet.getDouble("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					//这里的categoryflavor_id等于category_id
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count,cate_description,cate_oldprice,cate_price,
							cate_image_id,category_id,flavor_category_id,cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}
	
	public ArrayList<ShoppingCate> querryPrice(int category_id) throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querryPrice(connection,category_id);
			if (resultSet != null) {
				while (resultSet.next()) {
					
					int cate_id = resultSet.getInt("cate_id");
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getDouble("cate_oldprice");
					double cate_price = resultSet.getDouble("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
//					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count,cate_description,cate_oldprice,cate_price,
							cate_image_id,category_id,flavor_category_id,cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}
	
	public ArrayList<ShoppingCate> querryPrice2(int categoryflavor_id) throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querryPrice2(connection,categoryflavor_id);
			if (resultSet != null) {
				while (resultSet.next()) {
					
					int cate_id = resultSet.getInt("cate_id");
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getDouble("cate_oldprice");
					double cate_price = resultSet.getDouble("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count,cate_description,cate_oldprice,cate_price,
							cate_image_id,category_id,flavor_category_id,cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}
	
	
	public ArrayList<ShoppingCate> querrySales(int category_id) throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querrySales(connection,category_id);
			if (resultSet != null) {
				while (resultSet.next()) {
					
					int cate_id = resultSet.getInt("cate_id");
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getDouble("cate_oldprice");
					double cate_price = resultSet.getDouble("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
//					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count,cate_description,cate_oldprice,cate_price,
							cate_image_id,category_id,flavor_category_id,cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}
	
	public ArrayList<ShoppingCate> querrySales2(int categoryflavor_id) throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querrySales2(connection,categoryflavor_id);
			if (resultSet != null) {
				while (resultSet.next()) {
					
					int cate_id = resultSet.getInt("cate_id");
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getDouble("cate_oldprice");
					double cate_price = resultSet.getDouble("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count,cate_description,cate_oldprice,cate_price,
							cate_image_id,category_id,flavor_category_id,cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}
	
	public ArrayList<ShoppingCate> querryCateTrack() throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querryCateTrack(connection);
			if (resultSet != null) {
				while (resultSet.next()) {
					
					int cate_id = resultSet.getInt("cate_id");
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getDouble("cate_oldprice");
					double cate_price = resultSet.getDouble("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count,cate_description,cate_oldprice,cate_price,
							cate_image_id,category_id,flavor_category_id,cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}

	public ArrayList<ShoppingCate> querryFlavorEat() throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querryFlavorEat(connection);
			if (resultSet != null) {
				while (resultSet.next()) {
					
					int cate_id = resultSet.getInt("cate_id");
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getDouble("cate_oldprice");
					double cate_price = resultSet.getDouble("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count,cate_description,cate_oldprice,cate_price,
							cate_image_id,category_id,flavor_category_id,cate_sale_info);
					shoppingCates.add(shoppingCate);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		return shoppingCates;
	}


	
//========================================================================================
	//商品详情图片（4张）
	public ShoppingCategoryImage querryShoppingCategoryImage(int cateImageId) throws Exception {
		Connection connection = null;
		ShoppingCategoryImage shoppingCategoryImage = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.querryShoppingCategoryImage(connection,cateImageId);
			if (resultSet != null) {
				while (resultSet.next()) {
					int category_image_id = resultSet.getInt("category_image_id");
					String category_image_path01 = resultSet.getString("category_image_path01");
					String category_image_path02 = resultSet.getString("category_image_path02");
					String category_image_path03 = resultSet.getString("category_image_path03");
					String category_image_path04 = resultSet.getString("category_image_path04");
					shoppingCategoryImage = new ShoppingCategoryImage(category_image_id, category_image_path01, category_image_path02, category_image_path03, category_image_path04);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				WbJdbcUtil.closeConnection(connection);
			}
		}
		
		return shoppingCategoryImage;
	}
	
	public ArrayList<ShoppingCate> insertCate(int cate_id) throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = WbJdbcUtil.getConnection();
			WbJdbcUtil.insertCateId(connection,cate_id);
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (connection != null) {
			WbJdbcUtil.closeConnection(connection);
		}
	}
		return shoppingCates;
	
	
	}	
	
	
	public ArrayList<ShoppingCate> deleteCate(int cate_id) throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		// TODO Auto-generated method stub
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.deleteCate(connection,cate_id);
			if (resultSet!=null) {
				while (resultSet.next()) {
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getDouble("cate_oldprice");
					double cate_price = resultSet.getDouble("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count,cate_description,cate_oldprice,cate_price,
							cate_image_id,category_id,flavor_category_id,cate_sale_info);
					shoppingCates.add(shoppingCate);
					
				}
			}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (connection != null) {
			WbJdbcUtil.closeConnection(connection);
		}
	}
		return shoppingCates;
	
	
	}	
	
	
	public ArrayList<ShoppingCate> deleteFootCate(int cate_id) throws Exception {
		ArrayList<ShoppingCate> shoppingCates = new ArrayList<ShoppingCate>();
		// TODO Auto-generated method stub
		Connection connection = null;
		ShoppingCate shoppingCate = null;
		try {
			connection = WbJdbcUtil.getConnection();
			ResultSet resultSet = WbJdbcUtil.deleteFootCate(connection,cate_id);
			if (resultSet!=null) {
				while (resultSet.next()) {
					String cate_name = resultSet.getString("cate_name");
					int cate_inventory = resultSet.getInt("cate_inventory");
					int cate_sale_count = resultSet.getInt("cate_sale_count");
					String cate_description = resultSet.getString("cate_description");
					double cate_oldprice = resultSet.getDouble("cate_oldprice");
					double cate_price = resultSet.getDouble("cate_price");
					int cate_image_id = resultSet.getInt("cate_image_id");
					
					int category_id = resultSet.getInt("category_id");
					int flavor_category_id = resultSet.getInt("flavor_category_id");
					String cate_sale_info = resultSet.getString("cate_sale_info");
					shoppingCate = new ShoppingCate(cate_id, cate_name, cate_inventory, cate_sale_count,cate_description,cate_oldprice,cate_price,
							cate_image_id,category_id,flavor_category_id,cate_sale_info);
					shoppingCates.add(shoppingCate);
					
				}
			}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (connection != null) {
			WbJdbcUtil.closeConnection(connection);
		}
	}
		return shoppingCates;
	
	
	}	
	
	

}
