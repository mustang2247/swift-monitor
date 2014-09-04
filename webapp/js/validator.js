String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
}

function getFieldValue(field){
  var fieldobj = document.getElementById(field);
  if(null == fieldobj || 'undefined'==typeof(fieldobj)){
    return;
  }
  return fieldobj.value;
}


function isNull(field) {
  var str = getFieldValue(field);
	if (str.trim() == "")
		return true;
	var regu = "^[ \f\n\r\t]+$";
	var re = new RegExp(regu);
	return re.test(str);
}


function isNulls(field) {
  var flagArray=new Array()
  var regu = "^[ \f\n\r\t]+$";
  var objs = document.getElementsByName(field);
  if(null == objs || typeof(objs)=='undefined'){
    return;
  }
  for(var i=0;i<objs.length;i++){
    if(objs[i].value.trim() == ""){
      flagArray[i]=true;
      continue;
    }
    var re = new RegExp(regu);
    flagArray[i]=re.test(objs[i].value);
  }

	for(var x in flagArray){
    if(flagArray[x]){
      return true;
    }
  }

	return false;
}


/**
 * 整数，可以为负数
 **/
function isInteger(field) {
  var str = getFieldValue(field);
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
}

/**
 * 数字精度 带小数点
 **/
function isPrecision(field) {
  var str = getFieldValue(field);
	//var reg=/^\d*\.\d*$/;
  var reg=/^[.\d]*[.][.\d]+|\d+$/;
	return reg.test(str);
}

/**
 * 正整数
 **/
function isNumber(field) {
  var s = getFieldValue(field);
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
		return true;
	} else {
		return false;
	}
}

function isDecimal(str) {
	if (isInteger(str))
		return true;
	var re = /^[-]{0,1}(\d+)[\.]+(\d+)$/;
	if (re.test(str)) {
		if (RegExp.$1 == 0 && RegExp.$2 == 0)
			return false;
		return true;
	} else {
		return false;
	}
}


function isEmail(field) {
  var strEmail = getFieldValue(field);
	var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
	if (emailReg.test(strEmail)) {
		return true;
	} else {
		return false;
	}
}

function lengthOf(str){
	var ret = 0;
	for(var e = 0;e< str.length;e++){
		if(/[^\x00-\xff]/.test(str.charAt(e))){
			ret+=2;
		}else
			ret+=1;
	}
	return ret;
}

function warnMsg(field,msg){
   document.getElementById(field).innerHTML='<font color=red>'+msg+'</font>';
}

function clearMsg(field){
  document.getElementById(field).innerHTML='';
}

/**
 * 验证checkbox是否被选中
 **/
function isChecked(field){
  var sel = false;
  var objs = document.getElementsByName(field);
  if(null == objs || typeof(objs)=='undefined'){
    return;
  }
  for(var i=0;i<objs.length;i++){
    if(objs[i].checked){
      sel = true;
    }
  }
  return sel;
}

/**
 * 验证checkbox是否被选中其一
 **/
function isSingleChecked(field){
  var sel = false;
  var objs = document.getElementsByName(field);
  if(null == objs || typeof(objs)=='undefined'){
    return;
  }
  for(var i=0;i<objs.length;i++){
    if(objs[i].checked){
      sel = true;
      break;
    }
  }
  return sel;
}

/**
 * 验证下拉框是否被选中
 * 判断自定义unSelectIndex如果跟下拉框中选中的option
 * 一致的话，那就此下拉框就未选中，返回false
 **/
function isSelected(field){
  var sel = false;
  var objs = document.getElementsByName(field);
  if(null == objs || typeof(objs)=='undefined'){
    return;
  }
  for(var i=0;i<objs.length;i++){
    if(0 != objs[i].selectedIndex){
      sel = true;
    }
  }
  return sel;
}

/**
 * 验证是否为中文
 **/
function isChinese(field){
  var value = getFieldValue(field);
  var reg = /.*[\u4e00-\u9fa5]+.*$/;
  return reg.test(value)
} 











