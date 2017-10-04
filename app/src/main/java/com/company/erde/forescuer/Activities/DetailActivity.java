package com.company.erde.forescuer.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.erde.forescuer.API.APIFourSquare;
import com.company.erde.forescuer.API.FourSquare;
import com.company.erde.forescuer.Model.VenueDetails.VenueDetails;
import com.company.erde.forescuer.Model.VenueDetails.VenueInfo;
import com.company.erde.forescuer.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    Toolbar myToolbar;
    private FourSquare fourSquare;
    ImageView ivPhoto;
    TextView tvName;
    TextView tvCity;
    TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

         myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        tvName =(TextView) findViewById(R.id.tvName);

        tvCity =(TextView) findViewById(R.id.tvCity);
        tvAddress =(TextView) findViewById(R.id.tvAddress);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fourSquare = APIFourSquare.getApi().create(FourSquare.class);


        Calendar cal = Calendar.getInstance();

        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");

        String formatCal = formater.format(cal.getTime());
        Log.d("params",getIntent().getStringExtra("id")+" "+getIntent().getStringExtra("token")+" "+formatCal);
        getVenuesInfo(getIntent().getStringExtra("id"), getIntent().getStringExtra("token"), formatCal);


    }

    public void getVenuesInfo(String id, String token, String v){
        Call<VenueInfo> photosCall = fourSquare.getVenueInfo(id, token, v);

        photosCall.enqueue(new Callback<VenueInfo>() {
            @Override
            public void onResponse(Call<VenueInfo> call, Response<VenueInfo> response) {
                final VenueDetails res = response.body().getReponse().getVenue();
                //Log.d("Entra","entra");
                tvName.setText(res.getName());
                tvAddress.setText(res.getLocation().getAddress());
                tvCity.setText(res.getLocation().getCity());

                if(res.getPhotos().getCount() > 0) {
                    Picasso.with(getApplicationContext()).load(res.getPhotos().getGroups()[0].getItems()[0].getPrefix() + "300x300" + res.getPhotos().getGroups()[0].getItems()[0].getSuffic()).into(ivPhoto);
                }else{
                    ivPhoto.setImageResource(R.drawable.image);
                }
            }

            @Override
            public void onFailure(Call<VenueInfo> call, Throwable t) {
                Log.d("Fail","Fail");
            }
        });
    }

}
