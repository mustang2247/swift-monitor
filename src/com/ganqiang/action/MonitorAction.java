package com.ganqiang.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ganqiang.core.dao.DAOFactory;
import com.ganqiang.core.dao.IGenericDAO;
import com.ganqiang.core.util.common.StringUtil;
import com.ganqiang.core.web.CRUDBaseAction;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MonitorAction extends CRUDBaseAction
{

	private static final long serialVersionUID = -1499231549462155718L;

	@Override
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
			String nodeid = request.getParameter("nodeid");
			if (!StringUtil.isNullOrBlank(nodeid) && !nodeid.equals("-1")){
				request.setAttribute("nodeid", nodeid);
				List<Map> mmap = dao.find("select * from monitor_info where node_id='"+nodeid+"' order by ID desc limit 10");
				Collections.reverse(mmap);
				request.setAttribute("mmap", mmap);
			}
    List<Map> nodemap = dao.find("select * from nodes where 1=1 order by ID");
    request.setAttribute("nodemap", nodemap);

    List<Map> proxymap = dao.find("select * from http_proxys where 1=1 order by ID");
    request.setAttribute("proxymap", proxymap);

    List<Map> seedmap = dao.find("select * from seeds where 1=1 order by ID");
    request.setAttribute("seedmap", seedmap);
    
    List<Map> monitormap = dao.find("select * from monitor_info where 1=1 order by ID desc limit 10");
    for(Map map : monitormap){
    			Map<String,Object> node = dao.findOne("select * from nodes where id="+map.get("NODE_ID"));
    			map.put("NODE_IP", node.get("IP"));
           }
    request.setAttribute("monitormap", monitormap);
    
    List<Map> confmap = dao.find("select * from configuration where 1=1 order by ID");
    for (Map map : confmap){
		   Map<String,Object> node = dao.findOne("select * from nodes where id="+map.get("NODE_ID"));
		   if (node != null){
				   map.put("NODE_IP", node.get("IP"));
		        }
		   Object obj = map.get("HTTP_PROXY_IDS");
		   if(obj != null && !obj.equals("")){
			   List<Map> proxys = dao.find("select * from http_proxys where id in("+map.get("HTTP_PROXY_IDS").toString()+")");
			   String proxystr = "";
			   for(Map proxy : proxys){
					  proxystr += proxy.get("IP")+":"+proxy.get("PORT")+",";
			       }
			   map.put("HTTP_PROXYS", proxystr.substring(0, proxystr.length()-1));
		   		}
		   if(map.get("IN_SEED_IDS") != null && !map.get("IN_SEED_IDS").equals("")){
			   List<Map> inseeds = dao.find("select * from seeds where id in("+map.get("IN_SEED_IDS").toString()+")");
			   String inseedstr = "";
			   for(Map seed : inseeds){
					   inseedstr += seed.get("INSIDE_NAME")+",";
			            }
			   map.put("IN_SEEDS", inseedstr.substring(0, inseedstr.length()-1));
		    	}
    		}
    request.setAttribute("confmap", confmap);

    RequestDispatcher rd = request.getRequestDispatcher("/jsp/list.jsp");
    rd.forward(request, response);
	}
	
	public void preaddnodes(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/add_nodes.jsp");
			  rd.forward(request, response);
		}
	
	public void preaddseeds(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/add_seeds.jsp");
			  rd.forward(request, response);
		}

	public void preaddproxys(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/add_proxys.jsp");
			  rd.forward(request, response);
		}

	public void preaddconf(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
			List<Map> confmap = dao.find("select * from configuration where 1=1 order by ID");
			String nodeids = "";
		  for (Map map : confmap){
				  nodeids += map.get("NODE_ID") +",";
		    }
			
		  List<Map> nodemap = null;
		  if (nodeids==null || nodeids.equals("")){
				  nodemap = dao.find("select * from nodes order by ID");  
		  }else {
				  nodemap = dao.find("select * from nodes where id not in ("+nodeids.substring(0, nodeids.length()-1)+") order by ID");  
		      }
				request.setAttribute("nodemap", nodemap);

		 		List<Map> proxymap = dao.find("select * from http_proxys where 1=1 order by ID");
		 		request.setAttribute("proxymap", proxymap);
		    
		 		 List<Map> inseedmap = dao.find("select * from seeds where is_inside=1 order by ID");
		 	    request.setAttribute("inseedmap", inseedmap);
		    
		 	   List<Map> outseedmap = dao.find("select * from seeds where is_inside=0 order by ID");
		 	    request.setAttribute("outseedmap", outseedmap);
		 	    
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/add_conf.jsp");
			  rd.forward(request, response);
		}

	public void addnodes(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
					String ip = request.getParameter("ip");
					String port = request.getParameter("port");
					String os = request.getParameter("os");
					String sql = "insert into nodes(ip,port,os) values('"+ip+"','"+port+"','"+os+"')";
					IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
					dao.insert(sql);
	}

	public void addconf(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
			String nodeid = request.getParameter("nodeid");
			String starttime = request.getParameter("starttime");
			String interval = request.getParameter("interval");
			String[] proxy = request.getParameterValues("proxy");
			String proxystr = "";
			if(proxy != null){
				for(String str : proxy){
					proxystr += str+",";
				}
				proxystr = proxystr.substring(0, proxystr.length()-1);
			}
			String[] inseed = request.getParameterValues("inseed");
			String inseedstr = "";
			if(inseed != null){
				for(String str : inseed){
					inseedstr += str+",";
				}
				inseedstr = inseedstr.substring(0,inseedstr.length()-1);
			}
			String[] outseed = request.getParameterValues("outseed");
			String outseedstr = "";
			if(outseed != null){
				for(String str : outseed){
					outseedstr += str+",";
				}
				outseedstr = outseedstr.substring(0, outseedstr.length()-1);
			}
			Integer indelay = Integer.valueOf(request.getParameter("indelay")==null||request.getParameter("indelay").equals("")?"0":request.getParameter("indelay"));
			Integer outdelay = Integer.valueOf(request.getParameter("outdelay")==null||request.getParameter("outdelay").equals("")?"0":request.getParameter("outdelay"));
			Integer inisdownload = Integer.valueOf(request.getParameter("inisdownload")==null||request.getParameter("inisdownload").equals("")?"0":request.getParameter("inisdownload"));
			Integer outisdownload = Integer.valueOf(request.getParameter("outisdownload")==null||request.getParameter("outisdownload").equals("")?"0":request.getParameter("outisdownload"));
			Integer jssupport = Integer.valueOf(request.getParameter("jssupport")==null||request.getParameter("jssupport").equals("")?"0":request.getParameter("jssupport"));
			Integer iscascade = Integer.valueOf(request.getParameter("iscascade")==null||request.getParameter("iscascade").equals("")?"0":request.getParameter("iscascade"));
			Integer depth = Integer.valueOf(request.getParameter("depth")==null||request.getParameter("depth").equals("")?"0":request.getParameter("depth"));

			String sql = "insert into configuration(node_id,start_time,interval_time,http_proxy_ids,in_seed_ids,OUT_SEED_IDS,in_delay,out_delay,"
							+ "in_is_download,out_is_download,js_support,is_cascade,out_depth) "
							+ "values('"+nodeid+"','"+starttime+"','"+interval+"','"+proxystr+"','"+inseedstr+"',"
							+ "'"+outseedstr+"','"+indelay+"','"+outdelay+"','"+inisdownload+"','"+outisdownload+"','"+jssupport+"','"+iscascade+"','"+depth+"')";
			IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
			dao.insert(sql);
	}
	
	public void addproxys(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
							String ip = request.getParameter("ip");
							String port = request.getParameter("port");
							String username = request.getParameter("username");
							String password = request.getParameter("password");
							String sql = "insert into http_proxys(ip,port,username,password) values('"+ip+"','"+port+"','"+username+"','"+password+"')";
							IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
							dao.insert(sql);
	}
	
	public void addseeds(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
							String name = request.getParameter("name");
							String url = request.getParameter("url");
							String isInside = request.getParameter("isInside");
							String insideName = request.getParameter("insideName");
							String sql = "insert into seeds(name,url,is_inside,inside_name) values('"+name+"','"+url+"',"+isInside+",'"+insideName+"')";
							IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
							dao.insert(sql);
					}
	
	public void preeditnodes(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
			String sql = "select * from nodes where id="+id;
			IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
			Map<String,Object> map = dao.findOne(sql);
			request.setAttribute("node", map);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/edit_nodes.jsp");
			  rd.forward(request, response);
		}
	
	public void preeditseeds(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
			String sql = "select * from seeds where id="+id;
			IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
			Map<String,Object> map = dao.findOne(sql);
			request.setAttribute("seed", map);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/edit_seeds.jsp");
			  rd.forward(request, response);
		}
	
	public void preeditproxys(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
    			String id = request.getParameter("id");
    			String sql = "select * from http_proxys where id="+id;
    			IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
    			Map<String,Object> map = dao.findOne(sql);
    			request.setAttribute("proxy", map);
    				RequestDispatcher rd = request.getRequestDispatcher("/jsp/edit_proxys.jsp");
    			  rd.forward(request, response);
		}
	
	public void preeditconf(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
			 IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
		 	 Map<String,Object> conf = dao.findOne("select * from configuration where id='"+id+"'");
		 	 request.setAttribute("conf", conf);
		 	 String proxys = (String)conf.get("HTTP_PROXY_IDS");
		 	 List<String> proxylist = null;
		 	 if (proxys !=null && !proxys.equals("")) {
		 			proxylist = new ArrayList<String>();
		 			String[] array = proxys.split(",");
		 			for(String p : array){
		 					proxylist.add(p);
		 			}
		 	   }
		 	 
		 	 String inseeds = (String)conf.get("IN_SEED_IDS");
		 	List<String> inseedlist = null;
		 	 if (inseeds !=null && !inseeds.equals("")) {
		 			inseedlist = new ArrayList<String>();
		 			String[] array = inseeds.split(",");
		 			for(String s : array){
		 					inseedlist.add(s);
		 			}
		 	   }
		 	 
		 	 String outseeds = (String)conf.get("OUT_SEED_IDS");
			 	List<String> outseedlist = null;
			 	 if (outseeds !=null && !outseeds.equals("")) {
			 			outseedlist = new ArrayList<String>();
			 			String[] array = outseeds.split(",");
			 			for(String s : array){
			 					outseedlist.add(s);
			 			}
			 	   }
		 	 
		  List<Map> nodemap = dao.find("select * from nodes order by ID");  

				request.setAttribute("nodemap", nodemap);

		 		List<Map> proxymap = dao.find("select * from http_proxys where 1=1 order by ID");
		 		for(Map p : proxymap){
		 				String pid = p.get("ID").toString();
		 				if(proxylist != null && proxylist.contains(pid)){
		 						p.put("flag", "true");
		 				}else{
		 						p.put("flag", "false");
		 				}
		 		}
		 		request.setAttribute("proxymap", proxymap);
		    
		 	  List<Map> inseedmap = dao.find("select * from seeds where is_inside=1 order by ID");
		 	 for(Map s : inseedmap){
		 				String sid = s.get("ID").toString();
		 				if(inseedlist != null && inseedlist.contains(sid)){
		 						s.put("flag", "true");
		 				}else{
		 						s.put("flag", "false");
		 				}
		 		}
		 	 request.setAttribute("inseedmap", inseedmap);

		  	List<Map> outseedmap = dao.find("select * from seeds where is_inside=0 order by ID");
		  	 for(Map s : outseedmap){
		 				String sid = s.get("ID").toString();
		 				if(outseedlist != null && outseedlist.contains(sid)){
		 						s.put("flag", "true");
		 				}else{
		 						s.put("flag", "false");
		 				}
		 		}
		 	 request.setAttribute("outseedmap", outseedmap);
		 	    
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/edit_conf.jsp");
			  rd.forward(request, response);
		}
	
	
	public void editnodes(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
					String ip = request.getParameter("ip");
					String port = request.getParameter("port");
					String os = request.getParameter("os");
					String sql = "update nodes set ip='"+ip+"', port='"+port+"', os='"+os+"' where id='"+id+"'";
					IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
					dao.update(sql);
	}
	
	public void editseeds(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String url = request.getParameter("url");
			String isInside = request.getParameter("isInside");
			String insideName = request.getParameter("insideName");
					String sql = "update seeds set name='"+name+"', url='"+url+"', is_inside='"+isInside+"', inside_name='"+insideName+"' where id='"+id+"'";
					IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
					dao.update(sql);
	}
	
	public void editproxys(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
					String ip = request.getParameter("ip");
					String port = request.getParameter("port");
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					String sql = "update http_proxys set ip='"+ip+"', port='"+port+"', username='"+username+"', password='"+password+"' where id='"+id+"'";
					IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
					dao.update(sql);
	}
	
	public void editconf(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
			String nodeid = request.getParameter("nodeid");
			String starttime = request.getParameter("starttime");
			String interval = request.getParameter("interval");
			String[] proxys = request.getParameterValues("proxy");
			String proxystr = "";
			if (proxys !=null && !proxys.equals("")) {
				for(String str : proxys){
					proxystr += str+",";
				}
				proxystr = proxystr.substring(0, proxystr.length()-1);
			}
			
			String[] inseed = request.getParameterValues("inseed");
			String inseedstr = "";
			if (inseed !=null && !inseed.equals("")) {
				for(String str : inseed){
					inseedstr += str+",";
				}
				inseedstr= inseedstr.substring(0, inseedstr.length()-1);
			}
			
			String[] outseed = request.getParameterValues("outseed");
			String outseedstr = "";
			if (outseed !=null && !outseed.equals("")) {
				for(String str : outseed){
					outseedstr += str+",";
				}
				outseedstr = outseedstr.substring(0, outseedstr.length()-1);
			}
			
			Integer indelay = Integer.valueOf(request.getParameter("indelay")==null||request.getParameter("indelay").equals("")?"0":request.getParameter("indelay"));
			Integer outdelay = Integer.valueOf(request.getParameter("outdelay")==null||request.getParameter("outdelay").equals("")?"0":request.getParameter("outdelay"));
			Integer inisdownload = Integer.valueOf(request.getParameter("inisdownload")==null||request.getParameter("inisdownload").equals("")?"0":request.getParameter("inisdownload"));
			Integer outisdownload = Integer.valueOf(request.getParameter("outisdownload")==null||request.getParameter("outisdownload").equals("")?"0":request.getParameter("outisdownload"));
			Integer jssupport = Integer.valueOf(request.getParameter("jssupport")==null||request.getParameter("jssupport").equals("")?"0":request.getParameter("jssupport"));
			Integer iscascade = Integer.valueOf(request.getParameter("iscascade")==null||request.getParameter("iscascade").equals("")?"0":request.getParameter("iscascade"));
			Integer depth = Integer.valueOf(request.getParameter("depth")==null||request.getParameter("depth").equals("")?"0":request.getParameter("depth"));
			
					String sql = "update configuration set NODE_ID='"+nodeid+"', INTERVAL_TIME='"+interval+"', START_TIME='"+starttime+"', IN_SEED_IDS='"+inseedstr
									+"', HTTP_PROXY_IDS='"+proxystr+"' "
									+ ", OUT_SEED_IDS='"+outseedstr+"' , IN_DELAY='"+indelay+"' , OUT_DELAY='"+outdelay+"' , IN_IS_DOWNLOAD='"+inisdownload+"' , OUT_IS_DOWNLOAD='" + outisdownload
									+"' , JS_SUPPORT='"+jssupport+"' , IS_CASCADE='"+iscascade+"' , out_depth='"+depth+"' where id='"+id+"'";
					IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
					dao.update(sql);
	}
	
	public void delnodes(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
					String sql = "delete from nodes where id='"+id+"'";
					IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
					dao.delete(sql);
					RequestDispatcher rd = request.getRequestDispatcher("list?method=query");
					  rd.forward(request, response);
	}
	
	public void delseeds(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
					String sql = "delete from seeds where id='"+id+"'";
					IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
					dao.delete(sql);
					RequestDispatcher rd = request.getRequestDispatcher("list?method=query");
					  rd.forward(request, response);
	}
	
	public void delproxys(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
					String sql = "delete from http_proxys where id='"+id+"'";
					IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
					dao.delete(sql);
					RequestDispatcher rd = request.getRequestDispatcher("list?method=query");
					  rd.forward(request, response);
	}
	
	public void delconf(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException {
			String id = request.getParameter("id");
					String sql = "delete from configuration where id='"+id+"'";
					IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
					dao.delete(sql);
					RequestDispatcher rd = request.getRequestDispatcher("list?method=query");
					  rd.forward(request, response);
	}
	
	public void ping(HttpServletRequest request, HttpServletResponse response)
				      throws ServletException, IOException, InterruptedException {
		
			   String id = request.getParameter("id");
    			String sql = "select * from nodes where id='"+id+"'";
    			IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
    			String nodeid = request.getParameter("nodeid");
    			if (!StringUtil.isNullOrBlank(nodeid) && !nodeid.equals("-1")){
    				request.setAttribute("nodeid", nodeid);
    				List<Map> mmap = dao.find("select * from monitor_info where node_id='"+nodeid+"' order by ID desc limit 10");
    				Collections.reverse(mmap);
    				request.setAttribute("mmap", mmap);
    			}
    			Map<String,Object> map = dao.findOne(sql);
    			String ip = map.get("IP").toString();
    			int port = (int)map.get("PORT");
					RemoteService.ping(dao, id, ip, port);
					RequestDispatcher rd = request.getRequestDispatcher("list?method=query");
					rd.forward(request, response);
	}
	
	public void viewremote(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
    			String id = request.getParameter("id");
    			String sql = "select * from configuration where id='"+id+"'";
    			IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
    			Map<String,Object> conf = dao.findOne(sql);
    			Map<String,Object> map = dao.findOne("select * from nodes where id="+conf.get("NODE_ID"));
    			String ip = map.get("IP").toString();
    			int port = (int)map.get("PORT");
    			Map<String,Object> m = RemoteService.view(ip, port);
    			request.setAttribute("remotep", m);
    			RequestDispatcher rd = request.getRequestDispatcher("/jsp/view_remote.jsp");
			  	rd.forward(request, response);
			}
	
	public void sendglobalcommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String id = request.getParameter("id");
			String opt = request.getParameter("opt");
			String sql = "select * from configuration where id='"+id+"'";
			IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
			String nodeid = request.getParameter("nodeid");
			if (!StringUtil.isNullOrBlank(nodeid) && !nodeid.equals("-1")){
				request.setAttribute("nodeid", nodeid);
				List<Map> mmap = dao.find("select * from monitor_info where node_id='"+nodeid+"' order by ID desc limit 10");
				Collections.reverse(mmap);
				request.setAttribute("mmap", mmap);
			}
			Map<String,Object> conf = dao.findOne(sql);
			Map<String,Object> map = dao.findOne("select * from nodes where id="+conf.get("NODE_ID"));
			String ip = map.get("IP").toString();
			int port = (int)map.get("PORT");
			RemoteService.sendGlobalCommand(opt, ip, port);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/view_remote.jsp");
		  rd.forward(request, response);
	}
	
	public void sendjob(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
    			String id = request.getParameter("id");
    			String opt = request.getParameter("opt");
    			String sql = "select * from configuration where id='"+id+"'";
    			IGenericDAO dao = DAOFactory.getInstance().buildGenericDAO();
    			Map<String,Object> conf = dao.findOne(sql);
    			Map<String,Object> map = dao.findOne("select * from nodes where id="+conf.get("NODE_ID"));
    			String nodeid = request.getParameter("nodeid");
    			if (!StringUtil.isNullOrBlank(nodeid) && !nodeid.equals("-1")){
    				request.setAttribute("nodeid", nodeid);
    				List<Map> mmap = dao.find("select * from monitor_info where node_id='"+nodeid+"' order by ID desc limit 10");
    				Collections.reverse(mmap);
    				request.setAttribute("mmap", mmap);
    			}
    			String ip = map.get("IP").toString();
    			int port = (int)map.get("PORT");
    		  RemoteService.sendjob(id, opt,ip, port);
    			RequestDispatcher rd = request.getRequestDispatcher("list?method=query");
			  	rd.forward(request, response);
			}

	/**
	 * 增加 (暂时不做)
	 */
	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * 修改 (暂时不做)
	 */
	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * 删除 (暂时不做)
	 */
	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * 重命名 (暂时不做)
	 */
	public void rename(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

  @Override
  public void detail(HttpServletRequest request, HttpServletResponse response)
    				throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		
       }

}