/*************************下面是多项目选择*************************************/
function isMultiSelected(field){
  var sel = false;
  var objs = document.getElementsByName(field);
  if(null == objs || typeof(objs)=='undefined'){
    return;
  }
  for(var i=0;i<objs.length;i++){
    if(0 < objs[i].options.length){
      sel = true;
    }
  }
  return sel;
}

function addMultiSelect(source,target){
      var oDest=document.getElementById(target);
      for (var i=0;i<document.getElementById(source).options.length;i++)
      {   
          var e=document.getElementById(source).options[i];   
          if (e.selected){   
              insert(oDest,e.innerText,e.value);   
          }   
     }   
     oDest=document.getElementById(source);   
//     removeSelected(oDest);   
}   
     
function addAllMultiSelect(source,target){   
      var oDest=document.getElementById(target);   
      for (var i=0;i<document.getElementById(source).options.length;i++)   
      {   
          var e=document.getElementById(source).options[i];   
          insert(oDest,e.innerText,e.value);   
      }   
      oDest=document.getElementById(source);   
//      removeAll(oDest);   
}   

function removeMultiSelect(source,target){   
//      var oDest=document.getElementById(source);  
//      for (var i=0;i<document.getElementById(target).options.length;i++)   
//      {   
//          var e=document.getElementById(target).options[i];   
//          if (e.selected){   
//              insert(oDest,e.innerText,e.value);   
//          }            
//     } 
      var oDest=document.getElementById(target);   
      removeSelected(oDest);   
}   
     
function removeAllMultiSelect(source,target){   
//      var oDest=document.getElementById(source);   
//      for (var i=0;i<document.getElementById(target).options.length;i++)   
//      {   
//          var e=document.getElementById(target).options[i];   
//          insert(oDest,e.innerText,e.value);   
     
//      }   
      var oDest=document.getElementById(target);   
      removeAll(oDest);   
  }   

function removeAll(oSelect){
      for( i=oSelect.childNodes.length -1;i>=0;i--){   
          var node = oSelect.childNodes[i];   
          if(node.nodeType == 1){
              oSelect.removeChild(node);   
          }
        }   
  }   

function insert(oDest,name, value){   
          var oNewNode = document.createElement("option");   
          oNewNode.innerText=name;   
          oNewNode.value = value;   
          addUniqueNode(oNewNode, oDest)   
  }   
 
function  addUniqueNode(node, oDest){   
 var oNewNode = document.createElement("option");   
 var nodeExist = false;  

 for(var y=0;y<oDest.options.length;y++){
     if(node.value == oDest.options[y].value){   
        nodeExist = true;   
        break;   
     }   
 }   

 if(!nodeExist){  
    var newNode = node.cloneNode(true);   
    oDest.appendChild(newNode);   
 }       
}   

function removeSelected(oSelect){  
  for( i=oSelect.childNodes.length -1;i>=0;i--){
      var node = oSelect.childNodes[i]; 
      if(node.nodeType == 1){   
        if(node.selected)
          oSelect.removeChild(node);   
      }   
  }   
}   

//给firefox添加innerText属性
 function isIE(){ //ie? 
    if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1) 
     return true; 
    else 
     return false; 
 } 
 
 if(!isIE()){ //firefox innerText define
    HTMLElement.prototype.__defineGetter__(     "innerText", 
     function(){
      var anyString = "";
      var childS = this.childNodes;
      for(var i=0; i<childS.length; i++) {
       if(childS[i].nodeType==1)
        anyString += childS[i].tagName=="BR" ? '\n' : childS[i].innerText;
       else if(childS[i].nodeType==3)
        anyString += childS[i].nodeValue;
      }
      return anyString;
     } 
    ); 
    HTMLElement.prototype.__defineSetter__(     "innerText", 
     function(sText){ 
      this.textContent=sText; 
     } 
    ); 
 }
 


/*************************上面是多项目选择*************************************/











