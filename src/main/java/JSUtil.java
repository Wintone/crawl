import jdk.internal.org.xml.sax.SAXException;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class JSUtil
{

    // 如果要更换运行环境，请注意exePath最后的phantom.exe需要更改。因为这个只能在window版本上运行。前面的路径名
    // 也需要和exePath里面的保持一致。否则无法调用
    private static String projectPath = System.getProperty("user.dir");
    private static String jsPath = projectPath + File.separator + "crawl.js";
    private static String exePath = "E:" + File.separator + "phantomjs" + File.separator + "bin" + File.separator
            + "phantomjs.exe";
    private static String REGEX_CHINESE = "[^\u4e00-\u9fa5]";// 中文正则
    private LinkedBlockingQueue<String> produceQueue = new LinkedBlockingQueue<String>();
    private Set<String> cosumeSet = new HashSet<String>();

    public static void main(String[] args) throws IOException, SAXException
    {

        new Thread(new ProduceUrl()).start();
        new Thread(new ConsumeUrl()).start();
        System.out.println(projectPath);
        // 测试调用。传入url即可
        String html = getParseredHtml2("http://www-dev.ctyun.cn/home/");
        System.out.println("html: " + html);
    }

    // 调用phantomjs程序，并传入js文件，并通过流拿回需要的数据。
    public static String getParseredHtml2(String url) throws IOException
    {
        //取得当前JVM的运行时环境,Singleton的
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec(exePath + " " + jsPath + " " + url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String tmp = "";
        while ((tmp = br.readLine()) != null)
        {
            sbf.append(tmp);
        }
        System.out.println(sbf.toString());
        return sbf.toString();
    }

}