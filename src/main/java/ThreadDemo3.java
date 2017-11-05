import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by 姜仁雨 on 2017/6/25.
 */
public class ThreadDemo3 {
    public static void main(String args[]) throws Exception{
        ThreadDemo3 t1 = new ThreadDemo3();
        t1.count1();
        System.out.println("----------------------");
        t1.count2();

    }
    //使用阻塞容器保存每次Executor处理的结果，在后面进行统一处理
    public void count1() throws Exception{
        ExecutorService es1 = Executors.newCachedThreadPool();
        BlockingDeque <Future<Integer>> deque = new LinkedBlockingDeque<Future<Integer>>();
        for(int i=0;i<10;i++){
               Future<Integer> future = es1.submit(getTask());
               deque.add(future);
        }
        int sum = 0;
        int duqueSize = deque.size();
        for(int j=0;j<duqueSize;j++){
             sum+=deque.take().get();
        }
    }
    //使用completionService(完成服务)保持Executor处理的结果
    public void count2() throws Exception{
        ExecutorService es2 = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(es2);
        for(int i=0;i<10;i++){
            cs.submit(getTask());
        }
        int sum = 0;
        for(int j=0;j<10;j++){
           //检索并移除下一个已经完成任务的Future，如果不存在这样的任务，则等待
            Future<Integer> future = cs.take();
            sum =+future.get();
        }
    }
    //得到一个任务
    public Callable<Integer> getTask(){
        final Random random = new Random();
        Callable<Integer> callable = new Callable<Integer>(){
            public Integer call(){
                int i = random.nextInt(10);
                int j = random.nextInt(10);
                int sum = i*j;
                System.out.println("乘积sum: " + sum);
                return sum;
            }
        };
        return callable;
    }
}
