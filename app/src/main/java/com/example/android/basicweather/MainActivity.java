package com.example.android.basicweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WeatherAdapter.OnDetailsListener {

    private ArrayList<String> weatherData;
    private ArrayList<String> detailedWeatherData;
    private RecyclerView weatherRV;
    private WeatherAdapter wWeatherAdapter;
    private Toast wToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherData = new ArrayList<>();
        populateWeatherArray(weatherData);                      // Brief Info

        detailedWeatherData = new ArrayList<>();
        populateDetailedWeatherArray(detailedWeatherData);      // Detailed Info

        wToast = null;                                          // Empty Toast

        weatherRV = findViewById(R.id.rv_weather);              // Recycler View
        weatherRV.setLayoutManager(new LinearLayoutManager(this));
        weatherRV.setHasFixedSize(true);

        wWeatherAdapter = new WeatherAdapter(this);     // Weather Adapter
        weatherRV.setAdapter(wWeatherAdapter);


        for (int i = 0; i < getDataCount(); i++) {              // Populate the array
            wWeatherAdapter.addForcast(weatherData.get(i));
        }


    }


    public void populateWeatherArray(ArrayList<String> array){
        array.add("Th Jan 16th - Sunny and Warm - 75F");
        array.add("Fri Jan 17th - Sunny and Cold - 55F");
        array.add("Sat Jan 18th - Cloudy and Cold - 45F");
        array.add("Sun Jan 19th - Cloudy and Rainy - 40F");
        array.add("Mon Jan 20th - Sunny and Hot - 95F");
        array.add("Tu Jan 21st - Blazing and Torturous - 110F");
        array.add("Wed Jan 22nd - Clear and Cold - 58F");
        array.add("Th Jan 23rd - Sunny and cold - 60F");
        array.add("Fri Jan 24th - Sunny and Chilly - 47F");
        array.add("Sat Jan 25th - Cloudy and Rainy - 53F");
        array.add("Sun Jan 26th - Cloudy and Pouring - 51F");
        array.add("Mon Jan 27th - Cloudy and Showers - 49F");
        array.add("Tu Jan 28th - Clear and Light Showers - 53F");
        array.add("Wed Jan 29th - Cloudy and Dry - 54F");
        array.add("Th Jan 30th - Cloudy and Cold - 56F");
        array.add("Fri Jan 31th - Cloudy and Showers - 48F");
    }

    public void populateDetailedWeatherArray(ArrayList<String> array){
        array.add("Thursday January 16th - Sunny and Warm - Low: 40F - High:80 - Humidity: 45% - Wind: 5mph");
        array.add("Friday January 17th - Sunny and Cold - Low: 37F - High:62 - Humidity: 38% - Wind: 2mph");
        array.add("Saturday January 18th - Cloudy and Cold - Low: 30F - High:49 - Humidity: 52% - Wind: 1mph");
        array.add("Sunday January 19th - Cloudy and Rainy - Low: 29F - High:50 - Humidity: 59% - Wind: 0mph");
        array.add("Monday January 20th - Sunny and Hot - Low: 69F - High:99 - Humidity: 30% - Wind: 0mph");
        array.add("Tuesday January 21st - Blazing and Torturous - Low: 102F - High:139 - Humidity: 107% - Wind: -10mph");
        array.add("Wednesday January 22nd - Clear and Cold - Low: 38F - High:60 - Humidity: 61% - Wind: 5mph");
        array.add("Thursday January 23rd - Sunny and cold - Low: 42F - High:69 - Humidity: 54% - Wind: 2mph");
        array.add("Friday January 24th - Sunny and Chilly - Low: 37F - High:50 - Humidity: 66% - Wind: 9mph");
        array.add("Saturday January 25th - Cloudy and Rainy - Low: 40F - High:58 - Humidity: 58% - Wind: 5mph");
        array.add("Sunday January 26th - Cloudy and Pouring - Low: 29F - High:60 - Humidity: 98% - Wind: 10mph");
        array.add("Monday January 27th - Cloudy and Showers - Low: 33F - High:57 - Humidity: 87% - Wind: 6mph");
        array.add("Tuesday January 28th - Cloudy and Showers - Low: 36F - High:58 - Humidity: 79% - Wind: 14mph");
        array.add("Wednesday January 29th - Cloudy and Dry - Low: 35F - High:54 - Humidity: 49% - Wind: 15mph");
        array.add("Thursday January 30th - Cloudy and Cold - Low: 38F - High:53 - Humidity: 86% - Wind: 8mph");
        array.add("Friday January 31st - Cloudy and Showers - Low: 33F - High:58 - Humidity: 87% - Wind: 9mph");
    }

    public int getDataCount() {
        return weatherData.size();
    }

    public int getDetailedDataCount() {
        return detailedWeatherData.size();
    }


    public void onWeatherDetails(String weatherForcast) {
        if (wToast != null) {
            wToast.cancel();
        }
        String toastText = "More Details: " + detailedWeatherData.get(weatherData.indexOf(weatherForcast));
        wToast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        wToast.setGravity(Gravity.CENTER, 0, 0);
        wToast.show();
    }

}
