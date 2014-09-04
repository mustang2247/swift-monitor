package com.ganqiang.core.dao.meta.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ganqiang.core.dao.DAOException;
import com.ganqiang.core.dao.meta.Constraint;
import com.ganqiang.core.dao.meta.DataBase;
import com.ganqiang.core.dao.meta.DataSourceInfo;
import com.ganqiang.core.dao.meta.JDBCUtil;

/**
 * 适配器 适应多种数据库
 */
public abstract class DBAdaptor implements DataBase
{
  
  
  /**
   * 根据不用的数据库及其对应的版本，<br>
   * 查询其系统表，获取所有的用户表名称 <br>
   * 默认按照表名称排序 <br>
   */
  abstract String getSearchTableSql(DataSourceInfo ds);
  
  /**
   * 根据不用的数据库及其对应的版本，<br>
   * 查询其系统表，获取所有用户表下的所有字段元数据信息 <br>
   * 必须按照表名称排序，否则影响业务逻辑代码 <br>
   */
  abstract String getSearchColumnSql(DataSourceInfo ds);
  
  /**
   * 根据不用的数据库及其对应的版本，<br>
   * 查询其系统表，获取所有用户表之间的约束关系 <br>
   * 默认按照约束名称排序 <br>
   */
  abstract String getSearchConstraintSql(DataSourceInfo ds);
  
  
  /**
   * 获取各种数据源下的所有表名 <br>
   * 默认ResultSet取第一条记录就是表名称, <br>
   * 所以要求传入的SQL查询结果就是表名列表 <br>
   * 默认按表名排序 <br>
   * @throws DAOException 
   */
  List<String> getAllTableName(DataSourceInfo dataSourceInfo,String tableNameSql) throws DAOException{
    Connection con = JDBCUtil.newConnection(dataSourceInfo);
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<String> tableNameList = new ArrayList<String>();
    try {
      pstmt = con.prepareStatement(tableNameSql);
      rs=pstmt.executeQuery();
      while (rs.next()){   
        tableNameList.add(rs.getString(1));
      }
    } catch (SQLException e) {
      throw new DAOException("Cannot to excute find getAllTableName by sql : "+tableNameSql, e.getCause());
    } finally{
      JDBCUtil.free(rs, pstmt, con);
    }
    return tableNameList;
  }

  /**
   * 获取各种数据源下的所有约束 <br>
   * 默认ResultSet结果集的顺序为: 约束名称，表1，表1字段，表2，表2字段 <br>
   * 所以要求传入的SQL查询结果就是此顺序 <br>
   * 默认按表名排序 <br>
   */
  List<Constraint> getAllConstraint(DataSourceInfo dataSourceInfo,String constraintSql)  throws DAOException{
    Connection con = JDBCUtil.newConnection(dataSourceInfo);
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Constraint> constraintList = new ArrayList<Constraint>();
    try {
      pstmt = con.prepareStatement(constraintSql);
      rs=pstmt.executeQuery();
      while (rs.next()){   
        Constraint constraint = new Constraint();
        constraint.setName(rs.getString(1));
        constraint.setTableName(rs.getString(2));
        constraint.setColumnName(rs.getString(3));
        constraint.setRefTableName(rs.getString(4));
        constraint.setRefColumnName(rs.getString(5));
        constraintList.add(constraint);
      }
    } catch (SQLException e) {
      throw new DAOException("Cannot to excute find getAllConstraint by sql : "+constraintSql, e.getCause());
    } finally{
      JDBCUtil.free(rs, pstmt, con);
    }
    return constraintList;
  }
  
  /**
   * 通过JDBC获取字段元数据信息，但是有的元数据信息获取不到 (所以暂时不用此方法)
   */
//  public List<Table> getAllTable(DataSourceInfo ds,List<String> tableNameList){
//    if(null!=tableNameList && tableNameList.size()>0){
//      return null;
//    }
//    Connection con = JDBCUtil.newConnection(ds);
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//    List<Table> tableList = new ArrayList<Table>();
//    Table table = null;
//    try {
//      for(String tableName : tableNameList){
//        table = new Table();
//        String tableSql = "select * from "+tableName;
//        pstmt = con.prepareStatement(tableSql);
//        rs=pstmt.executeQuery();
//        ResultSetMetaData md = rs.getMetaData();   
//        int columnCount = md.getColumnCount(); 
//        List<Column> columnList = new ArrayList<Column>();
//        for (int i=1; i<=columnCount; i++){   
//          Column column = new Column();
//          column.setName(md.getColumnName(i));
//          column.setType(md.getColumnTypeName(i));
//          column.setPrecision(md.getPrecision(i));
//          column.setLength(md.getColumnDisplaySize(i));
//          column.setNecessary(0==md.isNullable(i)?1:0);
////          column.setIsPrimaryKey();
////          column.setIsForeignKey();
////          column.setDefaultValue();
////          column.setComment();
//          columnList.add(column);
//        }
//        table.setName(tableName);
//        table.setColumns(columnList);
//        tableList.add(table);      
//      } 
//    } catch (SQLException e) {
//      throw new DAOException("Cannot to excute find getAllTable ", e.getCause());
//    } finally{
//      JDBCUtil.free(rs, pstmt, con);
//    }
//    
//    return tableList;
//  
//    }
    
  
}
