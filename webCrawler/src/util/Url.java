package util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nishant on 3/12/2016.
 */
public class Url {

    public static boolean isInvalidScheme(String url) {
        if(url==null){
            throw  new NullPointerException("url is null");
        }
        return url.startsWith("mailto:") || url.startsWith("javascript:") || url.startsWith("#") || url.startsWith("tel:");
    }

    public static boolean isValidUrl(String url) {
        if(url==null) {
            return  false;
        }
        try {
            URL ur = new URL(url);
        }catch (MalformedURLException ex){
            return  false;
        }
        return true;
    }


}
