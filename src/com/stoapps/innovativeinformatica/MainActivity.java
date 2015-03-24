package com.stoapps.innovativeinformatica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	
	ImageView image1, image2, image3,image4;
	Animation animationSlideInLeft, animationSlideOutRight;
	ImageView curSlidingImage,imgContact,imgAbtUs,imgDock,imgTech;
	//ImageView btnContact,btnAboutUs,btnDock,btnTechnology;
	private ViewFlipper mViewFlipper;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(this.getResources().getColor(R.color.blue));*/
		setContentView(R.layout.activity_main);
		/*btnContact = (ImageView)findViewById(R.id.btnContact);
		 btnAboutUs = (ImageView)findViewById(R.id.btnAboutUs);
		 btnDock = (ImageView)findViewById(R.id.btnSendProtien);
		 btnTechnology = (ImageView)findViewById(R.id.btnTechnology);*/
		imgAbtUs = (ImageView)findViewById(R.id.imgAboutUs);
		imgDock = (ImageView)findViewById(R.id.imgDock);
		imgContact = (ImageView)findViewById(R.id.imgContact);
		imgTech = (ImageView)findViewById(R.id.imgTechnology);
		 mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
			mViewFlipper.setAutoStart(true);
			mViewFlipper.setFlipInterval(4000);
			mViewFlipper.startFlipping();
			
			imgTech.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent technologyIntent = new Intent(MainActivity.this,TechnologyActivity.class);
					startActivity(technologyIntent);
				}
			});
	        
			imgContact.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(MainActivity.this,SendMailActivity.class);
					startActivity(i);
				}
			});
	        
			imgAbtUs.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(MainActivity.this,AboutUSActivity.class);
					startActivity(i);
				}
			});
	        
			imgDock.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(MainActivity.this,MoleculeActivity.class);
					startActivity(i);
				}
			});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mViewFlipper.setAutoStart(true);
		mViewFlipper.setFlipInterval(4000);
		mViewFlipper.startFlipping();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		mViewFlipper.setAutoStart(true);
		mViewFlipper.setFlipInterval(4000);
		mViewFlipper.startFlipping();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
