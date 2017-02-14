package client;

import util.AppUtil;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.*;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by nishant on 3/12/2016.
 *
 *  used to make a http get request
 *  interface is used to keep things decoupled ,and this class can be used for making generic http request
 */

public  class Task implements Runnable  {

    private final String url;
    private IStreamLoadListener listener;

    public Task(String url) {
        if(!AppUtil.isValidUrl(url)){
            throw  new IllegalArgumentException("url provided is not valid");
        }
        this.url = url;
    }

    /**
     * fire a get request
     */

    @Override
    public void run() {
        if(Thread.interrupted()){
            return;
        }
        try {
            URL ur = new URL(url);
            InputStream is = (InputStream) ur.getContent();
            if(listener!=null){
                listener.onNewStream(is);
                listener.onParseFinished(url);
            }
        } catch (IOException e) {
            System.out.println("unable to process  "+ url + " reason  :" + e.getMessage());
            listener.onParseFinished(url);
        }
    }


    public  void  setStreamListner(IStreamLoadListener listener){
        this.listener = listener;
    }

    public String getUrl() {
        return url;
    }

    public  interface  IStreamLoadListener{
        void  onNewStream(InputStream is) throws IOException;
        void  onParseFinished(String url);
    }

}
