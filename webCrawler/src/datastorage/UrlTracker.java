package datastorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishant on 3/13/2016.
 *  this class stores the urls which are yet to be processed  and hence in the queue
 *  of threadpool will be stored  ,the ones which are taken off the queue and has not been processed
 *  completely  is not taken care of   ...not sure of how to get hold of those items
 */
public class UrlTracker  extends  AbstractFileStorage {


    private  static  UrlTracker sInstance;


    private  UrlTracker(){
        super("Unprocessed.txt");
    }

    public  static  UrlTracker getsInstance(){
        if(sInstance==null){
            synchronized (UrlTracker.class){
                if(sInstance==null){
                    sInstance = new UrlTracker();
                }
            }
        }
        return  sInstance;
    }

    public List<String> getAllUrls() throws IOException {
        List<String> list = new ArrayList<>();
        if(isFileEmpty()) {
            return  list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }
        return  list;
    }


    public  boolean isFileEmpty(){
        return  !getFile().exists()||getFile().length()==0;
    }
}
