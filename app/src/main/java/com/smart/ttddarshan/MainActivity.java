package com.smart.ttddarshan;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.smart.ttddarshan.adapter.CustomDrawerAdapter;
import com.smart.ttddarshan.fragment.AccommodationFragment;
import com.smart.ttddarshan.fragment.ECounterFragment;
import com.smart.ttddarshan.fragment.LegendDialogFragment;
import com.smart.ttddarshan.fragment.SevaFragment;
import com.smart.ttddarshan.fragment.SpecialEntryDarshanFragment;
import com.smart.ttddarshan.utils.AppUtils;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] ttdServices;

    private FirebaseAnalytics mFirebaseAnalytics;
    private int accomType = 1;
    private Spinner spinner;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        boolean isNetworkUp = AppUtils.isNetworkOnline((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE));
        if (!isNetworkUp)
            buildNetworkErrorUI();
        else
            displayMainScreen();
    }

    /**
     * To display error message in case of any network errors.
     */
    private void buildNetworkErrorUI() {

        setContentView(R.layout.activity_network_error);
        Button retryButton = (Button) findViewById(R.id.retryButton);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isNetworkOnline((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))) {
                    displayMainScreen();
                }
            }
        });
    }

    private void displayMainScreen() {
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        ttdServices = getResources().getStringArray(R.array.services_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        /*mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, ttdServices));*/
        mDrawerList.setAdapter(new CustomDrawerAdapter(this, R.layout.drawer_list_item, ttdServices));
        mDrawerList.setOnItemClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        spinner = new Spinner(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        getSupportActionBar().setCustomView(spinner);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().getCustomView().setVisibility(View.GONE);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        selectItem(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        accomType = i + 1; // Tirumala-1 Tirupati-2
        AccommodationFragment fragment = (AccommodationFragment) AccommodationFragment.getInstance();
        fragment.getDarshanData(accomType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        getSupportActionBar().getCustomView().setVisibility(View.GONE);
        spinner.setOnItemSelectedListener(null);
        switch (position) {
            case 0:
                fragment = new SevaFragment();
                break;
            case 1:
                fragment = SpecialEntryDarshanFragment.getInstance();
                break;
            case 2:
                fragment = AccommodationFragment.getInstance();
                Bundle bundle = new Bundle();
                bundle.putInt("accomType", accomType);
                fragment.setArguments(bundle);
                getSupportActionBar().getCustomView().setVisibility(View.VISIBLE);
                spinner.setOnItemSelectedListener(this);
                break;
            case 3:
                fragment = new ECounterFragment();
                break;
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(ttdServices[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private void logToFirebase(long id, String name) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        if (mDrawerToggle != null)
            mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        if (mDrawerToggle != null)
            mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle != null && mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        if (id == R.id.legend) {
            LegendDialogFragment fragment = new LegendDialogFragment();
            fragment.show(getFragmentManager(), "Legend");
            return true;
        } else if (id == R.id.quota) {
            Intent intent = new Intent(this, ESevaQuotaActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
        ((CustomDrawerAdapter) parent.getAdapter()).selectItem(position);
        logToFirebase(id, ((CustomDrawerAdapter) parent.getAdapter()).getItem(position));
    }
}