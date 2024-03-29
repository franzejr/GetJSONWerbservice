package br.ufc.engsoft.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.ufc.engsoft.beans.Post;

public class JSONFunctions {

	public static String getJSONfromURL(String url) {
		InputStream is = null;
		String result = "";
		JSONObject jArray = null;

		// http post
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		try {

			jArray = new JSONObject(result);
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		Log.i("result", result);

		return result;
	}

	public static Post getPost(String url) {
		String json = JSONFunctions.getJSONfromURL(url);
		String title = "";
		String text = "";
		try {
			JSONObject json1 = new JSONObject(json);
			title = (String) json1.getJSONObject("parse").getString("title");
			text = (String) json1.getJSONObject("parse").getJSONObject("text")
					.getString("*");

		} catch (Exception e) {
			System.out.println("Erro na hora de parsear:   "
					+ e.getStackTrace());
		}

		Post p = new Post(title,text);

		return p;

	}
}
