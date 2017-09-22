package com.company.erde.forescuer.Activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.company.erde.forescuer.Adapters.RecyclerViewAdapter;
import com.company.erde.forescuer.Manifest;
import com.company.erde.forescuer.Model.Places;
import com.company.erde.forescuer.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private final int REQUEST_LOCATION = 1;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GoogleApiClient googleApiClient;
    private Location location;


    private ArrayList<Places>  places = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Log.d("aut",getIntent().getStringExtra("token"));
        places.add(new Places("thsdjf","aqui"));
        places.add(new Places("thsdjf","aqui no"));
        places.add(new Places("thsdjf","aqui"));
        places.add(new Places("thsdjf","aqui nofsadfsdaf dfsdfjksdaf sdakjfbksajbflsadnflsda fkjdasfljnhsdfnsdalkfnlsdaf sdfnosdfasdmfñsdaF"));


        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        recyclerView = (RecyclerView) findViewById(R.id.rvPlaces);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(places, new RecyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(ListActivity.this,"Es el"+position,Toast.LENGTH_LONG).show();
            }
        });
        Log.d("nombre", places.get(0).getName());
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocation();
    }

    private void startLocation() {
        getLocation();

        if(location!= null)updateLocation();
    }

    private boolean checkPermission() {
        int permission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    private void getLocation() {
        if(checkPermission()){
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        }else requestPermissions();
    }

    private void requestPermissions() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this,"No se dieron permisos",Toast.LENGTH_SHORT).show();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_LOCATION){
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

                if(location != null) updateLocation();
                else  Toast.makeText(this,"Ubicación no encontrada",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"No se dieron permisos",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateLocation() {
        Log.d("Lat","Lat = "+ location.getLatitude());
        Log.d("Lon","Lon = "+ location.getLongitude());
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }
}
