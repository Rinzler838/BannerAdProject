package com.google.android.gms.example.bannerexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Main Activity inflation + child fragment inflation.
 */
public class MyActivity extends ActionBarActivity
{

    private AdView appAdView;
    private TextView ratingText;
    private ToggleButton onOffButton;
    private RatingBar appRating;
    private AnalogClock personalClock;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Identifies application components by name.
        appAdView = (AdView) findViewById(R.id.ad_view);
        ratingText = (TextView) findViewById(R.id.ratingText);
        onOffButton = (ToggleButton) findViewById(R.id.onOffButton);
        appRating = (RatingBar) findViewById(R.id.appRating);
        personalClock = (AnalogClock) findViewById(R.id.personalClock);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        appAdView.loadAd(adRequest);

        setupListeners();
    }

    //Application method(s)
    private void showClock()
    {
        if (personalClock.getVisibility() == View.INVISIBLE)
        {
            personalClock.setVisibility(View.VISIBLE);
            ratingText.setVisibility(View.VISIBLE);
            appRating.setVisibility(View.VISIBLE);
        }
        else
        {
            personalClock.setVisibility(View.INVISIBLE);
            ratingText.setVisibility(View.INVISIBLE);
            appRating.setVisibility(View.INVISIBLE);
        }
    }

    private void setupListeners()
    {
        onOffButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View ButtonView)
            {
                showClock();
            }
        });
    }

    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (appAdView != null)
        {
            appAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume()
    {
        super.onResume();
        if (appAdView != null)
        {
            appAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy()
    {
        if (appAdView != null)
        {
            appAdView.destroy();
        }
        super.onDestroy();
    }

    //Menu Inflation
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
