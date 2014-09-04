package com.ganqiang.core.dao.meta.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ganqiang.core.dao.DAOException;
import com.ganqiang.core.dao.meta.Column;
import com.ganqiang.core.dao.meta.Constraint;
import com.ganqiang.core.dao.meta.DataSourceInfo;
import com.ganqiang.core.dao.meta.JDBCUtil;
import com.ganqiang.core.util.common.StringUtil;

public class Mysql extends DBAdaptor
{
  private DataSourceInfo dataSourceInfo;
  
  public Mysql(DataSourceInfo ds){
    this.dataSourceInfo = ds;
  }
  
  public DataSourceInfo getDataSourceInfo()
  {
    return dataSourceInfo;
  }


  public void setDataSourceInfo(DataSourceInfo dataSourceInfo)
  {
    this.dataSourceInfo = dataSourceInfo;
  }


  String getSearchTableSql(DataSourceInfo ds)
  {
    String sql = null;
    switch (ds.getDbVersion()) {
      case MYSQL_4:
        sql = "show tables";
      case MYSQL_5:
        sql = "show tables";
    } 
    return sql;    
  }
  
  
  String getSearchColumnSql(DataSourceInfo ds)
  {
    String sql = null;
    switch (ds.getDbVersion()) {
      case MYSQL_4:
        sql = "select * from Information_schema.columns where table_schema='"+ds.getInstanceName()+"' order by table_name";
      case MYSQL_5:
        sql = "select * from Information_schema.columns where table_schema='"+ds.getInstanceName()+"' order by table_name";
    } 
    return sql;    
  }
  

  String getSearchConstraintSql(DataSourceInfo ds)
  {
    String sql = null;
    switch (ds.getDbVersion()) {
      case MYSQL_4:
        sql = "select constraint_name,table_name,column_name,referenced_table_name,referenced_column_name " +
        		  "from Information_schema.key_column_usage where table_schema = '"+ds.getInstanceName()+"' " +
        		  "and constraint_name != 'PRIMARY' order by constraint_name";
      case MYSQL_5:
        sql = "select constraint_name,table_name,column_name,referenced_table_name,referenced_column_name " +
              "from Information_schema.key_column_usage where table_schema = '"+ds.getInstanceName()+"' " +
              "and constraint_name != 'PRIMARY' order by constraint_name";
    } 
    return sql; 
  }

  public List<String> getAllTableName()  throws DAOException
  {
    String tableNameSql = getSearchTableSql(dataSourceInfo);
    return super.getAllTableName(dataSourceInfo,tableNameSql);
  }

  public List<Constraint> getAllConstraint()
  {
    String constraintSql = getSearchConstraintSql(dataSourceInfo);
    return super.getAllConstraint(dataSourceInfo, constraintSql);
  }

  
  public List<Column> getAllColumn()
  {
    String columnSql = getSearchColumnSql(dataSourceInfo);
    List<Column> columnList = new ArrayList<Column>();
    Connection con = JDBCUtil.newConnection(dataSourceInfo);
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = con.prepareStatement(columnSql);
      rs=pstmt.executeQuery();
      while (rs.next()){   
        Column column = new Column();
        column.setTableName(rs.getString(3));
        column.setName(rs.getString(4));
        if(!StringUtil.isNullOrBlank(rs.getString(6))){
          if(200 >= rs.getString(6).length()){
            column.setDefaultValue(rs.getString(6));
          }else{
            column.setDefaultValue(rs.getString(6).substring(0,200));
          }
        }
        column.setNecessary("NO".equalsIgnoreCase(rs.getString(7))?"1":"0");
        column.setType(rs.getString(8));
        if(!StringUtil.isNullOrBlank(rs.getString(9))){
          column.setLength(rs.getInt(9));
        }else if(!StringUtil.isNullOrBlank(rs.getString(11))){
          column.setLength(rs.getInt(11));
        }
        if(!StringUtil.isNullOrBlank(rs.getString(12))){
          column.setPrecision(rs.getInt(12));
        }
        if(!StringUtil.isNullOrBlank(rs.getString(16))){
          if("PRI".equalsIgnoreCase(rs.getString(16))){
            column.setIsPrimaryKey("1");
          }else if("MUL".equalsIgnoreCase(rs.getString(16))){
            column.setIsForeignKey("1");
          }
        }
        column.setComment(rs.getString(19));
        columnList.add(column);
      }
    } catch (SQLException e) {
      throw new DAOException("Cannot to excute Mysql find getAllColumn by columnSql : "+columnSql, e.getCause());
    } finally{
      JDBCUtil.free(rs, pstmt, con);
    }
    return columnList;
  }

}
