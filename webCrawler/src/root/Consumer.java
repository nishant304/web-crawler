package root;


import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

/**
 * Created by nishant on 3/12/2016.
 */
public class Consumer extends AbstractConsumer<String> {


    private final HashMap<String, Integer> hm;

    public Consumer(BlockingQueue<String> queue) {
        super(queue);
        hm = new HashMap<>();
    }

    @Override
    protected void onNewItemFetched(String url) {
        if(url!=null&&hm.get(url)==null) {
            hm.put(url,0);
            System.out.println(url + " ");
            notify(url);
        }
    }

}
