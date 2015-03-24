package com.stoapps.innovativeinformatica;

import java.io.File;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class SendMailActivity extends Activity {
	
	EditText edtMail,edtSub,edtMessage;
	Button btnSend;
	ImageView imgMap,imgPhone,imgMail,imgWeb,imgGplus,imgLinkedIn,imgFacebook;
	String filePath = "";
	Uri URI = null;
	int columnIndex;
	TextView txtWebAddress,txtSendEmail,txtPhone,txtAddress;
	String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	
	private static final int PICKFILE_RESULT_CODE = 1;
	private static final int MAIL_SENT_RESULT_CODE = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(this.getResources().getColor(R.color.blue));*/
		setContentView(R.layout.activity_send_mail);
		edtMail = (EditText)findViewById(R.id.edtMailID);
		edtSub = (EditText)findViewById(R.id.edtSubject);
		edtMessage = (EditText)findViewById(R.id.edtMail);
		btnSend = (Button)findViewById(R.id.btnSend);
		imgMap = (ImageView)findViewById(R.id.imgPinpoint);
		imgPhone = (ImageView)findViewById(R.id.imgPhone);
		imgMail = (ImageView)findViewById(R.id.imgSendMail);
		imgWeb = (ImageView)findViewById(R.id.imgWebAddress);
		imgGplus = (ImageView)findViewById(R.id.imgGooglePlus);
		imgLinkedIn = (ImageView)findViewById(R.id.imgLinkedIn);
		imgFacebook = (ImageView)findViewById(R.id.imgFacebook);
		txtAddress = (TextView)findViewById(R.id.txtAddress);
		txtPhone = (TextView)findViewById(R.id.txtPhone);
		txtSendEmail = (TextView)findViewById(R.id.txtSendEmail);
		txtWebAddress = (TextView)findViewById(R.id.txtWebAddress);
		
		txtAddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mapIntent = new Intent(SendMailActivity.this,MapActivity.class);
				startActivity(mapIntent);
			}
		});
		
		txtPhone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String uri = "tel:"+"+91 9177247605";
				Intent callIntent = new Intent(Intent.ACTION_DIAL);
				callIntent.setData(Uri.parse(uri));
				startActivity(callIntent);
			}
		});
		
		txtSendEmail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent sMail = new Intent(Intent.ACTION_SEND);
				sMail.setType("text/plain");
				sMail.putExtra(Intent.EXTRA_EMAIL,new String[]{ "consultinnovativeinformatica@gmail.com","info@innovativeinformatica.org" });
				sMail.putExtra(Intent.EXTRA_SUBJECT, edtSub.getText());
				sMail.putExtra(Intent.EXTRA_TEXT, edtMessage.getText());
				startActivityForResult(Intent.createChooser(sMail, "Choose a client"),MAIL_SENT_RESULT_CODE);
			}
		});
		
		txtWebAddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent webIntent = new Intent(SendMailActivity.this,WebActivity.class);
				webIntent.putExtra("integer", 1);
				webIntent.putExtra("loadUrl", "http://www.innovativeinformatica.org");
				startActivity(webIntent);
			}
		});
		
		imgFacebook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String facebookUrl = "https://www.facebook.com/innovativeinformaticatechnologies";
				try {
				    int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
				    if (versionCode >= 3002850) {
				        Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
				        startActivity(new Intent(Intent.ACTION_VIEW, uri));;
				    } else {
				        // open the Facebook app using the old method (fb://profile/id or fb://page/id)
				        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/462183570567090")));
				    }
				} catch (PackageManager.NameNotFoundException e) {
				    // Facebook is not installed. Open the browser
					Intent webIntent = new Intent(SendMailActivity.this,WebActivity.class);
					webIntent.putExtra("integer", 3);
					webIntent.putExtra("loadFB", facebookUrl);
					startActivity(webIntent);
				   // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
				}
			}
			
		});
		
		imgLinkedIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
				Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("linkedin://innovativeinformatica"));
				startActivity(i);
				}catch(Exception e){
					Intent i = new Intent(SendMailActivity.this,WebActivity.class);
					i.putExtra("linkedInUrl", "http://in.linkedin.com/in/innovativeinformatica");
					i.putExtra("integer", 2);
					//Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://in.linkedin.com/in/innovativeinformatica"));
					startActivity(i);
				}
			}
		});
		
		imgGplus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//loadGooglePlus();
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/108353104097049618907/posts")));
			}
		});
		
		imgMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mapIntent = new Intent(SendMailActivity.this,MapActivity.class);
				startActivity(mapIntent);
			}
		});
		
		imgPhone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String uri = "tel:"+"+91 9177247605";
				Intent callIntent = new Intent(Intent.ACTION_DIAL);
				callIntent.setData(Uri.parse(uri));
				startActivity(callIntent);
			}
		});
		
		imgMail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent sMail = new Intent(Intent.ACTION_SEND);
				sMail.setType("text/plain");
				sMail.putExtra(Intent.EXTRA_EMAIL,new String[]{ "consultinnovativeinformatica@gmail.com","info@innovativeinformatica.org" });
				sMail.putExtra(Intent.EXTRA_SUBJECT, edtSub.getText().toString());
				sMail.putExtra(Intent.EXTRA_TEXT, edtMessage.getText().toString());
				startActivityForResult(Intent.createChooser(sMail, "Choose a client"),MAIL_SENT_RESULT_CODE);
			}
		});
		
		imgWeb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent webIntent = new Intent(SendMailActivity.this,WebActivity.class);
				webIntent.putExtra("integer", 1);
				webIntent.putExtra("loadUrl", "http://www.innovativeinformatica.org");
				startActivity(webIntent);
			}
		});
		
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*if(edtMail.getText().toString().matches("")){
					edtMail.setError("Please type your email id");
					edtMail.setFocusable(true);
					return;*/
				if(validation() == false){
					return;
				}else{
					Intent sendEmail = new Intent(Intent.ACTION_SEND_MULTIPLE);
					sendEmail.setType("text/plain");
					sendEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{ "consultinnovativeinformatica@gmail.com","info@innovativeinformatica.org" });
					sendEmail.putExtra(Intent.EXTRA_SUBJECT, edtSub.getText().toString());
					sendEmail.putExtra(Intent.EXTRA_TEXT, edtMessage.getText().toString());
					startActivityForResult(Intent.createChooser(sendEmail, "Choose a client"),MAIL_SENT_RESULT_CODE);
					clearFields();
				}
			}
		});
		
		/*imgg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String facebookUrl = "https://www.facebook.com/JRummyApps";
				try {
				    int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
				    if (versionCode >= 3002850) {
				        Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
				        startActivity(new Intent(Intent.ACTION_VIEW, uri));;
				    } else {
				        // open the Facebook app using the old method (fb://profile/id or fb://page/id)
				        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/336227679757310")));
				    }
				} catch (PackageManager.NameNotFoundException e) {
				    // Facebook is not installed. Open the browser
				    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
				}
			}
		});*/
		
	}
	
	public boolean emailValidator(String email) 
	{
	    Pattern pattern;
	    Matcher matcher;
	    final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	    pattern = Pattern.compile(EMAIL_PATTERN);
	    matcher = pattern.matcher(email);
	    return matcher.matches();
	}
	
	public void clearFields(){
		edtMail.setText("");
		edtSub.setText("");
		edtMessage.setText("");
	}
	
	private boolean validation(){
		String email = edtMail.getText().toString();
		boolean emailValid = emailValidator(email);
		if(edtMail.getText().length() == 0){
			edtMail.setError("Please enter your email id.");
			edtMail.requestFocus();
		}else if(emailValid == false){
			edtMail.setError("Please enter valid email");
			edtMail.requestFocus();
		}else if(edtSub.getText().length() == 0){
			edtSub.setError("Please enter subject");
			edtSub.requestFocus();
		}else if(edtMessage.getText().length() == 0){
			edtMessage.setError("Please enter your message here");
			edtMessage.requestFocus();
			
		}else{
			return true;
		}
		
		return false;
	}
	
	public static Intent getOpenFacebookIntent(Context context) {

		   try {
		    context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
		    return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/37826501219"));
		   } catch (Exception e) {
		    return new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/MEGASTARCHIRANJEEVI"));
		   }
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_mail, menu);
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
	
	public boolean isGooglePlusInstalled()
	{
	    try
	    {
	        getPackageManager().getApplicationInfo("com.google.android.apps.plus", 0 );
	        return true;
	    } 
	    catch(PackageManager.NameNotFoundException e)
	    {
	        return false;
	    }
	}
	
	public void loadGooglePlus()
	{
	    if(isGooglePlusInstalled())
	    {
	        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
	               .setText("Hello there! This is a pic of the lazy cat")
	               .setType("image/jpeg")
	               .setStream(Uri.parse("https://plus.google.com/u/0/108353104097049618907/posts"))
	               .getIntent()
	               .setPackage("com.google.android.apps.plus");
	       startActivity(shareIntent);
	   }
	   else{
	      //Notify user
		   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/108353104097049618907/posts")));
	   }
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case PICKFILE_RESULT_CODE:
			if(resultCode == RESULT_OK){
				
			}
			break;
			
		case MAIL_SENT_RESULT_CODE :
			
			break;

		default:
			break;
		}
	}
}
