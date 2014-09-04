package com.ganqiang.core.dao;

import java.util.List;
import java.util.Map;

import com.ganqiang.core.dao.support.Page;

/**
 * 公共DAO接口 <br>
 * 避免写JDBC代码 <br>
 */
public interface IGenericDAO {

	/**
	 * 根据查询SQL返回LIST结果集  <br>
	 * LIST中包含多个MAP<key,value> <br>
	 * 每个MAP代表一条数据记录  <br>
	 * key : 字段名称  <br>
	 * value : 字段值  <br>
	 * @param <T>
	 * @param sql
	 * @return
	 */
	<T> List<T> find(final String sql);
	
	/**
	 * 只返回一条MAP类型的数据记录 <br>
	 * 如果数据库中含有多条，则默认将最后一条记录返回 <br>
	 * @param sql
	 * @return
	 */
	Map<String,Object> findOne(final String sql);
	
	/**
	 * 根据查询SQL:<code>select count(*) from table_name</code> <br>
	 * 返回count值 <br>
	 * @param sql
	 * @return
	 */
	int findCount(final String sql);
	
	/**
	 * 根据参数返回一个分页对象以用于前台展示 <br>
	 * @param sql
	 * @param totalCount 总记录数
	 * @param pageNo 当前页数
	 * @param pageSize 每页记录数
	 * @return
	 */
	<T> Page findPage(String sql,int totalCount,int pageNo, int pageSize);
	
	/**
	 * 插入操作
	 * @param sql
	 * @return
	 */
	int insert(final String sql);
	
	/**
	 * 更新操作
	 * @param sql
	 * @return
	 */
	int update(final String sql);
	
	/**
	 * 删除操作
	 * @param sql
	 * @return
	 */
	int delete(final String sql);
	
}