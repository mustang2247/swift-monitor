<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 

<head>
	<meta charset="utf-8"/>
	<title>Dashboard I Admin Panel</title>
	
	<link rel="stylesheet" href="css/layout.css" type="text/css" media="screen" />
	<!--[if lt IE 9]>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen" />
	<![endif]-->
	<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="js/hideshow.js" type="text/javascript"></script>
	<script src="js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/jquery.equalHeight.js"></script>
	<script type="text/javascript"  src="js/jquery-ui.min.js"></script>
	<style>
    body { font-size: 62.5%; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }


  </style>

	<script type="text/javascript">
	$(document).ready(function() 
    	{ 
      	  $(".tablesorter").tablesorter(); 
   	 } 
	);
	$(document).ready(function() {

	//When page loads...
	$(".tab_content").hide(); //Hide all content
	$("ul.tabs li:first").addClass("active").show(); //Activate first tab
	$(".tab_content:first").show(); //Show first tab content

	//On Click Event
	$("ul.tabs li").click(function() {

		$("ul.tabs li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".tab_content").hide(); //Hide all tab content

		var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active ID content
		return false;
	});

});
    </script>
    <script type="text/javascript">
    $(function(){
        $('.column').equalHeight();
    });
    
    
  function fmt(){
     if(document.addform.nodeid.value==""){
     	    alert("爬虫节点不能为空值！");
     	    return false;
              }
     if(document.addform.starttime.value==""){
     	    alert("开始抓取时间不能为空值！");
     	    return false;
              }
     if(document.addform.interval.value==""){
     	    alert("抓取间隔不能为空值！");
     	    return false;
              }

    				document.addform.action="list?method=editconf";
    				document.addform.submit();
    				window.opener.location.href="list?method=query";
    				window.close();
    }
</script>

			
			
<form id="addform" name="addform" method="post">
<input type="hidden" name="id" value="${conf.ID}">
  <div class="tab_container">
			<table align="center" cellspacing="0" width=80%> 
				<tr>
				   <td height=50>爬虫节点</td>
				   <td height=50>
				   <select id="nodeid" name="nodeid">
				      <c:forEach var="node" items="${nodemap}">
					       	<option value="${node.ID}" <c:if test="${node.ID==conf.NODE_ID}">checked</c:if> >${node.IP}</option>
					    	</c:forEach>
						</select>
				   </td>

				   <td height=50>开始抓取时间</td>
				   <td height=50><input type="text" name="starttime" maxlength=20 value="${conf.START_TIME}"></td>
				</tr> 
				<tr>
				   <td height=50>抓取间隔</td>
				   <td height=50><input type="text" name="interval" maxlength=20 value="${conf.INTERVAL_TIME}">秒</td>
				</tr> 
				<c:if test="${fn:length(proxymap) > 0}">
					<tr width=500>
					   <td height=50>Http代理</td>
					   <td height=50 colspan=13 >
					     <c:forEach var="proxy" items="${proxymap}">
					          <input name="proxy" type="checkbox"  value="${proxy.ID}" <c:if test="${proxy.flag==true}">checked</c:if>/>${proxy.IP}:${proxy.PORT}
					   	 </c:forEach>
					   </td>
					</tr> 
				</c:if>
				<c:if test="${fn:length(inseedmap) > 0}">
					<tr width=500>
					   <td height=50>内置种子</td>
					   <td height=50 colspan=13 >
					     <c:forEach var="seed" items="${inseedmap}">
					         <input name="inseed" type="checkbox" value="${seed.ID}" <c:if test="${seed.flag==true}">checked</c:if>/>${seed.INSIDE_NAME}
					   	 </c:forEach>
					   </td>
					</tr> 
				</c:if>
				<c:if test="${fn:length(outseedmap) > 0}">
					<tr width=500>
					   <td height=50>外置种子</td>
					   <td height=50 colspan=13 >
					     <c:forEach var="seed" items="${outseedmap}">
					         <input name="outseed" type="checkbox" value="${seed.ID}" <c:if test="${seed.flag==true}">checked</c:if>/>${seed.INSIDE_NAME}
					   	 </c:forEach>
					   </td>
					</tr> 
				</c:if>
					<tr>
				   <td height=50>内置种子抓取延迟</td>
				   <td height=50><input type="text" name="indelay" maxlength=10 value="${conf.IN_DELAY}"></td>
				   <td height=50>外置种子抓取延迟</td>
				   <td height=50><input type="text" name="outdelay" maxlength=10 value="${conf.OUT_DELAY}"></td>
				</tr>
				<tr>
				   <td height=50>内置种子是否支持下载</td>
				   <td height=50><input type="radio" name="inisdownload" value="1" <c:if test="${conf.IN_IS_DOWNLOAD==1}">checked</c:if>>是 
				   <input type="radio" name="inisdownload" value="0" <c:if test="${conf.IN_IS_DOWNLOAD==0}">checked</c:if>>否</td>
				   <td height=50>外置种子是否支持下载</td>
				   <td height=50><input type="radio" name="outisdownload" value="1" <c:if test="${conf.OUT_IS_DOWNLOAD==1}">checked</c:if>>是 
				   <input type="radio" name="outisdownload" value="0" <c:if test="${conf.OUT_IS_DOWNLOAD==0}">checked</c:if>>否</td>
				</tr>
				<tr>
				   <td height=50>内置种子是否支持JS</td>
				   <td height=50><input type="radio" name="jssupport" value="1" <c:if test="${conf.JS_SUPPORT==1}">checked</c:if>>是 
				   <input type="radio" name="jssupport" value="0" <c:if test="${conf.JS_SUPPORT==0}">checked</c:if>>否</td>
				   <td height=50>外置种子是否支持级联</td>
				   <td height=50><input type="radio" name="iscascade" value="1" <c:if test="${conf.IS_CASCADE==1}">checked</c:if>>是 
				   <input type="radio" name="iscascade" value="0" <c:if test="${conf.IS_CASCADE==0}">checked</c:if>>否</td>
				</tr>
				<tr>
				   <td height=50>外置种子抓取深度</td>
				   <td height=50><input type="text" name="depth" maxlength=10 value="${conf.OUT_DEPTH}"></td>
				</tr>
				
       <tr>
				   <td ><input type="button" value="确定"   onclick="fmt()"></td>&nbsp;&nbsp;
				   <td ><input type="button" value="关闭"  onclick="window.close();"></td>
				</tr> 

			</table>
			</div><!-- end of #tab1 -->
</form>			



