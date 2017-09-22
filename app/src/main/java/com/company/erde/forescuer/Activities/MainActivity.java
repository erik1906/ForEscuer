package com.company.erde.forescuer.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.company.erde.forescuer.API.APIFourSquare;
import com.company.erde.forescuer.R;
import com.foursquare.android.nativeoauth.FoursquareOAuth;
import com.foursquare.android.nativeoauth.model.AccessTokenResponse;
import com.foursquare.android.nativeoauth.model.AuthCodeResponse;

import static com.company.erde.forescuer.API.APIFourSquare.CLIENT_ID;
import static com.foursquare.android.nativeoauth.FoursquareOAuth.*;


public class MainActivity extends AppCompatActivity {

     private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login =  (Button) findViewById(R.id.bLogIn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getConnectIntent(MainActivity.this, CLIENT_ID);
                startActivityForResult(intent, APIFourSquare.REQUEST_CODE_FSQ_CONNECT);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case APIFourSquare.REQUEST_CODE_FSQ_CONNECT:
                AuthCodeResponse codeResponse = new FoursquareOAuth().getAuthCodeFromResult(resultCode,data);

                Intent intent = FoursquareOAuth.getTokenExchangeIntent(MainActivity.this, CLIENT_ID, APIFourSquare.CLIENT_SECRET, codeResponse.getCode());
                startActivityForResult(intent, APIFourSquare.REQUEST_CODE_FSQ_TOKEN_EXCHANGE);

                break;

            case APIFourSquare.REQUEST_CODE_FSQ_TOKEN_EXCHANGE:
                AccessTokenResponse tokenResponse = FoursquareOAuth.getTokenFromResult(resultCode, data);

                Intent i = new Intent(MainActivity.this, ListActivity.class);
                i.putExtra("token", tokenResponse.getAccessToken());
                startActivity(i);

                break;
        }
    }
}
