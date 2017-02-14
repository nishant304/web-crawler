package root;

import client.Task;
import datastorage.FileBasedDataStore;
import util.AppUtil;
import util.Url;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;

/**
 * Created by nishant on 3/12/2016.
 */
public class UrlFinder implements Task.IStreamLoadListener {

    private final BlockingQueue<String> queue;

    public UrlFinder(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    /***
     * * @param is
     * @throws IOException
     * parse and find new urls
     */
    @Override
    public void onNewStream(InputStream is) throws IOException {
        Reader reader= new InputStreamReader(is);
        new ParserDelegator().parse(reader, new Page(), true);
    }

    /***
     * @param url
     * finished processing this url ,save to database
     */
    @Override
    public void onParseFinished(String url) {
        FileBasedDataStore.getsInstance().addUrl(url);
    }

    class Page extends HTMLEditorKit.ParserCallback {

        public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {

            if (t == HTML.Tag.A) {
                String link = null;
                Enumeration<?> attributeNames = a.getAttributeNames();
                if (attributeNames.nextElement().equals(HTML.Attribute.HREF)) {
                    link = a.getAttribute(HTML.Attribute.HREF).toString();
                    if(AppUtil.isValidUrl(link)&& !Url.isInvalidScheme(link)) {
                        queue.add(link);
                    }
                }
            }

        }

    }
}
