package com.ganqiang.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ganqiang.core.util.conf.Constants;

public class ConnectionManager
{
	private static final Logger logger = Logger.getLogger(ConnectionManager.class);

	static class ConnectionHolder
	{
		static Connection instance = createConnection();
	}

	public static Connection getInstance() {
		return ConnectionHolder.instance;
	}
	
	static {
	  init();
	}

	private ConnectionManager()
	{
	}

	/**
	 * 所有初始化操作 <br>
	 */
	public static void init() {
		getConnection();
	}

	/**
	 * 释放ResultSet、PreparedStatement操作 <br>
	 */
	public static void close(ResultSet rs,PreparedStatement psmt) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			logger.error("Cannot to close DB resultset.", e.getCause());
			throw new DAOException("Cannot to close DB resultset.", e.getCause());
		}finally{
			try {
				if (psmt != null) {
					psmt.close();
					psmt = null;
				}
			} catch (SQLException e) {
				logger.error("Cannot to close DB prepared statment.", e.getCause());
				throw new DAOException("Cannot to close DB prepared statment.", e.getCause());
			}
		}
	}

	private static Connection createConnection() {
		Connection con = null;
		try {
			// 加载数据库驱动程序
			Class.forName(Constants.JDBC_DRIVER_CLASS_NAME);
			con = DriverManager.getConnection(Constants.JDBC_URL,
					Constants.JDBC_USERNAME, Constants.JDBC_PASSWORD);
		} catch (Exception e) {e.printStackTrace();
			throw new DAOException("Cannot to create DB connection.", e.getCause());
		}
		return con;
	}

	private static Connection getConnection() {
		Connection con = ConnectionManager.getInstance();
		try {
			if (null == con || con.isClosed()) {
				return createConnection();
			}
		} catch (SQLException e) {
			logger.error("Cannot get DB connection.", e.getCause());
			throw new DAOException("Cannot to get DB connection.", e.getCause());
		}
		return con;
	}

	public static void closeConnection() {
		Connection con = getConnection();
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			logger.error("Cannot to close DB connection.", e.getCause());
			throw new DAOException("Cannot to close DB connection.", e.getCause());
		}
	}
	
}
