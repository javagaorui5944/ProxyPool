package com.ruyuapp.MyCrawler.server.util;

import java.io.IOException;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *  封装  jsoup http 请求
 *  最多请求4次，4次无法连接就返回空文档
 *  默认3000ms 无法返回结果即为超时。（为了数据优质）
 * @author Administrator
 *
 */
public  class  CrawerBase {
   public static String[] ua = { "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)",
			"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)",
			"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)",
			"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)",
			"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)",
			"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; ARM; Trident/6.0)",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36",
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8) AppleWebKit/536.25 (KHTML, like Gecko) Version/6.0 Safari/536.25" };
	  public static Random random;

	public static Document get(String url, int trys) throws IOException {
		  CrawerBase.random = new Random();
		try {
			Connection connection = Jsoup.connect(url);
			connection.timeout(3000);
			String userAgent = ua[random.nextInt(ua.length - 1)];// 随机设置，但部分站点存在高频解析不一致 问题。
			//String userAgent = ua[4];   // 设置 固定值 可解决这一问题。
			 connection.userAgent(userAgent); 
			 
			 return connection.get();
		} catch (IOException e) {
			System.out.println("try connect the page:" + url + ",try times:" + trys);
			if (trys-- != 0) {
				return get(url, trys);
			}
			throw e;
		}
	}

	public static Document get(String url) {
		int trys = 3;
		try {
			return get(url, trys);
		} catch (Exception e) {

		}
		  // 4次请求之后无法解析返回空文档
		return new Document("");
	}
	
	
	public static Document proxyGet(String url, int trys,String ip,int port) throws IOException {
		  CrawerBase.random = new Random();
		try {
			Connection connection = Jsoup.connect(url);
			connection.timeout(1000);
			String userAgent = ua[random.nextInt(ua.length - 1)];
			connection.userAgent(userAgent);
			connection(ip, port);
		    return connection.get();
		} catch (IOException e) {
			System.out.println("try connect the page:" + url + ",try times:" + trys);
			if (trys-- != 0) {
				return get(url, trys);
			}
			throw e;
		}
	}
	
	public static Document proxyGet(String url,String ip,int port) {
		int trys = 3;
		try {
			return proxyGet(url, trys,ip,port);
		} catch (Exception e) {

		}
        // 4次请求之后无法解析返回空文档
		return new Document("");
	}
	
	
	/*public static void main(String[] args) {
		// 单次代理效果测试
		Document doc = CrawerBase.proxyGet("http://1212.ip138.com/ic.asp", "111.26.225.118", 80);
		System.out.println(doc.toString());
		Document doc2 = CrawerBase.get("http://1212.ip138.com/ic.asp");
		System.out.println(doc2.toString());
	}*/
}
