package datastorage;

/**
 * Created by nishant on 3/12/2016.
 *  singleton ,and bound to be called from different threads
 *  good idea to use double lock check to keep our singleton design intact
 */
public class FileBasedDataStore extends  AbstractFileStorage {

    private  static  FileBasedDataStore sInstance;

    private  FileBasedDataStore(){
        super("ProcessedUrls.txt");
    }

    public static   FileBasedDataStore getsInstance(){
        if(sInstance==null){
            synchronized (FileBasedDataStore.class){
                if(sInstance==null){
                    sInstance = new FileBasedDataStore();
                }
            }
        }
        return  sInstance;
    }


}
