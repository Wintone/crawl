import java.io.File;

public class ProduceUrl implements Runnable{
    // 如果要更换运行环境，请注意exePath最后的phantom.exe需要更改。因为这个只能在window版本上运行。前面的路径名
    // 也需要和exePath里面的保持一致。否则无法调用
    private static String projectPath = System.getProperty("user.dir");
    private static String jsPath = projectPath + File.separator + "crawl.js";
    private static String exePath = "E:" + File.separator + "phantomjs" + File.separator + "bin" + File.separator
            + "phantomjs.exe";

    public ProduceUrl(){

    }
    public void run() {

    }
}
