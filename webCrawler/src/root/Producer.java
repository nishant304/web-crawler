package root;

import client.Task;
import datastorage.UrlTracker;
import pool.Executor;
import util.AppUtil;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.BlockingQueue;

/**
 * Created by nishant on 3/12/2016.
 */
public class Producer implements Observer {

    private final BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public  void processNewUrl(String url) {
        if(!AppUtil.isValidUrl(url)) {
            return;
        }
        UrlFinder urlFinder = new UrlFinder(queue);
        Task task = new Task(url);
        task.setStreamListner(urlFinder);
        Executor.submit(task);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof  String){
            String url = (String)arg;
            processNewUrl(url);
        }
    }

    public void resumeOperation() {
        List<String> urls = null;
        try {
            urls = UrlTracker.getsInstance().getAllUrls();
            for(int i=0;i<urls.size();i++){
                processNewUrl(urls.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
