package com.ganqiang.core.dao.meta;

import com.ganqiang.core.dao.meta.type.DB;
import com.ganqiang.core.dao.meta.type.DBVersion;
import com.ganqiang.core.util.common.StringUtil;

/**
 * 数据源信息
 */
public class DataSourceInfo
{ 
 
  private String instanceName;
  
  private String driverClass;
  
  private String address;
  
  private String port;
  
  private String userName;
  
  private String password;
  
  private DB db;
  
  public DBVersion dbVersion;
  
  public DataSourceInfo(){
    
  }
  
  /**
   * 根据数据库名称查找相应的数据库类型 <br>
   * 供业务层调用 <br>
   * 注意：此参数name需要跟数据库中的name一致才可以 <br>
   */
  public static DB findDB(String name)
  {
    if(StringUtil.isNullOrBlank(name)){
      throw new IllegalArgumentException("invalid DBName code");
    }
    String lowerName = name.toLowerCase();
    if(lowerName.contains("oracle")){
      return DB.ORACLE;
    }else if(lowerName.contains("mysql")){
      return DB.MYSQL;
    }else if(lowerName.contains("sqlserver") || lowerName.contains("sql server")){
      return DB.SQLSERVER;
    }else if(lowerName.contains("postgresql")){
      return DB.POSTGRESQL;
    }
    throw new IllegalArgumentException("invalid DBName code");
  }
  
  /**
   * 根据数据库名称，数据库版本查找相应的版本类型 <br>
   * 供业务层调用 <br>
   * 目前version参数暂时无用，为了以后需求需要支持所有的数据库版本 <br>
   */
  public static DBVersion findDBVersion(String name,String version)
  {
    DB db = findDB(name);
    String firstNum = name.substring(0,1);
    switch(db){
      case ORACLE:
        if("8".equals(firstNum)){
          return DBVersion.ORACLE_8I;
        }else if("9".equals(firstNum)){
          return DBVersion.ORACLE_9I;
        }
        return DBVersion.ORACLE_10G;
      case MYSQL:
        if("4".equals(firstNum)){
          return DBVersion.MYSQL_4;
        }
        return DBVersion.MYSQL_5;
      case SQLSERVER:
        if("9".equals(firstNum)){
          return DBVersion.SQLSERVER_2005;
        }else if("1".equals(firstNum)){
          return DBVersion.SQLSERVER_2008;
        }
        return DBVersion.SQLSERVER_2000;
      case POSTGRESQL:
        if("7".equals(firstNum)){
          return DBVersion.POSTGRESQL_7;
        }else if("8".equals(firstNum)){
          return DBVersion.POSTGRESQL_8;
        }
        return DBVersion.POSTGRESQL_9;
    }
    throw new IllegalArgumentException("invalid DBVersion code");
  }
  
  /**
   * 根据数据库名称查找相应的JDBC驱动 <br>
   * 此方法的映射跟本地加载的JDBC JAR包相关 <br>
   * 供业务层调用 <br>
   */
  public static String findDriverClass(String name)
  {
    DB db = findDB(name);
    switch(db){
      case ORACLE:
        return "oracle.jdbc.driver.OracleDriver";
      case MYSQL:
        return "com.mysql.jdbc.Driver";
      case SQLSERVER:
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
      case POSTGRESQL:
        return "org.postgresql.Driver";
    }  
    throw new IllegalArgumentException("invalid DBName code");
  }

  public String getUrl(DB db)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("jdbc:");
    switch(db){
      case ORACLE:
        sb.append("oracle:thin:@");
        break;
      case MYSQL:
        sb.append("mysql://");  
        break;
      case SQLSERVER:
        sb.append("sqlserver://");
        break;
      case POSTGRESQL:
        sb.append("postgresql://");
        break;
    } 
    sb.append(this.address);
    sb.append(":");
    sb.append(this.port);
    if(DB.SQLSERVER.equals(db)){
      sb.append(";DatabaseName=");
    }else{
      sb.append("/");
    }
    sb.append(this.instanceName);
    return sb.toString();
  }
  
  public String getDriverClass()
  {
    return driverClass;
  }

  public void setDriverClass(String driverClass)
  {
    this.driverClass = driverClass;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public DBVersion getDbVersion()
  {
    return dbVersion;
  }

  public void setDbVersion(DBVersion dbVersion)
  {
    this.dbVersion = dbVersion;
  }

  public String getInstanceName()
  {
    return instanceName;
  }

  public void setInstanceName(String instanceName)
  {
    this.instanceName = instanceName;
  }

  public DB getDb()
  {
    return db;
  }

  public void setDb(DB db)
  {
    this.db = db;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public String getPort()
  {
    return port;
  }

  public void setPort(String port)
  {
    this.port = port;
  }
  
  
}
