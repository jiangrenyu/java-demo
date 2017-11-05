import java.rmi.server.ExportException;
import java.util.concurrent.*;

/**
 * Created by 姜仁雨 on 2017/6/25.
 */
public class ThreadDemo2 {
    public static void main(String aths[]){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
        for(int i=0;i<5;i++){
              final int taskId = i;
              cs.submit(new Callable<Integer>() {
                  public Integer call() throws Exception {
                      return taskId;
                  }
              });
        }
        for(int i=0;i<5;i++){
            try{
              System.out.println(cs.take().get());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
