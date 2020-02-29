package com.example.android.connectedweather.utils;     // Must be in package to be imported

import android.net.Uri;

// For Tag and Logs
import android.util.Log;
import com.example.android.connectedweather.MainActivity;
import com.example.android.connectedweather.data.ForecastData;
import com.google.gson.Gson;

import java.util.ArrayList;

public class WeatherUtils {
    private final static String WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private final static String WEATHER_QUERY_PARAM = "q";
    private final static String WEATHER_QUERY_VALUE = "Corvallis,US";
    private final static String WEATHER_UNITS_PARAM = "units";
    private final static String WEATHER_UNITS = "imperial";
    private final static String WEATHER_APPID_PARAM = "appid";
    private final static String WEATHER_APPID = "c0743581803dc55cf68ea88d21677a16";

    static class ForecastResults {                                          // List of ForecastData
        ArrayList<ForecastData> list;
    }

//    public static String buildWeatherURL(String city, String countryCode){      // Create the HTTPS:// Url
//        return Uri.parse(WEATHER_BASE_URL).buildUpon()
//                .appendQueryParameter(WEATHER_QUERY_PARAM, city)
//                .appendQueryParameter(WEATHER_UNITS_PARAM, WEATHER_UNITS)
//                .appendQueryParameter(WEATHER_APPID_PARAM, WEATHER_APPID)
//                .build()
//                .toString();
//    }

    public static String buildWeatherURL(){      // Create the HTTPS:
        return Uri.parse(WEATHER_BASE_URL).buildUpon()
                .appendQueryParameter(WEATHER_QUERY_PARAM, WEATHER_QUERY_VALUE)
                .appendQueryParameter(WEATHER_UNITS_PARAM, WEATHER_UNITS)
                .appendQueryParameter(WEATHER_APPID_PARAM, WEATHER_APPID)
                .build()
                .toString();
    }

    public static ArrayList<ForecastData> parseForecastResults(String json) {       // Parse the json String
        Gson gson = new Gson();
        ForecastResults results = gson.fromJson(json, ForecastResults.class);
        if (results != null && results.list != null) {
            return results.list;
        } else {
            return null;
        }
    }

}

// KEY = c0743581803dc55cf68ea88d21677a16
//               api.openweathermap.org/data/2.5/forecast?q={city name},{country code}
// URL Created: http://api.openweathermap.org/data/2.5/forecast?q=Corvallis%2CUS&units=imperial&appid=c0743581803dc55cf68ea88d21677a16