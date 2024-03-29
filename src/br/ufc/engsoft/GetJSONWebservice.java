package br.ufc.engsoft;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import br.ufc.engsoft.beans.Post;
import br.ufc.engsoft.util.JSONFunctions;

public class GetJSONWebservice extends Activity {
	/** Called when the activity is first created. */
	MenuItem ed;
	MenuItem circuitos;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Pegando elementos da Tela
		TextView text_title = (TextView) findViewById(R.id.title);
		TextView text_text = (TextView) findViewById(R.id.text);

		// URL JSON
		Post p = JSONFunctions
				.getPost("http://wiki.dc.ufc.br/mediawiki/api.php?action=parse&format=json&page=C%C3%A1lculo_I&prop=text");
		
		//Setando elementos na tela
		text_title.setText(Html.fromHtml(p.getTitle()));
		text_text.setText(Html.fromHtml(p.getText()));

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		ed = menu.add("Estruturas de Dados");
		circuitos = menu.add("Circuitos Digitais");
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item == ed){
			startActivity(new Intent(GetJSONWebservice.this,EstruturasDeDados.class));
		}else if( item == circuitos){
			startActivity(new Intent(GetJSONWebservice.this,CircuitosDigitais.class));
		}
		return super.onMenuItemSelected(featureId, item);
	}

}