package com.example.android.connectedweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.connectedweather.data.ForecastData;

import java.util.List;

public class WeatherDetailActivity extends AppCompatActivity {
    public static final String EXTRA_WEATHER = "Weather";

    private ForecastData mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_WEATHER)) {
            mData = (ForecastData)intent.getSerializableExtra(EXTRA_WEATHER);

            TextView weatherReportTV = findViewById(R.id.tv_weather_report);
            String title = "Detailed Weather Report \n";
            title += "for: " + mData.dt_txt;
            weatherReportTV.setText(title);

            TextView reportDescriptionTV = findViewById(R.id.tv_report_description);
            String detailedDescription = mData.weather.get(0).main + " and " +
                    mData.main.temp + "\u00B0" + "F \n\n" +
                    "Feels like: " + mData.main.feels_like + "\u00B0" + "F \n" +
                    "High temp of: " + mData.main.temp_max + "\u00B0" + "F \n" +
                    "Low temp of: " + mData.main.temp_min + "\u00B0" + "F \n" +
                    "Humidity at: " + mData.main.humidity + "%\n" +
                    "Wind speeds of: " + mData.wind.speed + " mph";

            reportDescriptionTV.setText(detailedDescription);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                shareRepo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareRepo() {
        if (mData != null) {
            String shareText = mData.weather.get(0).main + " and " +
                    mData.main.temp + "\u00B0" + "F \n\n" +
                    "Feels like: " + mData.main.feels_like + "\u00B0" + "F \n" +
                    "High temp of: " + mData.main.temp_max + "\u00B0" + "F \n" +
                    "Low temp of: " + mData.main.temp_min + "\u00B0" + "F \n" +
                    "Humidity at: " + mData.main.humidity + "%\n" +
                    "Wind speeds of: " + mData.wind.speed + " mph";

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            shareIntent.setType("text/plain");

            Intent chooserIntent = Intent.createChooser(shareIntent, null);
            startActivity(chooserIntent);
        }
    }

}
