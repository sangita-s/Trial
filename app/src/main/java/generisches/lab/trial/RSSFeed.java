package generisches.lab.trial;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RSSFeed extends ListActivity {

    List headline, links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MyAsyncTask().execute();
    }

    class MyAsyncTask extends AsyncTask<Object, Void, ArrayAdapter> {

        @Override
        protected ArrayAdapter doInBackground(Object... params) {
            headline = new ArrayList();
            links = new ArrayList();
            try {
                URL url = new URL("http://feeds.bbci.co.uk/news/world/rss.xml");

                //create implementations of XML Pull Parser
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                //Indicates whether or not the factory is configured to produce parsers which are namespace aware
                factory.setNamespaceAware(false);
                //XML Pull Parser is an interface that defines parsing functionality
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(getInputStream(url), "UTF_8");
                boolean InsideItem = false;

                //The current event state of the parser can be determined by calling the getEventType() method.
                //Initially, the parser is in the START_DOCUMENT state.
                int event = xpp.getEventType();

                //END_DOCUMENT indicates that no more events are available
                while (event != XmlPullParser.END_DOCUMENT) {
                    //An XML start tag was read.
                    //TEXT tag - Text content was read; the text content can be retrieved using the getText() method.
                    //(when in validating mode next() will not report ignorable whitespace, use nextToken() instead)
                    //Start tag event number is 0
                    if (event == XmlPullParser.START_TAG) {
                        //For START_TAG or END_TAG events, the (local) name of the current element is returned when namespaces are enabled
                        //When namespace processing is disabled, the raw name is returned.
                        if (xpp.getName().equalsIgnoreCase("item"))
                            //An item has title, description, link, guid, pub date and media thumbnail
                            InsideItem = true;
                        else if (xpp.getName().equalsIgnoreCase("title")) {
                            //title - start tag detected
                            if (InsideItem) {
                                String s = xpp.nextText();
                                headline.add(s);
                                Log.d("String is ", s);
                            }
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (InsideItem) {
                                links.add(xpp.nextText());
                            }
                        }
                    }
                    //End Tag read
                    else if (event == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        InsideItem = false;
                    }
                    event = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayAdapter result) {
            result = new ArrayAdapter(RSSFeed.this, android.R.layout.simple_list_item_1, headline);
            setListAdapter(result);
        }
    }

    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Uri uri = Uri.parse(links.get(position).toString());
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }
}