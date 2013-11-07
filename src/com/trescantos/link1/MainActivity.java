package com.trescantos.link1;

import org.jsoup.nodes.Document;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.trescantos.link2.R;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView webView = (WebView) findViewById(R.id.webView);
		
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);
		webView.setWebViewClient(new MyViewClient());
		//webView.setWebChromeClient(new WebChromeClient());
		
//		webView.loadUrl("file:///android_asset/www/index.html");
		 String htmlcode = ""; 
		 Document doc;

	        try {
	        	 
	        	AsyncTask<String, Void, String> execute = new RetreiveFeedTask().execute("http://www.trescantos.es/web/component/k2/itemlist");
	            htmlcode = execute.get();
	            webView.loadDataWithBaseURL("http://www.trescantos.es/",
	                    htmlcode, "text/html", "UTF-8", null);
//	            webView.loadData(htmlcode, "text/html", "UTF-8");

	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
