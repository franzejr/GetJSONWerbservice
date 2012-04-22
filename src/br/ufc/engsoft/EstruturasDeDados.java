package br.ufc.engsoft;

import br.ufc.engsoft.beans.Post;
import br.ufc.engsoft.util.JSONFunctions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class EstruturasDeDados extends Activity {

	MenuItem main;
	MenuItem circuitos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.estruturas);

		// Pegando elementos da Tela
		TextView text_title = (TextView) findViewById(R.id.title);
		TextView text_text = (TextView) findViewById(R.id.text);

		// URL JSON
		Post p = JSONFunctions
				.getPost("http://wiki.dc.ufc.br/mediawiki/api.php?action=parse&format=json&page=Estruturas_de_Dados&prop=text");

		// Setando elementos na tela
		text_title.setText(Html.fromHtml(p.getTitle()));
		text_text.setText(Html.fromHtml(p.getText()));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		main = menu.add("Calculo I");
		circuitos = menu.add("Circuitos Digitais");
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item == main){
			startActivity(new Intent(EstruturasDeDados.this,GetJSONWebservice.class));
		}else if( item == circuitos){
			startActivity(new Intent(EstruturasDeDados.this,CircuitosDigitais.class));
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
