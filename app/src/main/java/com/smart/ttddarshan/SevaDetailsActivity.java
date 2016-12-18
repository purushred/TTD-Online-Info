package com.smart.ttddarshan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.smart.ttddarshan.vo.SevaDetailsVO;

public class SevaDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seva_details);

        SevaDetailsVO sevaDetails = (SevaDetailsVO) getIntent().getSerializableExtra("sevaDetails");
        getSupportActionBar().setTitle(sevaDetails.sevaName);

        TextView sevaName = (TextView) findViewById(R.id.sevaName);
        TextView selectedDate = (TextView) findViewById(R.id.selectedDate);
        TextView sevaPrice = (TextView) findViewById(R.id.sevaPrice);
        TextView personsAllowed = (TextView) findViewById(R.id.personsAllowed);
        TextView availableSlots = (TextView) findViewById(R.id.availableSlots);
        TextView reportingTime = (TextView) findViewById(R.id.reportingTime);
        TextView sevaStartTime = (TextView) findViewById(R.id.sevaStartTime);
        TextView sevaEndTime = (TextView) findViewById(R.id.sevaEndTime);
        Button bookNow = (Button) findViewById(R.id.bookButton);
        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://ttdsevaonline.com/#/sevaCal";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        sevaName.setText(sevaDetails.sevaName);
        selectedDate.setText("Seva Date: " + sevaDetails.sevaDate);
        sevaPrice.setText("Seva Price: Rs." + sevaDetails.sevaPrice + "/person");
        personsAllowed.setText("Persons Allowed: " + sevaDetails.maxNoOfPersons);
        availableSlots.setText("Tickets Available: " + sevaDetails.noOfTicketsAvailable);
        reportingTime.setText("Reporting Time: " + sevaDetails.reportingTime);
        sevaStartTime.setText("Seva Start Time: " + sevaDetails.sevaFromTime);
        sevaEndTime.setText("Seva End Time: " + sevaDetails.sevaToTime);

        final AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                Log.e("Ads", "onAdLoaded");
                mAdView.bringToFront();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
