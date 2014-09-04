package com.ganqiang.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.ganqiang.core.dao.DAOFactory;
import com.ganqiang.core.dao.IGenericDAO;
import com.ganqiang.core.util.common.CalculateUtil;
import com.ganqiang.core.util.common.DateUtil;
import com.ganqiang.thrift.GlobalCommand;
import com.ganqiang.thrift.GlobalConfig;
import com.ganqiang.thrift.Job;
import com.ganqiang.thrift.JobCommand;
import com.ganqiang.thrift.JobResponse;
import com.ganqiang.thrift.PingResponse;
import com.ganqiang.thrift.SwiftController;

@SuppressWarnings({ "rawtypes" })
public final class RemoteService{

		
		public static void ping(IGenericDAO dao,String id, String ip,int port) {
				int clientTimeout = 30000;  
				TTransport transport = new TFramedTransport(new TSocket(ip, port,  clientTimeout));  
		        TProtocol protocol = new TCompactProtocol(transport);  
		        SwiftController.Client client = new SwiftController.Client(protocol);
		        try {  
		            transport.open();  
		            PingResponse pr = client.ping(); 
		            String date = DateUtil.getCurrentTimeStr();
		            dao.insert("insert into monitor_info(NODE_ID,MEM_RATE,CPU_RATE,CHECK_TIME) values('"+id+"','"+pr.getMemrate()+"','"+pr.getCpurate()+"','"+date+"')");
		            dao.update("update nodes set is_alive=1 where id='"+id+"'");
		        } catch (Exception e) {
		            dao.update("update nodes set is_alive=0 where id='"+id+"'");
		        			 }  
		        transport.close();
		}
		
		public static Map<String,Object> view(String ip,int port) {
				int clientTimeout = 30000;  
				Map<String,Object> map = new HashMap<String,Object>();
				TTransport transport = new TFramedTransport(new TSocket(ip, port,  clientTimeout));  
		        TProtocol protocol = new TCompactProtocol(transport);  
		        SwiftController.Client client = new SwiftController.Client(protocol);
		        try {  
		            transport.open();
		            GlobalConfig gc = client.view();
		            map.put("threadnum", gc.getThreadNum());
		            map.put("disk", gc.getDisk());
		            map.put("index", gc.getIndex());
		            map.put("dbdriver", gc.getDbDriver());
		            map.put("dburl", gc.getDbUrl());
		            map.put("dbuser", gc.getDbUsername());
		            map.put("dbpwd", gc.getDbPassword());
		            map.put("dbpoolsize", gc.getDbPoolSize());
		            map.put("disksync", gc.getSyncDomain());
		        } catch (Exception e) {
		        	e.printStackTrace();
		        			 }  
		        transport.close();
		        return map;
		}
		
		public static Map<String,Object> sendGlobalCommand(String opt,String ip,int port) {
			int clientTimeout = 30000;  
			Map<String,Object> map = new HashMap<String,Object>();
			TTransport transport = new TFramedTransport(new TSocket(ip, port,  clientTimeout));  
	        TProtocol protocol = new TCompactProtocol(transport);  
	        SwiftController.Client client = new SwiftController.Client(protocol);
	        try {  
	            transport.open();
	            if(opt.equals("restart")){
	               client.sendGlobalCommand(GlobalCommand.RESTART);
	           	}else{
	           		  client.sendGlobalCommand(GlobalCommand.STOP);
	            					}
	            
	        } catch (Exception e) {
	        					e.printStackTrace();
	        			 }  
	        transport.close();
	        return map;
	}
		
