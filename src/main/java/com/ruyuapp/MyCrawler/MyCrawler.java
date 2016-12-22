package com.ruyuapp.MyCrawler;

import com.ruyuapp.Bean.IpPort;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gaorui on 16/12/22.
 */
public class MyCrawler extends WebCrawler{


        public static  Set<IpPort> ips = new HashSet<IpPort>();

        private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
                + "|png|mp3|mp3|zip|gz))$");

        /**
         * This method receives two parameters. The first parameter is the page
         * in which we have discovered this new url and the second parameter is
         * the new url. You should implement this function to specify whether
         * the given url should be crawled or not (based on your crawling logic).
         * In this example, we are instructing the crawler to ignore urls that
         * have css, js, git, ... extensions and to only accept urls that start
         * with "http://www.ics.uci.edu/". In this case, we didn't need the
         * referringPage parameter to make the decision.
         */
        @Override
        public boolean shouldVisit(Page referringPage, WebURL url) {
            String href = url.getURL().toLowerCase();
            return !FILTERS.matcher(href).matches()
                    && href.startsWith("http://www.youdaili.net/Daili/QQ/25410.html");
        }

        /**
         * This function is called when a page is fetched and ready
         * to be processed by your program.
         */
        @Override
        public void visit(Page page) {
            String url = page.getWebURL().getURL();
            System.out.println("URL: " + url);

            if (page.getParseData() instanceof HtmlParseData) {
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                String text = htmlParseData.getText();
                String html = htmlParseData.getHtml();
                Set<WebURL> links = htmlParseData.getOutgoingUrls();
//

                System.out.println(html);
                Pattern p = Pattern.compile("<span style=\"font-family: 微软雅黑, &#39;Microsoft YaHei&#39;; font-size: 14px;\">(.*)@");

                Matcher m = p.matcher(html);

                while(m.find()) {
                    IpPort ip = new IpPort();
                    ip.setIp( m.group(1).substring(0, m.group(1).indexOf("@")).split(":")[0]);
                    ip.setIp(m.group(1).substring(0, m.group(1).indexOf("@")).split(":")[1]);
                    System.out.println(m.group(1).substring(0, m.group(1).indexOf("@")).split(":")[0]+":"+m.group(1).substring(0, m.group(1).indexOf("@")).split(":")[1]);
                    ips.add(ip);

                }


                System.out.println("爬取完毕");



               // System.out.println("Html length: "+html );
                //System.out.println("Number of outgoing links: " + links.size());
            }
        }

}
