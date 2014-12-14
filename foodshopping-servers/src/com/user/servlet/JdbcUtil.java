package com.user.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * 数据库 工具类
 * @author panzhipeng
 */
public class JdbcUtil {

	// 数据库连接的 几个属性
	private static final String dirverName = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@192.168.1.242:1521:orcl";
	private static final String username = "shopping";
	private static final String password = "shopping";
	
	public static void main(String[] args) {
		getConnection();
	}
	/**
	 * 获取连接对象
	 */
	public static Connection getConnection() {
		Connection connection = null;
		// 1.  2.
		try {
			Class.forName(dirverName);
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("连接成功");
		} catch (Exception e) {
			System.out.println("数据库连接异常！");
			e.printStackTrace();
		}
		return connection;
	}

	
	/**
	 * 执行查询操作
	 * @param connection 连接对象
	 * @param sql		查询SQL语句
	 */
	public static ResultSet executeQuery(Connection connection, String sql) throws Exception {
		// 3， 4
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		return resultSet;
	}
	
	/**
	 * 执行预处理查询
	 * @param connection
	 * @param querySql
	 * @param params
	 * @return
	 */
	public static ResultSet executeQuery(Connection connection, String querySql, Object[] params) throws Exception {
		PreparedStatement preparedStatement = connection.prepareStatement(querySql);
		if(null != params) {
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setObject(i+1, params[i]);
			}
		}
		return preparedStatement.executeQuery();
	}
	

	/**
	 * 执行更新操作
	 * @param connection 
	 * @param sql	增，删，改  3种SQL语句
	 * @return
	 */
	public static int executeUpdate(Connection connection, String sql)  throws Exception {
		Statement statement = connection.createStatement();
		return statement.executeUpdate(sql);
	}

	/**
	 * 执行预处理  更新（增加，删除，修改）
	 * @param connection	连接对象
	 * @param sql			SQL语句
	 * @param params		SQL的参数
	 * @return
	 */
	public static int executeUpdate2(Connection connection, String sql, Object[] params)  throws Exception {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		if(null != params) {
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setObject(i+1, params[i]);
			}
		}
		return preparedStatement.executeUpdate();
	}
	
	
	/**
	 * 关闭连接
	 * @param connection
	 */
	public static void closeConnection(Connection connection) {
		// 6
		if(null != connection) {
			try {
				connection.close();
				connection = null;
				//System.out.println("关闭数据库连接！");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 执行批量增加
	 */
	public static void extcuteBatch() {

	}

	/**
	 * 获取某个表的总记录数
	 * @param coutSql
	 * @return
	 */
	public static int getCount(Connection connection, String tableName) throws Exception {
		int countRow = 0;
		String coutSql = "SELECT count(*) from " + tableName;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(coutSql);
		if(resultSet.next()) {
			countRow = resultSet.getInt(1);
		}
		return countRow;
	}

	/**
	 * 查询分页结果集
	 * @param connection	连接对象
	 * @param tableName		表名
	 * @param orderBy		排序列名
	 * @param beiginIndex	起始记录
	 * @param endIndex		结束记录
	 * @return	结果集ResultSet
	 */
	public static ResultSet queryPager(Connection connection, String tableName, String orderBy,
			int beiginIndex, int endIndex) throws Exception {
//		SELECT * FROM (SELECT rownum num, s.* FROM  
//		(SELECT * FROM student ORDER BY id) s)
//		WHERE num>=1  and num<=5;
		String querySql = "SELECT * FROM (SELECT rownum num, s.* FROM  " 
			+ " (SELECT * FROM " + tableName + " ORDER BY " + orderBy + ") s) "
			+ " WHERE num>="  + beiginIndex + " and num<=" + endIndex;
		return executeQuery(connection, querySql);
	}
	
	/**
	 * 执行分页
	 */


}
