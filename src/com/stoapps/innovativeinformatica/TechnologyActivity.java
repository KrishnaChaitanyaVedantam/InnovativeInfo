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

public class TechnologyActivity extends Activity{
	
	
	ImageView imgVirtualScreen,imgMolecularDocking,imgProtien,imgFreeEnergy,
	imgMolecularDynamic,imgHomology,imgAdmet;
	
	WebView webVirtualScreen,webMolecularDocking,webProtien,webFreeEnergy,
	webMolecularDynamic,webHomology,webAdmet;
	
	boolean boolVirtualScreen=false,boolMolecularDocking=false,boolProtien=false,boolFreeEnergy=false,
	boolMolecularDynamic=false,boolWebHomology=false,boolWebAdment=false;
	
	String textVirtualScreen,textMolecularDocking,textProtien,textFreeEnergy,textMolecularDynamic,textHomology,textAdmet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(this.getResources().getColor(R.color.blue));*/
		setContentView(R.layout.activity_technology);
		imgVirtualScreen = (ImageView)findViewById(R.id.imgVirtualScreening);
		imgMolecularDocking = (ImageView)findViewById(R.id.imgMolecularDocking);
		imgProtien = (ImageView)findViewById(R.id.imgProtien);
		imgFreeEnergy = (ImageView)findViewById(R.id.imgFreeEnergy);
		imgMolecularDynamic = (ImageView)findViewById(R.id.imgMoleculeDynamic);
		imgHomology = (ImageView)findViewById(R.id.imgHomology);
		imgAdmet = (ImageView)findViewById(R.id.imgAdmet);
		
		webVirtualScreen = (WebView)findViewById(R.id.webViewVirtualScreening);
		webMolecularDocking = (WebView)findViewById(R.id.webViewMolecularDocking);
		webMolecularDynamic = (WebView)findViewById(R.id.webViewMoleculeDynamic);
		webProtien = (WebView)findViewById(R.id.webViewProtien);
		webFreeEnergy = (WebView)findViewById(R.id.webViewFreeEnergy);
		webHomology = (WebView)findViewById(R.id.webViewHomology);
		webAdmet = (WebView)findViewById(R.id.webViewAdmet);
		
		imgVirtualScreen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(boolVirtualScreen == false){
					webVirtualScreen.setVisibility(View.VISIBLE);
					boolVirtualScreen = true;
				}else{
					webVirtualScreen.setVisibility(View.GONE);
					boolVirtualScreen = false;
				}
			}
		});
		
		imgMolecularDocking.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(boolMolecularDocking == false){
					webMolecularDocking.setVisibility(View.VISIBLE);
					boolMolecularDocking = true;
				}else{
					webMolecularDocking.setVisibility(View.GONE);
					boolMolecularDocking = false;
				}
			}
		});
		
		imgProtien.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(boolProtien == false){
					webProtien.setVisibility(View.VISIBLE);
					boolProtien = true;
				}else{
					webProtien.setVisibility(View.GONE);
					boolProtien = false;
				}
			}
		});
		
		imgFreeEnergy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(boolFreeEnergy == false){
					webFreeEnergy.setVisibility(View.VISIBLE);
					boolFreeEnergy = true;
				}else{
					webFreeEnergy.setVisibility(View.GONE);
					boolFreeEnergy = false;
				}
			}
		});
		
		imgMolecularDynamic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(boolMolecularDynamic == false){
					webMolecularDynamic.setVisibility(View.VISIBLE);
					boolMolecularDynamic = true;
				}else{
					webMolecularDynamic.setVisibility(View.GONE);
					boolMolecularDynamic = false;
				}
			}
		});
		
		imgHomology.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(boolWebHomology == false){
					webHomology.setVisibility(View.VISIBLE);
					boolWebHomology = true;
				}else{
					webHomology.setVisibility(View.GONE);
					boolWebHomology = false;
				}
			}
		});
		
		imgAdmet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(boolWebAdment == false){
					webAdmet.setVisibility(View.VISIBLE);
					boolWebAdment = true;
				}else{
					webAdmet.setVisibility(View.GONE);
					boolWebAdment = false;
				}
			}
		});
		
		textVirtualScreen = "<html><body>"
	            + "<p align=\"justify\">"                
	           + getString(R.string.text_virtual_screen) 
	            + "</p> "
	            + "</body></html>";
		
		textMolecularDocking = "<html><body>"
	            + "<p align=\"justify\">"                
	           + getString(R.string.text_molecular_docking) 
	            + "</p> "
	            + "</body></html>";
		
		textProtien = "<html><body>"
	            + "<p align=\"justify\">"                
	           + getString(R.string.text_protien) 
	            + "</p> "
	            + "</body></html>";
		
		textFreeEnergy = "<html><body>"
	            + "<p align=\"justify\">"                
	           + getString(R.string.text_free_energy) 
	            + "</p> "
	            + "</body></html>";
		
		textMolecularDynamic = "<html><body>"
	            + "<p align=\"justify\">"                
	           + getString(R.string.text_molecular_dynamic) 
	            + "</p> "
	            + "</body></html>";
		
		textHomology = "<html><body>"
	            + "<p align=\"justify\">"                
	           + getString(R.string.text_homology) 
	            + "</p> "
	            + "</body></html>";
		
		textAdmet = "<html><body>"
	            + "<p align=\"justify\">"                
	           + getString(R.string.text_admet) 
	            + "</p> "
	            + "</body></html>";
		
		
		webVirtualScreen.loadData(textVirtualScreen, "text/html", "utf-8");
		webMolecularDocking.loadData(textMolecularDocking, "text/html", "utf-8");
		webProtien.loadData(textProtien, "text/html", "utf-8");
		webMolecularDynamic.loadData(textMolecularDynamic, "text/html", "utf-8");
		webHomology.loadData(textHomology, "text/html", "utf-8");
		webFreeEnergy.loadData(textFreeEnergy, "text/html", "utf-8");
		webAdmet.loadData(textAdmet, "text/html", "utf-8");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.technology, menu);
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
