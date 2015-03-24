package com.stoapps.innovativeinformatica;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;

public class AboutUSActivity extends Activity {
	
	ImageView imgAboutUs,imgOurMission,imgOurVision;
	WebView webViewAboutUs,webViewOurMission,webViewOurVision;
	boolean boolAboutUsVisible = false,boolOurMission = false,boolOurVision = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(this.getResources().getColor(R.color.blue));*/
		setContentView(R.layout.activity_about_us);
		imgAboutUs = (ImageView)findViewById(R.id.imgAbtUs);
		imgOurMission = (ImageView)findViewById(R.id.imgOurMission);
		imgOurVision = (ImageView)findViewById(R.id.imgOurVision);
		webViewAboutUs = (WebView)findViewById(R.id.webViewAbtUS);
		webViewOurMission = (WebView)findViewById(R.id.webViewOurMission);
		webViewOurVision = (WebView)findViewById(R.id.webViewOurVision);
		
		String textAboutUs = "<html><body>"
				                   + "<p align=\"justify\">"                
				                  + getString(R.string.txt_abt_us) 
				                   + "</p> "
				                   + "</body></html>";
		
		String textOurMission = "<html><body>"
                + "<p align=\"justify\">"                
               + getString(R.string.txt_our_mission) 
                + "</p> "
                + "</body></html>";
		
		String textOurVision = "<html><body>"
                + "<p align=\"justify\">"                
               + getString(R.string.txt_our_vision) 
                + "</p> "
                + "</body></html>";
		
		webViewAboutUs.loadData(textAboutUs, "text/html", "utf-8");
		webViewOurMission.loadData(textOurMission, "text/html", "utf-8");
		webViewOurVision.loadData(textOurVision, "text/html", "utf-8");
		
		imgAboutUs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(boolAboutUsVisible == false){
					webViewAboutUs.setVisibility(View.VISIBLE);
					boolAboutUsVisible = true;
				}else{
					webViewAboutUs.setVisibility(View.GONE);
					boolAboutUsVisible = false;
				}
			}
		});
		
		imgOurMission.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(boolOurMission == false){
					webViewOurMission.setVisibility(View.VISIBLE);
					boolOurMission = true;
				}else{
					webViewOurMission.setVisibility(View.GONE);
					boolOurMission = false;
				}
			}
		});
		
		imgOurVision.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(boolOurVision == false){
					webViewOurVision.setVisibility(View.VISIBLE);
					boolOurVision = true;
				}else{
					webViewOurVision.setVisibility(View.GONE);
					boolOurVision = false;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_u, menu);
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
}
