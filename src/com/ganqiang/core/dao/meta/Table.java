package com.ganqiang.core.dao.meta;

import java.util.List;

/**
 * 表
 */
public class Table
{
  
  private String name;
  
  private List<Column> columns;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public List<Column> getColumns()
  {
    return columns;
  }

  public void setColumns(List<Column> columns)
  {
    this.columns = columns;
  }
  
  
}
