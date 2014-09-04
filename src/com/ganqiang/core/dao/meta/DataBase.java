package com.ganqiang.core.dao.meta;

import java.util.List;

import com.ganqiang.core.dao.DAOException;

/**
 * 数据库
 */
public interface DataBase
{
  
  /**
   * 获取该版本的数据库下的所有用户表
   */
//  List<Table> getAllTable();
  
  
  /**
   * 获取该数据源下的所有表名 <br>
   */
  List<String> getAllTableName() throws DAOException;
  
  /**
   * 获取所有用户表所对应的字段列表 <br>
   */
  List<Column> getAllColumn() throws DAOException;
  
  /**
   * 获取所有用户表所对应的约束条件 <br>
   */
  List<Constraint> getAllConstraint() throws DAOException;

}
