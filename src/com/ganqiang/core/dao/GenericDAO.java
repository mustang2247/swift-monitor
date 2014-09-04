package com.ganqiang.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ganqiang.core.dao.support.Page;


@SuppressWarnings("unchecked")
public class GenericDAO implements IGenericDAO
{

	private static final Logger logger = Logger.getLogger(GenericDAO.class);
	
	public <T> List<T> find(final String sql)
	{
		Map<String,Object> rowData = null;
		List<T> list = new ArrayList<T>();
		Connection con = ConnectionManager.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ResultSetMetaData md = rs.getMetaData();   
      int columnCount = md.getColumnCount();   
      while (rs.next()){   
          rowData = new HashMap<String,Object>(columnCount);   
          for (int i=1; i<=columnCount; i++){   
            rowData.put(md.getColumnLabel(i).toUpperCase(),rs.getObject(i));   
          }
          list.add((T) rowData);   
      }
		} catch (SQLException e) {
			logger.error("Cannot to excute find method by sql : "+sql, e.getCause());
			throw new DAOException("Cannot to excute find method by sql : "+sql, e.getCause());
		} finally{
			ConnectionManager.close(rs, pstmt);
		}
		return list;
	}

	public Map<String,Object> findOne(final String sql)
	{
		Map<String,Object> rowData = null;
		Connection con = ConnectionManager.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql); 
			rs=pstmt.executeQuery();
			ResultSetMetaData md = rs.getMetaData();   
      int columnCount = md.getColumnCount();
      while (rs.next()){  
      	rowData = new HashMap<String,Object>(columnCount);   
        for (int i=1; i<=columnCount; i++){   
          rowData.put(md.getColumnLabel(i).toUpperCase(),rs.getObject(i));   
        }
      }
		} catch (SQLException e) {
			logger.error("Cannot to excute findOne method by sql : "+sql, e.getCause());
			throw new DAOException("Cannot to excute findOne method by sql : "+sql, e.getCause());
		} finally{
			ConnectionManager.close(rs, pstmt);
		}
		return rowData;
	}
	
	public int findCount(final String sql)
	{
		int count = 0;
		Connection con = ConnectionManager.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql); 
			rs=pstmt.executeQuery();
      while (rs.next()){  
      	count=rs.getInt(1); 
      }
		} catch (SQLException e) {
			logger.error("Cannot to excute findCount method by sql : "+sql, e.getCause());
			throw new DAOException("Cannot to excute findCount method by sql : "+sql, e.getCause());
		} finally{
			ConnectionManager.close(rs, pstmt);
		}
		return count;
	}
	
	public <T> Page findPage(String sql,int totalCount,int pageNo, int pageSize){
		Map<String,Object> rowData = null;
		List<T> list = new ArrayList<T>();
		Connection con = ConnectionManager.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Page page = null;
		try {
			int offset = Page.getOffsetOfPage(pageNo, pageSize);
//			int totalCount = findCount(SQLUtil.buildSelectCountSql(sql));
			String pageSql = SQLUtil.buildPageSql(sql,pageSize,offset);
			pstmt = con.prepareStatement(pageSql);
			rs=pstmt.executeQuery();
			ResultSetMetaData md = rs.getMetaData(); 
      int columnCount = md.getColumnCount();   
      while (rs.next()){   
          rowData = new HashMap<String,Object>(columnCount);   
          for (int i=1; i<=columnCount; i++){   
            
            rowData.put(md.getColumnLabel(i).toUpperCase(),rs.getObject(i));   
          }
          list.add((T) rowData);   
      }
      page = new Page(offset,totalCount,pageSize,list);
		} catch (SQLException e) {
			logger.error("Cannot to excute findPage method by sql : "+sql, e.getCause());
			throw new DAOException("Cannot to excute findPage method by sql : "+sql, e.getCause());
		} finally{
			ConnectionManager.close(rs, pstmt);
		}
		return page;
	}
	
	
	public int insert(final String sql)
	{
		int count = 0;
		Connection con = ConnectionManager.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Transcation.openTransaction(con);
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			Transcation.commit(con);
		} catch (SQLException e) {
			Transcation.rollback(con);
			logger.error("Cannot to excute insert method by sql : "+sql, e.getCause());
			throw new DAOException("Cannot to excute insert method by sql : "+sql, e.getCause());
		} finally{
			ConnectionManager.close(rs, pstmt);
		}
		return count;
	}
	
	public int update(final String sql)
	{
		int count = 0;
		Connection con = ConnectionManager.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Transcation.openTransaction(con);
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			Transcation.commit(con);
		} catch (SQLException e) {
			Transcation.rollback(con);
			logger.error("Cannot to excute update method by sql : "+sql, e.getCause());
			throw new DAOException("Cannot to excute update method by sql : "+sql, e.getCause());
		} finally{
			ConnectionManager.close(rs, pstmt);
		}
		return count;
	}
	
	public int delete(final String sql)
	{
		int count = 0;
		Connection con = ConnectionManager.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Transcation.openTransaction(con);
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			Transcation.commit(con);
		} catch (SQLException e) {
			Transcation.rollback(con);
			logger.error("Cannot to excute delete method by sql : "+sql, e.getCause());
			throw new DAOException("Cannot to excute delete method by sql : "+sql, e.getCause());
		} finally{
			ConnectionManager.close(rs, pstmt);
		}
		return count;
	}
	
}