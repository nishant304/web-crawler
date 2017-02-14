package root;

import client.Task;
import datastorage.UrlTracker;
import pool.Executor;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by nishant on 3/12/2016.
 */
public class WebCrawler {

    private static final String MAIN_URL = "http://telenor.com";

    public  static  void  main(String [] args){
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        consumer.setObserver(producer);
        if(UrlTracker.getsInstance().isFileEmpty()) {
            producer.processNewUrl(MAIN_URL);
        }else {
            producer.resumeOperation();
        }
        new  Thread(consumer).start();
        Runtime.getRuntime().addShutdownHook(new Thread(new Hook()));
    }


    private static class  Hook implements  Runnable{
        @Override
        public void run() {
           LinkedBlockingQueue<Runnable> q=  Executor.getQueue();
            Iterator<Runnable> it = q.iterator();
            while (it.hasNext()){
                Runnable runnable = it.next();
                if(runnable instanceof Task){
                    Task task = (Task) runnable;
                    String url = task.getUrl();
                    UrlTracker.getsInstance().addUrl(url);
                }
            }

        }
    }

}
