package com.ruyuapp.MyCrawler.server.thread;

import com.ruyuapp.MyCrawler.server.entity.ProxyIp;
import com.ruyuapp.MyCrawler.server.util.CrawerBase;
import org.jsoup.nodes.Document;



public class CheckThread extends Thread {

	private ProxyIp proxyIp;
	private String notIp;

	public CheckThread(ProxyIp proxyIp,String notIp) {
		super();
		this.proxyIp = proxyIp;
		this.notIp=notIp;
	}

	public ProxyIp getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(ProxyIp proxyIp) {
		this.proxyIp = proxyIp;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Document docTest = CrawerBase.proxyGet("http://1212.ip138.com/ic.asp",this.proxyIp.getIp(),this.proxyIp.getPort());
		String ipText = docTest.select("center").text();
		//System.out.print(ipText);
	    if (!ipText.contains(notIp) && !ipText.trim().equals("")) {
	        this.proxyIp.setWork(true);
			System.err.println("    yes ");
		} else {
		    // 默认极为 false
			System.err.println("    no");
		}
		
	}
}
