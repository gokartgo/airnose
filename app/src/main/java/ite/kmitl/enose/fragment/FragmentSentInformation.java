package ite.kmitl.enose.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import ite.kmitl.enose.R;


@SuppressWarnings("unused")
public class FragmentSentInformation extends Fragment implements OnMapReadyCallback
        , GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener
        , LocationListener {

    public interface FragmentListener {
        void onSendInformationClick();
    }

    // connect firebase
    FirebaseDatabase database;
    DatabaseReference databaseRef;

    Geocoder geocoder;
    private GoogleMap mMap;
    private LocationRequest mLocationRequest;
    Location mLastLocation;
    private MapView mapView;
    private static final String TAG = "LocationDemo";
    private GoogleApiClient googleApiClient;
    // location user
    double latitude;
    double longitude;
    LatLng latLng;
    List<Address> addressList;
    String strLatLng = "-";
    String valueLatLng = "-";
    MarkerOptions markerUser;
    Marker mCurrLocationMarker;
    int checkStatusLocation = 0;

    //sent information
    String information = "";
    String[] sentInfor;
    String smell, comment, duration, time, level, location, date;
    String sentInformation;
    TextView tvTypeSmell;
    TextView tvComment;
    TextView tvTime;
    TextView tvDurationSmell;
    TextView tvSmellLevel;
    Button btnSentInformation;

    public FragmentSentInformation() {
        super();
    }

    @SuppressWarnings("unused")
    public static FragmentSentInformation newInstance(String smellDetailsTimeFeel) {
        FragmentSentInformation fragment = new FragmentSentInformation();
        Bundle args = new Bundle();
        args.putString("smellDetailsTimeFeel", smellDetailsTimeFeel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        information = getArguments().getString("smellDetailsTimeFeel");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sent_information, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.format(calendar.getTime()).toString();
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // get firebase database reference
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReferenceFromUrl("https://airnoseandroid.firebaseio.com/");

        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();
        mapView = (MapView) rootView.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
        tvTypeSmell = (TextView) rootView.findViewById(R.id.tvTypeSmell);
        tvComment = (TextView) rootView.findViewById(R.id.tvComment);
        tvTime = (TextView) rootView.findViewById(R.id.tvTime);
        tvDurationSmell = (TextView) rootView.findViewById(R.id.tvDurationSmell);
        tvSmellLevel = (TextView) rootView.findViewById(R.id.tvSmellLevel);
        setTextView();
        btnSentInformation = (Button) rootView.findViewById(R.id.btnSentInformation);
        btnSentInformation.setOnClickListener(btnSentClick);
    }

    private void setTextView() {
        sentInfor = information.split(" ");
        for (int i = 0; i < sentInfor.length; i++) {
            sentInfor[i] = sentInfor[i].replace("_", " ");
        }
        smell = sentInfor[0];
        comment = sentInfor[1];
        duration = sentInfor[2];
        time = sentInfor[3];
        level = sentInfor[4];

        tvTypeSmell.setText(smell);
        tvComment.setText(comment);
        tvDurationSmell.setText(duration);
        tvTime.setText(time);
        tvSmellLevel.setText(level);
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }


    @Override
    public void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    /**************************
     *** google map api service ***
     **************************/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);

        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            mMap.setMyLocationEnabled(true);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        statusCheck();

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
        }


    }

    /*****************
     * check gps opening or not
     */

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed:error code = "
                + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        // instantiate the class Geocoder
        latLng = new LatLng(latitude, longitude);
        geocoder = new Geocoder(getContext());
        try {
            addressList = geocoder.getFromLocation(latitude, longitude, 1);
            valueLatLng = latitude+" | "+longitude;
            strLatLng = addressList.get(0).getAdminArea();
            strLatLng += " " + addressList.get(0).getLocality();
            strLatLng += " " + addressList.get(0).getSubLocality();
            strLatLng += " " + addressList.get(0).getThoroughfare();


            markerUser = new MarkerOptions().position(latLng).title(strLatLng);
            // Create Marker
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.2f));
            // set map to click
            mMap.setOnMapClickListener(mapClick);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //stop location updates
        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }

    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(getContext().LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.gps_alert_dialog_open))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.gps_open_yes), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton(getString(R.string.gps_open_no), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    /************
     * Click Listener
     */
    final GoogleMap.OnMapClickListener mapClick = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            try {
                addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                valueLatLng = latLng.latitude+" | "+latLng.longitude;
                strLatLng = addressList.get(0).getAdminArea();
                strLatLng += " " + addressList.get(0).getLocality();
                strLatLng += " " + addressList.get(0).getSubLocality();
                strLatLng += " " + addressList.get(0).getThoroughfare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng).title(strLatLng));
        }
    };

    final View.OnClickListener btnSentClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sentInformation = "smell : " + smell + "\ncomment : " + comment +
                    "\nduration : " + duration + "\ntime : " + time + "\nlevel : " + level + "\nlocation : " + strLatLng;
            if (view == btnSentInformation) {
                Toast.makeText(getContext(), sentInformation, Toast.LENGTH_LONG).show();

                DatabaseReference id = databaseRef.push();
                id.child("comment").setValue(comment);
                id.child("duration").setValue(duration);
                id.child("level").setValue(level);
                id.child("location").setValue(valueLatLng);
                id.child("smell").setValue(smell);
                id.child("time").setValue(time);
                id.child("date").setValue(date);

                FragmentListener fragmentListener = (FragmentListener) getActivity();
                fragmentListener.onSendInformationClick();
            }
        }
    };

}
