package br.ufc.engsoft;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class GetJSONWebservice extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
    	ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        
        //URL JSON
        String json = JSONFunctions.getJSONfromURL("http://wiki.dc.ufc.br/mediawiki/api.php?action=parse&format=json&page=Matem%C3%A1tica_Discreta&prop=text");
                
        try{
        	JSONObject json1 = new JSONObject(json);
        	String title = (String)json1.getJSONObject("parse").getString("title");
        	String text = (String)json1.getJSONObject("parse").getJSONObject("text").getString("*");
        	Log.i("title",title);
        	Log.i("text",text);
        	Log.i("json todo",json1.toString());
        	
        	TextView text_title =  (TextView)findViewById(R.id.title);
        	TextView text_text =  (TextView)findViewById(R.id.text);
        	
        	text_title.setText(Html.fromHtml(title));
        	text_text.setText(Html.fromHtml(text));
        	
	     
        }catch(JSONException e)        {
        	 Log.e("log_tag", "Error parsing data "+e.toString());
        }
        

    }
        
}