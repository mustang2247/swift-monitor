<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>
<% String path = request.getContextPath() + "/list?method=query";
   response.sendRedirect(path); %>

