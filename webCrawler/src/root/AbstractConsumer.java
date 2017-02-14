package root;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.BlockingQueue;

/**
 * Created by nishant on 3/12/2016.
 *
 *  a generic consumer for producer-consumer,must be extended
 *  had to put observer here as  it cant be put in sub class
 */

public abstract class AbstractConsumer<T> extends Observable implements  Runnable {

    private final BlockingQueue<T> queue;
    private Observer observer;

    public AbstractConsumer(BlockingQueue<T> queue) {
        if(queue==null){
            throw  new RuntimeException("queue should not be null");
        }
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                T t=   queue.poll();
                onNewItemFetched(t);
            }catch (Exception e){

            }
        }
    }

    protected  void  notify(T t){
         if(observer!=null){
             observer.update(this,t);
         }
    }

    public  void setObserver(Observer listener){
        this.observer = listener;
    }

    /***
     * prevent memory leak
     */
    public  void  removeObserver(){
        observer =null;
    }

    protected  abstract  void onNewItemFetched(T t);

}
