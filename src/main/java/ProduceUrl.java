import java.io.*;

public class ProduceUrl implements Runnable{
    // 如果要更换运行环境，请注意exePath最后的phantom.exe需要更改。因为这个只能在window版本上运行。前面的路径名
    // 也需要和exePath里面的保持一致。否则无法调用
    protected static String projectPath = System.getProperty("user.dir");
    protected static String jsPath = projectPath + File.separator + "crawl.js";
    protected static String exePath = "E:" + File.separator + "phantomjs" + File.separator + "bin" + File.separator
            + "phantomjs.exe";
    protected static String REGEX_CHINESE = "[^\u4e00-\u9fa5]";// 中文正则

    public ProduceUrl(){

    }
    public void run() {
        while(!JSUtil.produceQueue.isEmpty()) {
            try {
                String url = JSUtil.produceQueue.take();
                getParseredHtml2(url);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
