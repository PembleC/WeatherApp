package com.example.android.connectedweather.data;

import java.io.Serializable;
import java.util.ArrayList;

public class ForecastData implements Serializable {
    public mainData main;
    public ArrayList<weatherData> weather;
    public windData wind;
    public String dt_txt;
}

