package com.ganqiang.core.dao.meta;

import java.util.List;

/**
 * 字段
 */
public class Column
{
  //主键，供业务逻辑调用，跟抽取没关系，抽取时此值设为空
  private int id;
  //表名称
  private String tableName;
  //字段名称
  private String name;
  //字段类型
  private String type;
  //字段长度 (可能为null值)
  private Integer length;
  //字段精度:包含小数点的数字类型
  //当字段类型为字符类型时，此值为空 (可能为null值)
  private Integer precision;
  //是否为必填
  private String necessary="0";
  //是否为主键
  private String isPrimaryKey="0";
  //是否为外键
  private String isForeignKey="0";
  //缺省值 (可能为null值)
  private String defaultValue;
  //注释 (可能为null值)
  private String comment;
  //是否已经建立约束,默认为未建立 (暂时无用)
  private boolean isRef = false;  
  //该字段对应的所有的约束关系 (暂时无用)
  private List<Constraint> constraintList;
  

 /***
  * 是否需要更新 <br>
  * 此方法用于业务逻辑调用判断，跟抽取本身没有任何关系
  */
  public boolean isUpdate(Column column){
    boolean flag = false;
    if(null == column){
      return flag;
    }
    if(!(this.getType() == null ? column.getType() == null : this.getType().equals(column.getType()))){
      flag = true;
    }
    if(!(this.getLength() == null ? column.getLength() == null : this.getLength() != column.getLength())){
      flag = true;
    }
    if(!(this.getPrecision() == null ? column.getPrecision() == null : this.getPrecision() != column.getPrecision())){
      flag = true;
    }
    if(!(this.getNecessary() == null ? column.getNecessary() == null : this.getNecessary().equals(column.getNecessary()))){
      flag = true;
    }
    if(!(this.getIsPrimaryKey() == null ? column.getIsPrimaryKey() == null : this.getIsPrimaryKey().equals(column.getIsPrimaryKey()))){
      flag = true;
    }
    if(!(this.getIsForeignKey() == null ? column.getIsForeignKey() == null : this.getIsForeignKey().equals(column.getIsForeignKey()))){
      flag = true;
    }
    if(!(this.getDefaultValue() == null ? column.getDefaultValue() == null : this.getDefaultValue().equals(column.getDefaultValue()))){
      flag = true;
    }
    if(!(this.getComment() == null ? column.getComment() == null : this.getComment().equals(column.getComment()))){
      flag = true;
    }
    return flag;
  }
   
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public Integer getLength()
  {
    return length;
  }

  public void setLength(Integer length)
  {
    this.length = length;
  }

  public Integer getPrecision()
  {
    return precision;
  }

  public void setPrecision(Integer precision)
  {
    this.precision = precision;
  }

  public String getNecessary()
  {
    return necessary;
  }

  public void setNecessary(String necessary)
  {
    this.necessary = necessary;
  }

  public String getIsPrimaryKey()
  {
    return isPrimaryKey;
  }

  public void setIsPrimaryKey(String isPrimaryKey)
  {
    this.isPrimaryKey = isPrimaryKey;
  }

  public String getIsForeignKey()
  {
    return isForeignKey;
  }

  public void setIsForeignKey(String isForeignKey)
  {
    this.isForeignKey = isForeignKey;
  }

  public String getDefaultValue()
  {
    return defaultValue;
  }
  public void setDefaultValue(String defaultValue)
  {
    this.defaultValue = defaultValue;
  }
  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public List<Constraint> getConstraintList()
  {
    return constraintList;
  }

  public void setConstraintList(List<Constraint> constraintList)
  {
    this.constraintList = constraintList;
  }

  public boolean isRef()
  {
    return isRef;
  }

  public void setRef(boolean isRef)
  {
    this.isRef = isRef;
  }

  public String getTableName()
  {
    return tableName;
  }

  public void setTableName(String tableName)
  {
    this.tableName = tableName;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

}
