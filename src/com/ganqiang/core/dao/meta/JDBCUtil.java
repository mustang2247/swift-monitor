package com.ganqiang.core.dao.meta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ganqiang.core.dao.DAOException;

public class JDBCUtil
{

  public static Connection newConnection(DataSourceInfo ds)  throws DAOException {
    Connection con = null;
    try {
      // 加载数据库驱动程序
      Class.forName(ds.getDriverClass());
      con = DriverManager.getConnection(ds.getUrl(ds.getDb()),ds.getUserName(), ds.getPassword());
    } catch (Exception e) {
      throw new DAOException("Cannot to new DB connection.", e.getCause());
    }
    return con;
  }
  
  public static void free(ResultSet rs,PreparedStatement psmt,Connection con){
    try {
      if (rs != null) {
        rs.close();
        rs = null;
      }
      if (psmt != null) {
        psmt.close();
        psmt = null;
      }
      if (con != null) {
        con.close();
        con = null;
      }
    } catch (SQLException e) {
      throw new DAOException("Cannot to free DB .", e.getCause());
    }
  }
  
}
