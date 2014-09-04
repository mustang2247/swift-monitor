package com.ganqiang.core.util.conf;

import org.apache.log4j.PropertyConfigurator;


/**
 * 
 * <p>初始化配置文件加载器</p>
 * 此类还可扩展为自定义系统常量类
 * 
 * @author gan.qiang
 * @version1.0
 */
public class Constants extends ConfigurableConstants {

  /**
   * 将初始化配置文件Load到内存中
   */
  static {
    init("/jdbc.properties");
    init("/log4j.properties");
    PropertyConfigurator.configure(p);
  }
  
  /** jdbc.driverClassName */
  public final static String JDBC_DRIVER_CLASS_NAME = getProperty("jdbc.driverClassName","com.mysql.jdbc.Driver");
  
  /** jdbc.url */
  public final static String JDBC_URL = getProperty("jdbc.url","jdbc:mysql://localhost:3306/datasource_ms?useUnicode=true&characterEncoding=GBK");

  /** jdbc.username */
  public final static String JDBC_USERNAME = getProperty("jdbc.username","root");
  
  /** jdbc.password */
  public final static String JDBC_PASSWORD = getProperty("jdbc.password","root");
  
  
  /** 系统统一采用的默认编码 */
  public final static String SYS_CHARCTER = "UTF-8";
  
}
