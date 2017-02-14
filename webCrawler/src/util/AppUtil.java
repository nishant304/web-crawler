package util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nishant on 3/12/2016.
 */
public final class AppUtil {

    public static boolean isValidUrl(String url) {
        if(url==null) {
            return  false;
        }
        try {
            URL ur = new URL(url);
        }catch (MalformedURLException  ex){
            return  false;
        }
        return true;
    }
}
