package com.ganqiang.core.dao.meta;

import com.ganqiang.core.dao.DAOException;
import com.ganqiang.core.dao.meta.type.Mysql;
import com.ganqiang.core.dao.meta.type.Oracle;
import com.ganqiang.core.dao.meta.type.PostgreSql;
import com.ganqiang.core.dao.meta.type.SqlServer;

public class DataBaseFactory
{
  
  /**
   * 根据数据源信息获取相应的数据库
   */
  public static DataBase getDatabase(DataSourceInfo ds){
    if(null == ds){
      throw new DAOException("DataSourceInfo is null");
    }
    if(null == ds.getDriverClass()){
      throw new DAOException("DriverClass is null");
    }
    if(null == ds.getAddress()){
      throw new DAOException("Address is null");
    }
    if(null == ds.getPort()){
      throw new DAOException("Port is null");
    }
    if(null == ds.getUserName()){
      throw new DAOException("UserName is null");
    }
    if(null == ds.getPassword()){
      throw new DAOException("Password is null");
    }
    if(null == ds.getInstanceName()){
      throw new DAOException("InstanceName is null");
    }
    if(null == ds.getDbVersion()){
      throw new DAOException("DBVersion is null");
    }
    switch(ds.getDb()){
      case ORACLE:
        return new Oracle(ds);
      case MYSQL:
        return new Mysql(ds);
      case SQLSERVER:
        return new SqlServer(ds);
      case POSTGRESQL:
        return new PostgreSql(ds);
    }
    return null;
  }
  
  
}
