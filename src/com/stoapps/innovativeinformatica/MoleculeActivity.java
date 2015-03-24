package com.stoapps.innovativeinformatica;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stoapps.myspinner.CustomSpinner;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MoleculeActivity extends Activity {
	
	//Button btnBrowse;
	EditText edtProtien,edtLigand,edtProjectTitle,edtUserName,edtInstituteName,edtEmail,edtPhoneNumber,edtPurpose;
	CustomSpinner spinnerMoleculeData;
	CustomSpinner spinnerProtienData;
	Button submit;
	Uri filePathLigand,filePathProtien;
	boolean spinnerLigandSelected;
	boolean spinnerProtienSelected;
	String selectedLigandDBName,selectedProtienDBName;
	//String filePath = "";
	
	private String ligandFilePath,protienFilePath;
	final int FILE_CHOOSER=1;
	private static final int MAIL_SENT_RESULT_CODE = 102;
	private static final int PICKFILE_RESULT_CODE_LIGAND = 100;
	private static final int PICKFILE_RESULT_CODE_PROTIEN = 101;
	
	private String[] state = {"Select Database","Pubchem database","Zinc database","Chemspider database","Other(Please Specify)","Upload/Browse"};
	private String[] stateProtien = {"Select Database","PdbDataBase","Browse"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(this.getResources().getColor(R.color.blue));*/
		setContentView(R.layout.activity_molecule);
		//btnBrowse = (Button)findViewById(R.id.btnBrowse);
		submit = (Button)findViewById(R.id.btnSubmitMolecule);
		edtProtien = (EditText)findViewById(R.id.edtProtien);
		edtLigand = (EditText)findViewById(R.id.edtLigand);
		edtProjectTitle = (EditText)findViewById(R.id.edtProjectTitle);
		edtUserName = (EditText)findViewById(R.id.edtUserNameMolecule);
		edtInstituteName = (EditText)findViewById(R.id.edtInstituteName);
		edtEmail = (EditText)findViewById(R.id.edtEmailIDMol);
		edtPhoneNumber = (EditText)findViewById(R.id.edtPhoneNumber);
		edtPurpose = (EditText)findViewById(R.id.edtPurpose);
		spinnerMoleculeData = (CustomSpinner)findViewById(R.id.spinnerMoleculeDb);
		spinnerProtienData = (CustomSpinner)findViewById(R.id.spinnerProtienBrowse);
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinneritem,state);
		ArrayAdapter<String> adapter_state_protien = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinneritem,stateProtien);
		spinnerMoleculeData.setAdapter(adapter_state);
		spinnerProtienData.setAdapter(adapter_state_protien);
		/*spinnerProtienData.addItem("select database");
		spinnerProtienData.addItem("PdbDataBase");
		spinnerProtienData.addItem("Browse");*/
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isValid() == false){
					
				}else{
				Intent sendEmail = new Intent(Intent.ACTION_SEND_MULTIPLE);
				sendEmail.setType("text/plain");
				sendEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{ "consultinnovativeinformatica@gmail.com","info@innovativeinformatica.org" });
				sendEmail.putExtra(Intent.EXTRA_SUBJECT, edtProjectTitle.getText().toString());
				//sendEmail.putExtra(Intent.EXTRA_TEXT, edtPurpose.getText());
				sendEmail.putExtra(Intent.EXTRA_TEXT,  Html.fromHtml("<p><b>Hi,</b></p><p><b>From : "
						+edtUserName.getText().toString()+"</b></p>"
						+"<p><b>Institute Name : "+edtInstituteName.getText().toString()+"</b></p>"
						+"<p><b>EmailID : "+edtEmail.getText().toString()
						+"<p><b>Phone Number : "+edtPhoneNumber.getText().toString()
						+"<p><b>PDB ID : "+edtProtien.getText().toString()
						+"<p><b>Protien Database : "+selectedProtienDBName
						+"<p><b>Ligand ID : "+edtLigand.getText().toString()
						+"<p><b>Ligand Database : "+selectedLigandDBName
						+"</b></p>"+"<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>"+edtPurpose.getText().toString()+"</font></p>"));
				
				ArrayList<Uri> uris = new ArrayList<Uri>();
				if(spinnerLigandSelected==true&&spinnerProtienSelected==true){
					String[] filep = new String[]{ligandFilePath,protienFilePath};
					for(String f : filep){
						File fileIn = new File(f);
						Uri u = Uri.fromFile(fileIn);
						uris.add(u);
						spinnerLigandSelected = false;
						spinnerProtienSelected = false;
					}
				}else if(spinnerLigandSelected == true&&spinnerProtienSelected == false){
					String[] filep = new String[]{ligandFilePath};
					for(String f : filep){
						File fileIn = new File(f);
						Uri u = Uri.fromFile(fileIn);
						uris.add(u);
						spinnerLigandSelected = false;
					}
				}else if(spinnerLigandSelected == false&&spinnerProtienSelected == true){
					String[] filep = new String[]{protienFilePath};
					for(String f : filep){
						File fileIn = new File(f);
						Uri u = Uri.fromFile(fileIn);
						uris.add(u);
						spinnerProtienSelected = false;
					}
				}
				
				sendEmail.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
				startActivityForResult(Intent.createChooser(sendEmail, "Choose a client"),MAIL_SENT_RESULT_CODE);
				clearFields();
				}
			}
		});
		
		 
		
		spinnerProtienData.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//spinnerProtienData.setSelection(position);
				switch(position){
				case 1:
					spinnerProtienSelected = false;
					break;
				case 2:
					spinnerProtienSelected = true;
					Intent intent = new Intent(Intent.ACTION_PICK);
					 //Intent intent = new Intent(MoleculeActivity.this, FileChooserActivity.class);
					   
					//Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
					intent.setType("file/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);
					try{
						 startActivityForResult(intent, FILE_CHOOSER);
						//startActivityForResult(intent, PICKFILE_RESULT_CODE_PROTIEN);
					}catch(ActivityNotFoundException e){
						Toast.makeText(getApplicationContext(), "Please install file manager", Toast.LENGTH_LONG).show();
					}
					break;
				}
				selectedProtienDBName = (String)spinnerProtienData.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void clearFields(){
		//edtProtien,edtLigand,edtProjectTitle,edtUserName,edtInstituteName,edtEmail,edtPhoneNumber,edtPurpose
		edtProjectTitle.setText("");
		edtProtien.setText("");
		spinnerProtienData.setSelection(0);
		edtLigand.setText("");
		spinnerMoleculeData.setSelection(0);
		edtUserName.setText("");
		edtInstituteName.setText("");
		edtEmail.setText("");
		edtPhoneNumber.setText("");
		edtPurpose.setText("");
	}
	
	public boolean isValid(){
		String email = edtEmail.getText().toString();
		boolean emailValid = emailValidator(email);
		int posProtiesnSpinner = spinnerProtienData.getSelectedItemPosition();
		int posLigandSpinner = spinnerMoleculeData.getSelectedItemPosition();
		View selectedProtienSpinnerView = spinnerProtienData.getSelectedView();
		View selectedLigandSpinnerView = spinnerMoleculeData.getSelectedView();
		if(edtProjectTitle.getText().length() == 0){
			edtProjectTitle.setError("Please type project title");
			edtProjectTitle.requestFocus();
		}else if(edtProtien.getText().length() == 0){
			edtProtien.setError("Please type protien id");
			edtProtien.requestFocus();
		}else if(posProtiesnSpinner == 0){
			TextView selectedText = (TextView)selectedProtienSpinnerView;
			selectedText.setError("Please select protien database type");
			spinnerProtienData.requestFocus();
		}else if(edtLigand.getText().length() == 0){
			edtLigand.setError("Please type ligand id");
			edtLigand.requestFocus();
		}else if(posLigandSpinner == 0){
			TextView selectedLigand = (TextView)selectedLigandSpinnerView;
			selectedLigand.setError("Please select ligand database type");
			spinnerMoleculeData.requestFocus();
		}else if(edtUserName.getText().length() == 0){
			edtUserName.setError("Please type your name");
			edtUserName.requestFocus();
		}else if(edtInstituteName.getText().length() == 0){
			edtInstituteName.setError("Please type your name");
			edtInstituteName.requestFocus();
		}else if(edtEmail.getText().length() == 0){
			edtEmail.setError("Please type your name");
			edtEmail.requestFocus();
		}else if(emailValid == false){
			edtEmail.setError("Please type valid email");
			edtEmail.requestFocus();
		}else if(edtPhoneNumber.getText().length() == 0){
			edtPhoneNumber.setError("Please type your name");
			edtPhoneNumber.requestFocus();
		}else if(edtPurpose.getText().length() == 0){
			edtPurpose.setError("Please type your name");
			edtPurpose.requestFocus();
		}else{
			return true;
		}
		
		return false;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.molecule, menu);
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
	
	public static String getPath(Context context, Uri uri) throws URISyntaxException {
	    if ("content".equalsIgnoreCase(uri.getScheme())) {
	        String[] projection = { "_data" };
	        Cursor cursor = null;
	        try {
	            cursor = context.getContentResolver().query(uri, projection, null, null, null);
	            int column_index = cursor.getColumnIndexOrThrow("_data");
	            if (cursor.moveToFirst()) {
	                return cursor.getString(column_index);
	            }
	        } catch (Exception e) {
	            // Eat it
	        }
	    }
	    else if ("file".equalsIgnoreCase(uri.getScheme())) {
	        return uri.getPath();
	    }

	    return null;
	} 
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		/* if ((requestCode == FILE_CHOOSER) && (resultCode == RESULT_OK)) {
		        String fileSelected = data.getStringExtra(Constants.KEY_FILE_SELECTED);
		        Toast.makeText(this, "file selected "+fileSelected, Toast.LENGTH_SHORT).show();
		    }   */
		switch(requestCode){
		
		case PICKFILE_RESULT_CODE_LIGAND :
			if(resultCode == RESULT_OK){
				filePathLigand = data.getData();
				try{
					ligandFilePath = getPath(getApplicationContext(), filePathLigand);
						edtLigand.setText(ligandFilePath);
					
				}catch(URISyntaxException e){
					e.printStackTrace();
				}
			}
			break;
			
		case PICKFILE_RESULT_CODE_PROTIEN:
			if(resultCode == RESULT_OK){
				filePathProtien = data.getData();
				try{
					protienFilePath = getPath(getApplicationContext(), filePathProtien);
						edtProtien.setText(protienFilePath);
					
				}catch(URISyntaxException e){
					e.printStackTrace();
				}
			}
			break;
		} 	 		
	}
}
