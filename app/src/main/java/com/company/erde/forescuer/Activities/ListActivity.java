package com.company.erde.forescuer.Activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.company.erde.forescuer.API.APIFourSquare;
import com.company.erde.forescuer.API.FourSquare;
import com.company.erde.forescuer.Adapters.RecyclerViewAdapter;
import com.company.erde.forescuer.Manifest;
import com.company.erde.forescuer.Model.Places;
import com.company.erde.forescuer.Model.VenuesSearch.CatIcon;
import com.company.erde.forescuer.Model.VenuesSearch.Categorie;
import com.company.erde.forescuer.Model.VenuesSearch.Venue;
import com.company.erde.forescuer.Model.VenuesSearch.VenuesSearch;
import com.company.erde.forescuer.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private final int REQUEST_LOCATION = 1;

    private Toolbar myToolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GoogleApiClient googleApiClient;
    private Location location;

    private FourSquare fourSquare;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fourSquare = APIFourSquare.getApi().create(FourSquare.class);


        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        recyclerView = (RecyclerView) findViewById(R.id.rvPlaces);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);




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
        String ll = location.getLatitude()+","+location.getLongitude();

        Calendar cal = Calendar.getInstance();

        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");

        String formatCal = formater.format(cal.getTime());

        getVenues(ll, getIntent().getStringExtra("token"), formatCal);
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


    public void getVenues(String ll, String token, String v){
        Call<VenuesSearch> photosCall = fourSquare.getVenues(ll, token, v);
        photosCall.enqueue(new Callback<VenuesSearch>() {
            @Override
            public void onResponse(Call<VenuesSearch> call, Response<VenuesSearch> response) {
                final Venue[] res = response.body().getReponse().getVenues();
                final ArrayList<Places> places = new ArrayList<>();
                for(int i =0; i<12;i++) {

                    CatIcon categorie = res[i].getCategories()[0].getIcon();
                    String icon = categorie.getPrefix()+"88"+categorie.getSuffix();
                    Places place = new Places(icon,res[i].getName());
                    places.add(place);

                }

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(places, new RecyclerViewAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent i = new Intent(ListActivity.this, DetailActivity.class);
                        i.putExtra("token",getIntent().getStringExtra("token"));
                        Log.d("Id:" ,res[position].getId() );
                        i.putExtra("id", res[position].getId());
                        startActivity(i);
                    }
                });

                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<VenuesSearch> call, Throwable t) {

            }
        });
    }
}
