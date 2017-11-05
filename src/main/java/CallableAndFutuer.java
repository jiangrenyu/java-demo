import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/**
 * Created by 姜仁雨 on 2017/6/25.
 */
public class CallableAndFutuer {
     public static void main(String args[]) {

         Callable <Integer> callable = new Callable<Integer>(){
              public Integer call() throws Exception{
                  return new Random().nextInt(100);
              }
         };

         FutureTask futuerTask = new FutureTask(callable);
         new Thread(futuerTask).start();
         try{
             Thread.sleep(1000);
             System.out.println(futuerTask.get());
         }catch(Exception e){
             e.printStackTrace();
             System.out.println("程序异常");
         }



     }
}
