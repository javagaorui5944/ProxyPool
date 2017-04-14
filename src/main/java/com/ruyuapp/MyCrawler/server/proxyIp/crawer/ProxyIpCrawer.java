package com.ruyuapp.MyCrawler.server.proxyIp.crawer;

import java.util.ArrayList;
import java.util.List;

import com.ruyuapp.MyCrawler.server.entity.ProxyIp;
import com.ruyuapp.MyCrawler.server.thread.CheckThread;

 

/**
 * 经过测试过的 proxy ip 使用时仍然可能失效、使用本机地址， ProxyIpCrawer 高频运行 来降低 使用本地 ip 频率
 * 
 * @author chuang
 *
 */
public abstract class ProxyIpCrawer {

	public List<ProxyIp> allProxyIps;// 解析页面获取的所有proxyip
	public List<ProxyIp> workProxyIps;// 测试之后可用的 proxyip
	public String notIp; // 测试时过滤掉的本机ip
	public String website;

	public ProxyIpCrawer(String notIp,String website) {
		super();
		this.notIp = notIp;
		this.website=website;
		this.allProxyIps = new ArrayList();
		this.workProxyIps = new ArrayList();
	}

	/**
	 * 从数据库加载代理ip ,
	 */
	public void loadDB() {
		/* 数据库 使用 Ip+port两个字段作为 主键 限制重复 */
	}

	/**
	 * 网页抓取 proxy ip，放入 allProxyIps
	 */
	public abstract void fetchProxyIp();

	/**
	 * allProxyIps 检验有效后放入 workProxyIps 多线程检验
	 */
	public void filterWorkProxyIp() {
		List<CheckThread> checkList = new ArrayList();
		for (ProxyIp ip : this.allProxyIps) {
			CheckThread check = new CheckThread(ip,this.notIp);
			check.start();
			checkList.add(check);
        }
		
		try{
			for(CheckThread check : checkList){
				check.join();
	    }
			
		}catch(InterruptedException e){
		   e.printStackTrace();
		}
		
		for(ProxyIp proxyIp : this.allProxyIps){
			if(proxyIp.isWork()){
				this.workProxyIps.add(proxyIp);
			}
		}
		System.err.println("filter method over !");
	}

	public void showWorkProxyIpInfo() {

		System.out.println("******有效 Proxy IPs***** ");
		System.err.println("size : ("+this.workProxyIps.size()+")");
		 
		for (ProxyIp proxyIp : this.workProxyIps) {
			System.err.println(" work : " + proxyIp.getIp() + ":" + proxyIp.getPort() + "----" + proxyIp.getArea()
					+ "----" + proxyIp.getType());
		}
		System.out.println("*************************");

	}

	/**
	 * workProxyIps 存入数据库
	 */
	public void persistWorkProxyIpsDB() {

	}

}
