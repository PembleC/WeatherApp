package com.example.android.connectedweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.android.connectedweather.data.ForecastData;
import com.example.android.connectedweather.utils.WeatherUtils;     // Imports file WeatherUtils
import com.example.android.connectedweather.utils.NetworkUtils;     // Imports file NetworkUtil

public class MainActivity extends AppCompatActivity implements ForecastAdapter.OnForecastItemClickListener {

    private RecyclerView mForecastListRV;
    private ForecastAdapter mForecastAdapter;
    private Toast mToast;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mErrorMessageTV;

    private static final String TAG = MainActivity.class.getSimpleName();   // Information for the Log.d call

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mForecastListRV = findViewById(R.id.rv_weather);

        mForecastListRV.setLayoutManager(new LinearLayoutManager(this));
        mForecastListRV.setHasFixedSize(true);

        mForecastAdapter = new ForecastAdapter(this);
        mForecastListRV.setAdapter(mForecastAdapter);

        mLoadingIndicatorPB = findViewById(R.id.pb_loading_indicator);
        mErrorMessageTV = findViewById(R.id.tv_error_message);

        doWeatherPopulate();

    }

    @Override
    public void onForecastItemClick(ForecastData detailedForecast) {
//        if (mToast != null) {
//            mToast.cancel();
//        }
//        mToast = Toast.makeText(this, "hi", Toast.LENGTH_LONG);         // Remove
//        mToast.setGravity(Gravity.CENTER, 0, 0);
//        mToast.show();

        Intent intent = new Intent(this, WeatherDetailActivity.class);
        intent.putExtra(WeatherDetailActivity.EXTRA_WEATHER, detailedForecast);
        startActivity(intent);
    }

    private void doWeatherPopulate() {
        //String city = "Salem";
        //String city = "Corvallis";
        //String countryCode = "US";
        //String url = WeatherUtils.buildWeatherURL(city, countryCode); // Build URL with input
        String url = WeatherUtils.buildWeatherURL();
        Log.d(TAG,"URL Created: " + url);

        new WeatherTask().execute(url);
    }

    public class WeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicatorPB.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            String searchResults = null;
            try {
                searchResults = NetworkUtils.doHttpGet(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
            if (s != null) {
                mErrorMessageTV.setVisibility(View.INVISIBLE);
                mForecastListRV.setVisibility(View.VISIBLE);
                ArrayList<ForecastData> ForecastResultsList = WeatherUtils.parseForecastResults(s);
                mForecastAdapter.updateForecastData(ForecastResultsList, ForecastResultsList);
            } else {
                mErrorMessageTV.setVisibility(View.VISIBLE);
                mForecastListRV.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_map:
                showMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMap() {
        startActivity(new Intent(MainActivity.this, MapsActivity.class));

    }

}
