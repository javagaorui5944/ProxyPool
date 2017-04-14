package com.ruyuapp.MyCrawler.server.client;

import com.ruyuapp.Main;
import com.ruyuapp.MyCrawler.server.entity.ProxyIp;
import com.ruyuapp.MyCrawler.server.proxyIp.crawer.ProxyIpCrawer;
import com.ruyuapp.MyCrawler.server.proxyIp.crawer.impl.c01.ProxyIpCrawerImpl;

import java.util.List;


public class Client {
	
	public ProxyIpCrawer proxyIpCrawer;
    public Client(ProxyIpCrawer proxyIpCrawer) {
		super();
		this.proxyIpCrawer = proxyIpCrawer;
	}





	public static void main(String[] args) {
		 
        Client client = new Client(new ProxyIpCrawerImpl("109.74.75.34"));
        
        /*修改为链式调用*/
        client.proxyIpCrawer.fetchProxyIp();
		List<ProxyIp> allProxyIps =  client.proxyIpCrawer.allProxyIps;
		for(ProxyIp Proxyip: allProxyIps){

			Main.proxyPool.add(Proxyip.getIp(),Proxyip.getPort());
		}
     /*   client.proxyIpCrawer.filterWorkProxyIp();
        client.proxyIpCrawer.showWorkProxyIpInfo();

        for(ProxyIp proxyIp : client.proxyIpCrawer.workProxyIps){
        	Document doc = CrawerBase.proxyGet("http://1212.ip138.com/ic.asp", proxyIp.getIp(), proxyIp.getPort());
        	System.out.println(" document  -----"+doc.select("center").text());
        }*/
	}

}
