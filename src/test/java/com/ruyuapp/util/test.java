package com.ruyuapp.util;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gaorui on 16/12/22.
 */
public class test {
    public static  Set<IpPort> ips = new HashSet<IpPort>();
    public static void main(String args[]){
        String s ="<li class=\"paihang1\"><a class=\"b\" href=\"http://www.youdaili.net/Daili/QQ/23555.html\" title=\"12月15号 qq代理ip最新地址\">12月15号 qq代理ip最新地址</a><br>183.245.147.40:136@HTTP#浙江省丽水市 移动 210.22.108.90:80@HTTP...</li>\n" +
                "<li class=\"paihang2\"><a class=\"b\" href=\"http://www.youdaili.net/Daili/QQ/24201.html\" title=\"12月17号 qq ip在线代理服务器\">12月17号 qq ip在线代理服务器</a><br>61.158.173.14:8080@HTTP#河南省商丘市 联通 120.52.73.97:8081@HTT...</li>\n" +
                "<li class=\"paihang3\"><a class=\"b\" href=\"http://www.youdaili.net/Daili/QQ/24879.html\" title=\"12月20号 qq代理服务器最新ip地\">12月20号 qq代理服务器最新ip地</a><br>111.202.73.146:8888@HTTP#北京市 联通 202.105.131.231:80@HTTP#广东省...</li>";
        Pattern p = Pattern.compile("<br>(.*)@");
        Matcher m = p.matcher(s);
        while(m.find()) {
            System.out.println();
            IpPort ip = new IpPort();
            ip.setIp( m.group(1).substring(0, m.group(1).indexOf("@")).split(":")[0]);
            ip.setIp(m.group(1).substring(0, m.group(1).indexOf("@")).split(":")[1]);
            System.out.print(m.group(1).substring(0, m.group(1).indexOf("@")).split(":")[0]+m.group(1).substring(0, m.group(1).indexOf("@")).split(":")[1]);
            ips.add(ip);

        }
    }
}
