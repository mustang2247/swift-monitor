package com.ganqiang.core.util.common;

public class StringUtil
{

	public static boolean isNullOrBlank(String str)
	{
		if (null == str || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
}

