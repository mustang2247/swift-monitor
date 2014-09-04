package com.ganqiang.core.dao;

public class DAOFactory {
	
	private GenericDAO genericDAO = null;//公共的dao类
  
  private DAOFactory(){}  
  
  static class DAOFactoryHolder {    
    static DAOFactory instance = new DAOFactory();    
  }    
   
  public static DAOFactory getInstance() { 
    return DAOFactoryHolder.instance;    
  }    
  
  /**
   * 构建公用dao
   * @return
   */
	public GenericDAO buildGenericDAO(){
		if(null == genericDAO){
  		genericDAO = new GenericDAO();
  	}
		return genericDAO;
  }

}
