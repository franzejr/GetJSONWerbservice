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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class GetJSONWebservice extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
    	ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        
        
        JSONObject json = JSONFunctions.getJSONfromURL("http://lia.ufc.br/~franzejr/friends.json");
                
        try{
        	
        	JSONArray  friends = json.getJSONArray("data");
        	
	        for(int i=0;i<friends.length();i++){						
				HashMap<String, String> map = new HashMap<String, String>();	
				JSONObject e = friends.getJSONObject(i);
				
				map.put("id",  String.valueOf(i));
	        	map.put("name", "name:" + e.getString("name"));
	        	Log.i("name",e.getString("name"));
	        	mylist.add(map);			
			}
	        Log.i("text", mylist.toString());
        }catch(JSONException e)        {
        	 Log.e("log_tag", "Error parsing data "+e.toString());
        }
        

    }
        
}