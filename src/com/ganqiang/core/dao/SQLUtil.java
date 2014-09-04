package com.ganqiang.core.dao;


import com.ganqiang.core.util.common.StringUtil;

public class SQLUtil
{
	/**
	 * 拼装分页SQL
	 * @param sql
	 * @param limit
	 * @param offset
	 * @return
	 */
	public static String buildPageSql(String sql,int limit,int offset){
		if(StringUtil.isNullOrBlank(sql)){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(sql);
		sb.append(" LIMIT ");
		sb.append(limit);
		sb.append(" OFFSET ");
		sb.append(offset);
		return sb.toString();
	}
	 
	/**
	 * 根据查询sql构建"select count(*)"的sql
	 * @param sql
	 * @return
	 */
	public static String buildSelectCountSql(String sql){
		if(StringUtil.isNullOrBlank(sql)){
			return "";
		}
		String[] splits = sql.split("(?i) from ");
		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) from ");
		sb.append(splits[1]);
		return sb.toString();
		
	}
	
	public static void main(String[] args){
		String str="select sdfasdfs,sdfsadf from aaa,bbb a where adsfasdfasdfas=1 and awerwer=1 order by asdfasdf";
		String sss=buildSelectCountSql(str);
		System.out.println(sss);
		
	}
	
}
