/**
 *
 */
package com.ruyuapp;

import com.ruyuapp.proxy.HttpProxy;
import com.ruyuapp.proxy.ProxyPool;
import com.ruyuapp.util.HttpStatus;
import com.sleepycat.je.utilint.Stat;

import java.io.IOException;
import java.net.*;
import java.util.Timer;

/**
 * @author <a href="mailto:letcheng@ruyuapp.com">letcheng</a>
 * @version create at 2016年3月28日 15:32
 */
public class Main {

    public static  ProxyPool proxyPool = new ProxyPool();
    /**
     * 为了阻塞主线程，不在junit中测试
     *
     * @param
     */
    public static void main(String Ip,int Port) {


//      proxyPool.add("125.211.182.231",8998);
//      proxyPool.add("36.42.32.126",8080);
//      proxyPool.add("123.130.143.54",8998);
//      Timer

        System.err.println("proxyPool add():"+Ip+":"+Port);
        HttpProxy httpProxy  = proxyPool.borrow(); // 从 ProxyPool 中获取一个Proxy;
        System.err.println("proxyPool borrow():"+Ip+":"+Port);
        URL url = null;
        try {
                url = new URL("http://www.kuaidaili.com/check/");
                HttpURLConnection uc = (HttpURLConnection)url.openConnection(httpProxy.getProxy());
                System.out.println("code:"+uc.getResponseCode());
                uc.connect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        proxyPool.reback(httpProxy, HttpStatus.SC_OK); // 使用完成之后，归还 Proxy,并将请求结果的 http 状态码一起传入

        proxyPool.allProxyStatus();  // 可以获取 ProxyPool 中所有 Proxy 的当前状态

    }

}