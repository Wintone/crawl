
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class JSUtil
{
    protected static LinkedBlockingQueue<String> produceQueue = new LinkedBlockingQueue<String>();
    protected static Set<String> cosumeSet = new HashSet<String>();

    public static void main(String[] args) throws InterruptedException {

        produceQueue.put("http://www-dev.ctyun.cn/home/");
        new Thread(new ProduceUrl()).start();
        new Thread(new ConsumeUrl()).start();
    }

}