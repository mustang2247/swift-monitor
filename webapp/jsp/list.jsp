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
	<script src="js/highcharts.js" type="text/javascript"></script>
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

// Add the background image to the container
Highcharts.wrap(Highcharts.Chart.prototype, 'getContainer', function (proceed) {
	proceed.call(this);
	this.container.style.background = 'url(images/sand.png)';
});


Highcharts.theme = {
	colors: ["#f45b5b", "#8085e9", "#8d4654", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
		"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
	chart: {
		backgroundColor: null,
		style: {
			fontFamily: "Signika, serif"
		}
	},

	// General
	background2: '#E0E0E8'
	
};

// Apply the theme
Highcharts.setOptions(Highcharts.theme);
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
    
    
function addnodes(){
		window.open("list?method=preaddnodes",'newwindow','height=350,width=500,top='+(screen.height-500)/2+',left='+(screen.width-800)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}

function addseeds(){
		window.open("list?method=preaddseeds",'newwindow','height=350,width=500,top='+(screen.height-500)/2+',left='+(screen.width-800)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}

function addproxys(){
	window.open("list?method=preaddproxys",'newwindow','height=350,width=500,top='+(screen.height-500)/2+',left='+(screen.width-800)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}

function addconf(){
	 window.open("list?method=preaddconf",'newwindow','height=550,width=800,top='+(screen.height-500)/2+',left='+(screen.width-800)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}

function editnodes(id){
		window.open("list?method=preeditnodes&id="+id,'newwindow','height=350,width=500,top='+(screen.height-500)/2+',left='+(screen.width-800)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}
function editseeds(id){
		 window.open("list?method=preeditseeds&id="+id,'newwindow','height=350,width=500,top='+(screen.height-500)/2+',left='+(screen.width-800)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}
function editproxys(id){
		 window.open("list?method=preeditproxys&id="+id,'newwindow','height=350,width=500,top='+(screen.height-500)/2+',left='+(screen.width-800)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}
function editconf(id){
		window.open("list?method=preeditconf&id="+id,'newwindow','height=550,width=800,top='+(screen.height-500)/2+',left='+(screen.width-800)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}

function delnodes(id,name) {   
		var msg = "您真的确定要删除 "+name+" 吗？！";   
		if (confirm(msg)==true){   
		     location.href="list?method=delnodes&id="+id;
		}else{   
		   return false;   
		}   
}   
function delseeds(id,name) {   
		var msg = "您真的确定要删除 "+name+" 吗？！";   
		if (confirm(msg)==true){   
		     location.href="list?method=delseeds&id="+id;
		}else{   
		   return false;   
		}   
}   
function delproxys(id,name) {   
		var msg = "您真的确定要删除 "+name+" 吗？！";   
		if (confirm(msg)==true){   
		     location.href="list?method=delproxys&id="+id;
		}else{   
		   return false;   
		}   
}  
function delconf(id,name) {   
		var msg = "您真的确定要删除 "+name+" 吗？！";   
		if (confirm(msg)==true){   
		     location.href="list?method=delconf&id="+id;
		}else{   
		   return false;   
		}   
}  

function viewremote(id){
		window.open('list?method=viewremote&id='+id,'newwindow','height=550,width=800,top='+(screen.height-500)/2+',left='+(screen.width-800)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}

function pingserver(id){
		var sel = document.getElementById("nodeid").value;
		document.selform.action="list?method=ping&id="+id+"&nodeid="+sel;
		document.selform.submit();
}


function send(id, opt){
		var sel = document.getElementById("nodeid").value;
		document.selform.action="list?method=sendjob&id="+id+"&opt="+opt+"&nodeid="+sel;
		document.selform.submit();
}

function sendglobalcommand(opt, id){
		var sel = document.getElementById("nodeid").value;
		document.selform.action="list?method=sendglobalcommand&id="+id+"&opt="+opt+"&nodeid="+sel;
		document.selform.submit();
}

function changenode(){
   var sel = document.getElementById("nodeid").value;
   if (sel == -1){
      return false;
        }
		document.selform.action="list?method=query&nodeid="+sel;
		document.selform.submit();
}

</script>



<script>
$(function () {
    $('#container').highcharts({
        title: {
            text: '爬虫服务器资源使用率',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: [
            <c:forEach var="m" items="${mmap}" varStatus="status">
               '${m.CHECK_TIME}'<c:if test="${!status.last}"> ,</c:if>
            	</c:forEach>
                                  ]
        },
        yAxis: {
            title: {
                text: '系统资源使用率(%)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '%'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'CPU使用率',
            data: [
            <c:forEach var="m" items="${mmap}" varStatus="status">
               ${m.CPU_RATE}<c:if test="${!status.last}"> ,</c:if>
            	</c:forEach>
                                  ]
        }, {
            name: '内存使用率',
            data: [
            <c:forEach var="m" items="${mmap}" varStatus="status">
               ${m.MEM_RATE} <c:if test="${!status.last}"> ,</c:if>
            	</c:forEach>
                                 ]
        }]
    });
});
</script>
</head>


<body>

<form method="post" name="selform" id="selform">


	<header id="header">
		<hgroup>
			<h1 class="site_title"><a href="#">Swift Monitor Console</a></h1>
		</hgroup>
	</header> <!-- end of header bar -->
	
	<section id="secondary_bar">
		<div class="user">
			<p>Gan Qiang (<a href="#">Administrator</a>)</p>
			<!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs"><a href="#">Swift Monitor Console</a> <div class="breadcrumb_divider"></div> <a class="current">Dashboard</a></article>
		</div>
	</section><!-- end of secondary bar -->
	
	<aside id="sidebar" class="column">
		<hr/>
		<h3>Menu</h3>
		<ul class="toggle">
			<li class="icn_new_article"><a href="list?method=query">Dashboard</a></li>
		</ul>
		
		<footer>
			<hr />
			<p><strong>Copyright &copy; 2014 Swift Monitor Console</strong></p>
			<p>Theme by <a href="#">ganqiang</a></p>
		</footer>
	</aside><!-- end of sidebar -->
	
	<section id="main" class="column">
		
		
		<article class="module width_full">
		 
			<header><h3 >爬虫服务器资源监控图
							<select id="nodeid" name="nodeid" onchange="changenode()">
							       <option value="-1" > 请选择 </option>
							    <c:forEach var="node" items="${nodemap}">
							       	<option value="${node.ID}" <c:if test="${nodeid == node.ID}">selected</c:if>>${node.IP}</option>
							     </c:forEach>
							</select>
						
					</h3>
			</header>
		
			<div class="module_content">
				<article  style="width:100%;height:400px“ class="stats_graph">
					<div id="container" style="width:100%;height:400px"></div>
				</article>
				
				<div class="clear"></div>
			</div>
		</article><!-- end of stats article -->
		
		<article class="module width_3_quarter">
		<header><h3 class="tabs_involved">实体定义</h3>
		<ul class="tabs">
   		<li><a href="#tab1">爬虫节点服务器</a></li>
    		<li><a href="#tab2">种子站点</a></li>
    		<li><a href="#tab3">Http代理服务期</a></li>
		</ul>
		</header>
		

	</div>
		<div class="tab_container">
			<div id="tab1" class="tab_content">
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr>
				   <th style="width:12px"><input id="span1" type="button" value="新增" onclick='addnodes()'></th>
    				<th>IP地址</th> 
    				<th>爬虫程序通讯端口</th> 
    				<th>操作系统</th> 
    				<th>爬虫程序是否存活</th> 
    				<th>操作</th> 
				</tr> 
			</thead> 
			<tbody>
			<c:if test="${fn:length(nodemap) > 0}">
				<c:forEach var="node" items="${nodemap}">
						<tr> 
						   <td></td> 
		    				<td>${node.IP}</td> 
		    				<td>${node.PORT}</td> 
		    				<td>${node.OS}</td> 
		    				<td><c:if test="${node.IS_ALIVE==1}">是</c:if><c:if test="${node.IS_ALIVE==0}">否</c:if></td>
		    				<td><a href="#" onclick="pingserver('${node.ID}')">连接</a> | <a href="#" onclick="editnodes('${node.ID}')">修改</a> | <a href="#" onclick="delnodes('${node.ID}','${node.IP}')">删除</a> </td>  
						</tr> 
					</c:forEach>
				</c:if>
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->
			
			<div id="tab2" class="tab_content">
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr> 
   				<th style="width:12px"><input id="span1" type="button" value="新增" onclick='addseeds()'></th>
    				<th>种子网站名称</th> 
    				<th>种子的url地址</th> 
    				<th>是否为内置</th> 
    				<th>内置名称</th> 
    				<th>操作</th> 
				</tr> 
			</thead> 
			<tbody> 
				<c:if test="${fn:length(seedmap) > 0}">
				<c:forEach var="seed" items="${seedmap}">
						<tr> 
						   <td></td> 
		    				<td>${seed.NAME}</td> 
		    				<td>${seed.URL}</td> 
		    				<td><c:if test="${seed.IS_INSIDE==1}">是</c:if><c:if test="${seed.IS_INSIDE==0}">否</c:if></td> 
		    				<td>${seed.INSIDE_NAME}</td>
		    				<td><a href="#" onclick="editseeds('${seed.ID}')">修改</a> | <a href="#" onclick="delseeds('${seed.ID}','${seed.NAME}')">删除</a> </td>  
						</tr> 
					</c:forEach>
				</c:if>
			</tbody> 
			</table>

			</div><!-- end of #tab2 -->
			
			<div id="tab3" class="tab_content">
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr> 
   				<th style="width:12px"><input id="span1" type="button" value="新增" onclick='addproxys()'></th> 
    				<th>代理IP</th> 
    				<th>代理端口</th> 
    				<th>用户名</th> 
    				<th>密码</th> 
    				<th>操作</th> 
				</tr> 
			</thead> 
			<tbody> 
				<c:if test="${fn:length(proxymap) > 0}">
				<c:forEach var="proxy" items="${proxymap}">
						<tr> 
						   <td></td> 
		    				<td>${proxy.IP}</td> 
		    				<td>${proxy.PORT}</td> 
		    				<td>${proxy.USERNAME}</td> 
		    				<td>${proxy.PASSWORD}</td>
		    				<td><a href="#" onclick="editproxys('${proxy.ID}')">修改</a> | <a href="#" onclick="delproxys('${proxy.ID}','${proxy.IP}')">删除</a> </td>  
						</tr> 
					</c:forEach>
				</c:if>
			</tbody> 
			</table>

			</div><!-- end of #tab3 -->
			
			
			
		</div><!-- end of .tab_container -->
		
		</article><!-- end of content manager article -->
		
		<article class="module width_quarter">
			<header><h3>心跳信息 Top 10</h3></header>
			<div class="message_list">
				
				 <c:forEach var="monitor" items="${monitormap}">
				     <div class="module_content"><p><strong>${monitor.NODE_IP}</strong></p></div>
					    <div class="message"><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CPU使用率：${monitor.CPU_RATE}% | 内存使用率：${monitor.MEM_RATE}%</p></div>
					</c:forEach>
			</div>

		</article><!-- end of messages article -->
		
		<div class="clear"></div>
	
		
		
		<article class="module width_full">
			<header><h3>爬虫配置</h3></header>
				<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr> 
   				<th style="width:12px"><input id="span1" type="button" value="新增" onclick='addconf()'></th> 
    				<th>爬虫节点</th> 
    				<th>开始抓取时间</th> 
    				<th>抓取间隔</th> 
    				<th>Http代理</th> 
    				<th>内置种子</th> 
    				<th>外置种子</th> 
    				<!--th>内置种子抓取延迟</th> 
    				<th>外置种子抓取延迟</th> 
    				<th>内置种子是否支持下载</th> 
    				<th>外置种子是否支持下载</th> 
    				<th>内置种子是否支持JS</th> 
    				<th>外置种子是否支持级联</th> 
    				<th>外置种子抓取深度</th--> 
    				<th>爬虫任务操作</th> 
    				<th>爬虫全局配置</th> 
    				<th>配置操作</th> 
				</tr> 
			</thead> 
			<tbody> 
				<c:if test="${fn:length(confmap) > 0}">
				<c:forEach var="conf" items="${confmap}">
						<tr> 
						   <td></td> 
		    				<td>${conf.NODE_IP}</td> 
		    				<td>${conf.START_TIME}</td> 
		    				<td>${conf.INTERVAL_TIME}秒</td> 
		    				<td>${conf.HTTP_PROXYS}</td>
		    				<td>${conf.IN_SEEDS}</td>
		    				<td>${conf.OUT_SEED_URLS}</td>
		    				<!--td>${conf.IN_DELAY}</td>
		    				<td>${conf.OUT_DELAY}</td>
		    				<td><c:if test="${conf.IN_IS_DOWNLOAD==1}">是</c:if><c:if test="${conf.IN_IS_DOWNLOAD==0}">否</c:if></td>
		    				<td><c:if test="${conf.OUT_IS_DOWNLOAD==1}">是</c:if><c:if test="${conf.OUT_IS_DOWNLOAD==0}">否</c:if></td>
		    				<td><c:if test="${conf.JS_SUPPORT==1}">是</c:if><c:if test="${conf.JS_SUPPORT==0}">否</c:if></td>
		    				<td><c:if test="${conf.IS_CASCADE==1}">是</c:if><c:if test="${conf.IS_CASCADE==0}">否</c:if></td>
		    				<td>${conf.OUT_DEPTH}</td-->
		    				<td><c:if test="${conf.DEP_STATUS==0}"><a href="#" onclick="send('${conf.ID}','allot')">部署</a></c:if><c:if test="${conf.DEP_STATUS==1}">已部署</c:if> |  <a href="#" onclick="send('${conf.ID}','pause')">暂停</a> | <a href="#" onclick="send('${conf.ID}','continue')">继续</a> |  <a href="#" onclick="send('${conf.ID}','cancel')">取消</a></td>
		    				<td><a href="#" onclick="viewremote('${conf.ID}')">查看全局配置</a>  <!--|  <a href="#" onclick="sendglobalcommand('restart','${conf.ID}')">远程重启</a>  |  <a href="#" onclick="sendglobalcommand('stop','${conf.ID}')">远程停掉</a--></td>
		    				<td> <a href="#" onclick="editconf('${conf.ID}')">修改</a> | <a href="#" onclick="delconf('${conf.ID}','${conf.NODE_IP}')">删除</a> </td>  
						</tr> 
					</c:forEach>
				</c:if>
			</tbody> 
			</table>
		</article><!-- end of styles article -->
		<div class="spacer"></div>
	</section>

	</form>	
</body>

</html>