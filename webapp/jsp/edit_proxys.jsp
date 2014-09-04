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
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    div#users-contain { width: 350px; margin: 20px 0; }
    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
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
    
     if(document.addform.ip.value==""){
     	    alert("代理IP不能为空值！");
     	    return false;
              }
     if(document.addform.port.value==""){
     	    alert("Port不能为空值！");
     	    return false;
              }
    				document.addform.action="list?method=editproxys";
    				document.addform.submit();
    				window.returnValue="true";
    				window.close();
    }
</script>

			
			
<form id="addform" name="addform" method="post">
<input type="hidden" name="id" id="id" value="${proxy.ID}">
<div class="tab_container">
			<table align="center" cellspacing="0"> 
				<tr>
				   <td height=50>代理IP</td>
				   <td height=50><input type="text" name="ip" value="${proxy.IP}" maxlength=20></td>
				</tr> 
				<tr>
				   <td height=50>Port</td>
				   <td height=50><input type="text" name="port" value="${proxy.PORT}"  maxlength=10></td>
				</tr> 
					<tr>
				   <td height=50>用户名</td>
				   <td height=50><input type="text" name="username" value="${proxy.USERNAME}"  maxlength=10></td>
				</tr> 
					<tr>
				   <td height=50>密码</td>
				   <td height=50><input type="text" name="password" value="${proxy.PASSWORD}"  maxlength=10></td>
				</tr> 
       <tr>
				   <td ><input type="button" value="确定"   onclick="fmt()"></td>
				   <td ><input type="button" value="关闭"  onclick="window.close();"></td>
				</tr> 

			</table>
			</div><!-- end of #tab1 -->
</form>			



