package com.example.android.cfmaterial;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;

/**
 * Created by ravatapa on 5/21/2015.
 */
public class RegistrationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, Handler.Callback {

    private EditText phoneNumber;
    private EditText dob;
    private EditText email;
    private EditText zipCode;

    private Button zipCodeLocationButton;
    private Button privacyPolicy;
    private Button termsOfService;
    private Button register;
    private RadioGroup radioGroup;

    private CheckBox emailAlerts;
    private CheckBox textAlerts;

    private ProgressBar locationProgress;

    private Handler handler;

    private GoogleApiClient googleApiClient;
    private Location lastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        locationProgress = (ProgressBar) findViewById(R.id.location_progress);

        phoneNumber = (EditText) findViewById(R.id.edittext_phone_number);
        dob = (EditText) findViewById(R.id.edittext_dob);
        email = (EditText) findViewById(R.id.edittext_email);
        zipCode = (EditText) findViewById(R.id.edittext_zipcode);

        zipCodeLocationButton = (Button) findViewById(R.id.zipcode_location);
        privacyPolicy = (Button) findViewById(R.id.privacy);
        termsOfService = (Button) findViewById(R.id.terms);
        register = (Button) findViewById(R.id.register);

        radioGroup = (RadioGroup) findViewById(R.id.gender_radiogroup);

        emailAlerts = (CheckBox) findViewById(R.id.checkbox_email_alerts);
        textAlerts = (CheckBox) findViewById(R.id.checkbox_text_alerts);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        zipCodeLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationProgress.setVisibility(View.VISIBLE);
                zipCodeLocationButton.setBackgroundResource(R.drawable.ic_action_location_searching);
                googleApiClient = new GoogleApiClient.Builder(RegistrationActivity.this)
                        .addApi(LocationServices.API)
                        .addConnectionCallbacks(RegistrationActivity.this)
                        .addOnConnectionFailedListener(RegistrationActivity.this)
                        .build();
                googleApiClient.connect();
            }
        });

        handler = new Handler(this);

    }

    private void registerUser(){

        String emailString = email.getText().toString();
        if (!isValidEmail(emailString)){
            email.setError(getString(R.string.email_error));
        }

        String phoneNumberString = phoneNumber.getText().toString();
        if (!isValidPhoneNumber(phoneNumberString)){
            phoneNumber.setError(getString(R.string.phone_error));
        }

        String dobString = dob.getText().toString();
        if (!isDobValid(dobString)){
            dob.setError(getString(R.string.dob_error));
        }

        String zipCodeString = zipCode.getText().toString();
        if (!isValidZipCode(zipCodeString)){
            zipCode.setError(getString(R.string.zip_error));
        }

    }

    private boolean isValidPhoneNumber(String phoneNumberString){
        try {
            Long.parseLong(phoneNumberString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (phoneNumberString.length() != 10){
            return false;
        }

        return true;
    }

    private boolean isValidZipCode(String zipCode){
        try {
            Long.parseLong(zipCode);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (zipCode.length() != 5){
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String emailString){
        if (!emailString.contains("@") || !emailString.contains(".") || emailString.contains(" ")){
            return false;
        }else {
            return true;
        }
    }

    private boolean isDobValid(String dobString){
        try {
            Long.parseLong(dobString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (dobString.length() != 4){
            return false;
        }

        return true;
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onConnected(Bundle bundle) {
        lastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastKnownLocation != null){
            double lat = lastKnownLocation.getLatitude();
            double lon = lastKnownLocation.getLongitude();

            Geocoder geocoder = new Geocoder(this);
            List<Address> addressList = null;
            try {
                addressList = geocoder.getFromLocation(lat, lon, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addressList == null){
                zipCodeLocationButton.setBackgroundResource(R.drawable.ic_action_location_off);
            }else {
                zipCodeLocationButton.setBackgroundResource(R.drawable.ic_action_location_found);
                zipCode.setText(addressList.get(0).getPostalCode());
            }

            locationProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Google client connection failed", Toast.LENGTH_LONG).show();
        zipCodeLocationButton.setBackgroundResource(R.drawable.ic_action_location_off);
        locationProgress.setVisibility(View.GONE);
    }
}
