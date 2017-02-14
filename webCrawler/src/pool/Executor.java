package pool;

import java.util.concurrent.*;

/**
 * Created by nishant on 3/12/2016.
 *  rejectionhandler may not be necessary as , LinkedBlockingQueue is being used
 *  but this may lead to OOM in worst case scenarios
 */

public final class Executor {

    private static  ThreadPoolExecutor threadPoolExecutor;
    private  static  BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
    static {
          threadPoolExecutor = new ThreadPoolExecutor(5,10,1,TimeUnit.SECONDS,blockingQueue);
    }

    public static void submit(Runnable runnable) {
        threadPoolExecutor.submit(runnable);
    }

    public static void submit(Callable callable) {
        threadPoolExecutor.submit(callable);
    }

    public  static  LinkedBlockingQueue getQueue(){
        return (LinkedBlockingQueue) blockingQueue;
    }


//    TODO  complete rejectedexceutionhandler and submit method

}
