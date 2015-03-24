package com.stoapps.innovativeinformatica;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;

public class WebActivity extends Activity {
	
	String url = "";
	 private ProgressDialog progDailog;
	WebView webView;
	int i = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(this.getResources().getColor(R.color.blue));*/
		setContentView(R.layout.activity_web);
		progDailog = new ProgressDialog(this);
		i = getIntent().getIntExtra("integer", 0);
		if(i == 1){
			url = getIntent().getStringExtra("loadUrl");
		}else if(i == 2){
			url = getIntent().getStringExtra("linkedInUrl");
		}else if(i == 3){
			url = getIntent().getStringExtra("loadFB");
		}
		webView = (WebView)findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setPluginState(PluginState.ON);
		webView.setWebViewClient(new myWebClient());
		webView.loadUrl(url);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class myWebClient extends WebViewClient {
	    @Override
	    public void onPageStarted(WebView view, String url, Bitmap favicon) {
	        // TODO Auto-generated method stub
	        super.onPageStarted(view, url, favicon);
	        progDailog.setMessage("Loading...");
	        progDailog.show();
	    }

	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        // TODO Auto-generated method stub
	    	
	        view.loadUrl(url);
	        
	        return true;

	    }
	    
	    @Override
	    public void onPageFinished(WebView view, String url) {
	    	// TODO Auto-generated method stub
	    	super.onPageFinished(view, url);
	    	progDailog.dismiss();
	    }
	}
}
