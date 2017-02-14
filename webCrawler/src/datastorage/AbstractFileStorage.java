package datastorage;

import interfaces.IDataStore;
import java.io.*;

/**
 * Created by nishant on 3/13/2016.
 */
public  abstract class AbstractFileStorage  implements IDataStore {


    private final String fileName;
    private final File file;

    public AbstractFileStorage(String fileName){
        this.fileName = fileName;
        file = new File(fileName);
    }


    @Override
    public synchronized void addUrl(String s) {
        FileOutputStream fs = null;
        PrintStream ps = null;
        try {
            fs = new FileOutputStream(file,true);
            ps = new PrintStream(fs);
            ps.append(s);
            ps.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fs!=null){
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ps!=null){
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void delUrl(String s) {

    }

    @Override
    public boolean isUrlExists() {
        return false;
    }


    public File getFile() {
        return file;
    }

}