		public static void sendjob(String id, String opt, String ip,int port) {
						int clientTimeout = 30000;  
						TTransport transport = new TFramedTransport(new TSocket(ip, port,  clientTimeout));  
		        TProtocol protocol = new TCompactProtocol(transport);  
		        IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
		        SwiftController.Client client = new SwiftController.Client(protocol);
		        try {  
		            transport.open();  
		            if(opt.equals("allot")){
		            	  Job job = new Job();
		            		
		            		Map<String,Object> conf =	dao.findOne("select * from configuration where id="+id);
		            		String proxys = (String)conf.get("HTTP_PROXY_IDS");
		            		List<Map> list = dao.find("select * from http_proxys where id in ('"+proxys+"')");
		            		List<String> proxylist = new ArrayList<String>();;
		            		for(Map map : list){
		            		    proxylist.add(map.get("IP").toString());
		            							 }
		            		Object inseed = conf.get("IN_SEED_IDS");
		            		if(inseed != null && !inseed.equals("")){
		            			  List<Map> seed = dao.find("select * from seeds where id in ('"+inseed.toString()+"')");
				            		List<String> inseedlist = new ArrayList<String>();;
		                  for(Map map : seed){
		                	  		inseedlist.add(map.get("INSIDE_NAME").toString());
		                                                 }
		                  	job.setInSeeds(inseedlist);
		            	                       	}
		            		Object outseed = conf.get("OUT_SEED_IDS");
		            		if(outseed != null && !outseed.equals("")){
		            			   List<Map> seed = dao.find("select * from seeds where id in ('"+outseed.toString()+"')");
				            		 List<String> outseedlist = new ArrayList<String>();;
		                   for(Map map : seed){
		                	  		outseedlist.add(map.get("URL").toString());
		                                                  }
		                   job.setOutSeeds(outseedlist);
		            	                       	}
		            	  
		            	  job.setDepth((Integer)(conf.get("OUT_DEPTH")==null?0:conf.get("OUT_DEPTH")));
		            	  job.setHttpProxys(proxylist);
		            	  job.setId(id);
		            	  job.setStartTime(conf.get("START_TIME").toString());
		            	  job.setInterval(Long.valueOf(CalculateUtil.parseExp(conf.get("INTERVAL_TIME").toString())));
		            	  job.setInDelay(Long.valueOf(conf.get("IN_DELAY").toString()));
		            	  Object indown = conf.get("IN_IS_DOWNLOAD");
		            	  if(indown == null){
		            			  job.setInIsdownload(false);
		            	   }else{
		            	     Integer count = (Integer)indown;
		                   if(count == 1){
		                       job.setInIsdownload(true);
		                   }else{
		                      job.setInIsdownload(false);
		                                                  }
		            	                          } 
		            	  Object outdown = conf.get("OUT_IS_DOWNLOAD");
                  if(outdown == null){
                       job.setOutIsdownload(false);
                   }else{
                       Integer count = (Integer)outdown;
                       if(count == 1){
                           job.setOutIsdownload(true);
                       }else{
                           job.setOutIsdownload(false);
                                                            }
                                                  } 
                  Object jssupport = conf.get("JS_SUPPORT");
                  if(jssupport == null){
                       job.setJsSupport(false);
                   }else{
                       Integer count = (Integer)jssupport;
                       if(count == 1){
                           job.setJsSupport(true);
                       }else{
                           job.setJsSupport(false);
                                                            }
                                                  } 
                  Object iscascade = conf.get("IS_CASCADE");
                  if(iscascade == null){
                       job.setIsCascade(false);
                   }else{
                       Integer count = (Integer)iscascade;
                       if(count == 1){
                           job.setIsCascade(true);
                       }else{
                           job.setIsCascade(false);
                                                            }
                                                  }
                  JobResponse jr = client.allot(job);
                  if(jr.getStatus().equals("ok")){
                	  		dao.update("update configuration set dep_status=1 where id="+id);
                  							   }
		           	}else if(opt.equals("pause")){
		           			  JobResponse jr = 		client.sendJobCommand(id, JobCommand.PAUSE);
		           		  	if(jr.getStatus().equals("ok")){
		           					dao.update("update configuration set job_status=2 where id="+id);
		           							    	}
		           	}else if(opt.equals("continue")){
	           			 JobResponse jr = 		client.sendJobCommand(id, JobCommand.CONTINUE);
	           			 if(jr.getStatus().equals("ok")){
	           					dao.update("update configuration set job_status=1 where id="+id);
	           								 }
		           	}else if(opt.equals("cancel")){
	           			  JobResponse jr = 		client.sendJobCommand(id, JobCommand.CANCEL);
	           			  if(jr.getStatus().equals("ok")){
	           				  	dao.update("update configuration set job_status=3,dep_status=0 where id="+id);
	           							    	}
		           						}
		        } catch (Exception e) {
		        	e.printStackTrace();
		        			 }  
		        transport.close();
		    
		}
		
		

}
