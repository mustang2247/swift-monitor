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
     if(document.addform.inseed.value=="" && document.addform.outseed.value==""){
     	    alert("内置种子或外置种子不能同时为空值！");
     	    return false;
              }
    				document.addform.action="list?method=addconf";
    				document.addform.submit();
    				window.returnValue="true";
    				window.close();
    }
</script>

			
			
<form id="addform" name="addform" method="post">
  <div class="tab_container">
			<table align="center" cellspacing="0" width=80%> 
				<tr>
				   <td height=50>爬虫线程数</td>
				   <td height=50>${remotep.threadnum}</td>
				 </tr> 
				<tr> 
				   <td height=50>爬虫下载磁盘</td>
				   <td height=50>${remotep.disk}</td>
				</tr> 
				<tr>
				   <td height=50>爬虫文件同步域名</td>
				   <td height=50>${remotep.disksync}</td>
				 </tr> 
				<tr> 
				   <td height=50>爬虫索引路径</td>
				   <td height=50>${remotep.index}</td>
				</tr> 
					<tr>
				   <td height=50>爬虫数据库Driver</td>
				   <td height=50>${remotep.dbdriver}</td>
			 </tr> 
				<tr> 
				   <td height=50>爬虫数据库Url</td>
				   <td height=50>${remotep.dburl}</td>
				</tr> 
					<tr>
				   <td height=50>爬虫数据库用户名</td>
				   <td height=50>${remotep.dbuser}</td>
				 </tr> 
				<tr> 
				   <td height=50>爬虫数据库密码</td>
				   <td height=50>${remotep.dbpwd}</td>
				</tr> 
					<tr>
				   <td height=50>爬虫数据库池大小</td>
				   <td height=50>${remotep.dbpoolsize}</td>
				</tr> 
				<tr>
				   <td ><input type="button" value="关闭"  onclick="window.close();"></td>
				</tr> 
			</table>
			</div><!-- end of #tab1 -->
</form>			



