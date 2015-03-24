package com.stoapps.innovativeinformatica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.w3c.dom.Document;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.stoapps.innovativeinformatica.jsonparser.GMapV2GetRouteDirection;
import com.stoapps.innovativeinformatica.jsonparser.HttpConnection;
import com.stoapps.innovativeinformatica.jsonparser.PathJSONParser;

public class MapActivity extends Activity implements android.location.LocationListener {
	
	GoogleMap googleMap;
	LocationManager locationManager;
	LocationListener locationListener;
	Location l;
	double currentLatitude,currentLongitude;
	double latitude = 17.508159;
    double longitude = 78.374468;
    String provider = "";
    Document document;
    GMapV2GetRouteDirection v2GetRouteDirection;
    LatLng fromPosition;
    LatLng toPosition;
    GoogleMap mGoogleMap;
    MarkerOptions markerOptions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(this.getResources().getColor(R.color.blue));*/
		setContentView(R.layout.activity_map);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		Criteria c = new Criteria();
		provider = locationManager.getBestProvider(c, false);
		l = locationManager.getLastKnownLocation(provider); 
		fromPosition = new LatLng(currentLatitude, currentLongitude);
        toPosition = new LatLng(latitude, longitude);
		if(l != null){
			currentLatitude = l.getLatitude();
			currentLongitude = l.getLongitude();
		}else{
			Toast.makeText(getApplicationContext(), "No provider", Toast.LENGTH_LONG).show();
		}
		try{
			initilizeMap();
		}catch(Exception e){
			
		}
	}
	
	private void initilizeMap() {
        if (mGoogleMap == null) {
        	mGoogleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
            
            MarkerOptions marker[] = {new MarkerOptions().position(new LatLng(latitude, longitude)).title("Innovative Informatica"),new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Your Location")};
            /*MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Innovative Informatica");
            marker = new Marker*/
            for(int i=0;i<marker.length;i++){
            	if(i == 0){
            		
            		marker[i].icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            	}
            	if(i == 1){
            		marker[i].icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            	}
            	mGoogleMap.addMarker(marker[i]);
            }
            
        	mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
            mGoogleMap.getUiSettings().setCompassEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            mGoogleMap.getUiSettings().setAllGesturesEnabled(true);
            mGoogleMap.setTrafficEnabled(true);
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
            //markerOptions = new MarkerOptions();
          
            /*GetRouteTask getRoute = new GetRouteTask();
            getRoute.execute();*/
            
         // ROSE color icon
            
            // check if map is created successfully or not
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    new LatLng(17.508159, 78.374468)).zoom(12).build();
            mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            
            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            if (mGoogleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
	
	@Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
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

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
 		currentLatitude = location.getLatitude();
		currentLongitude = location.getLongitude();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	
	 private class GetRouteTask extends AsyncTask<String, Void, String> {
         
         private ProgressDialog Dialog;
         String response = "";
         @Override
         protected void onPreExecute() {
               Dialog = new ProgressDialog(MapActivity.this);
               Dialog.setMessage("Loading route...");
               Dialog.show();
         }

         @Override
         protected String doInBackground(String... urls) {
               //Get All Route values
                     document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2GetRouteDirection.MODE_DRIVING);
                     response = "Success";
               return response;

         }

         @Override
         protected void onPostExecute(String result) {
               mGoogleMap.clear();
               if(response.equalsIgnoreCase("Success")){
               ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
               PolylineOptions rectLine = new PolylineOptions().width(10).color(
                           Color.RED);

               for (int i = 0; i < directionPoint.size(); i++) {
                     rectLine.add(directionPoint.get(i));
               }
               // Adding route on the map
               mGoogleMap.addPolyline(rectLine);
               markerOptions.position(toPosition);
               markerOptions.draggable(true);
               mGoogleMap.addMarker(markerOptions);

               }
              
               Dialog.dismiss();
         }
   }
   @Override
   protected void onStop() {
         super.onStop();
         finish();
   }

}
