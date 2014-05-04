package com.example.automacaopalestra;

import java.util.Timer;
import java.util.TimerTask;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	RelativeLayout container;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		container = (RelativeLayout) findViewById(R.id.container);
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				AQuery aq = 
						new AQuery(MainActivity.this);
				aq.ajax(
		"http://1-dot-testeogliari.appspot.com/teste", 
		String.class,
				new AjaxCallback<String>(){
					@Override
					public void callback(String url, 
							String object, AjaxStatus status) {
						int retorno = Integer.parseInt(object.trim());
						int cor = Color.rgb(retorno, retorno, retorno);
						container.setBackgroundColor(cor);
					}
				});
			}
		};
		Timer t= new Timer();
		t.schedule(task, 0, 1500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
