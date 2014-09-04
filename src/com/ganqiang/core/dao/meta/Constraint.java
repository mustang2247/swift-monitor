package com.ganqiang.core.dao.meta;

/**
 * 主外键约束
 */
public class Constraint
{
  /**
   * 关联名称
   */
  private String name;
  /**
   * 表1
   */
  private String tableName;
  
  /**
   * 表1字段1
   */
  private String columnName;
  
  /**
   * 表2
   */
  private String refTableName;
  
  /**
   * 表2字段2
   */
  private String refColumnName;


  public String getTableName()
  {
    return tableName;
  }

  public void setTableName(String tableName)
  {
    this.tableName = tableName;
  }

  public String getColumnName()
  {
    return columnName;
  }

  public void setColumnName(String columnName)
  {
    this.columnName = columnName;
  }

  public String getRefTableName()
  {
    return refTableName;
  }

  public void setRefTableName(String refTableName)
  {
    this.refTableName = refTableName;
  }

  public String getRefColumnName()
  {
    return refColumnName;
  }

  public void setRefColumnName(String refColumnName)
  {
    this.refColumnName = refColumnName;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
  
}
