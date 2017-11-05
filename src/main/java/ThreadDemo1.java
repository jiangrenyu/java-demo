import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by 姜仁雨 on 2017/6/25.
 */
public class ThreadDemo1 {
    public static void main(String args[]){
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future future = threadPool.submit(new Callable<Integer>(){
             public Integer call() throws Exception{
               return new Random().nextInt(100);
            }
        });
        try{
            Thread.sleep(1000);
            System.out.println(future.get());
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("程序异常");
        }
    }
}
