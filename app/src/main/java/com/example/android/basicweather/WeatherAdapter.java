package com.example.android.basicweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private ArrayList<String> weatherData;
    private OnDetailsListener wListener;

    public interface OnDetailsListener {
        void onWeatherDetails(String todoText);
    }

    public WeatherAdapter(OnDetailsListener listener) {
        weatherData = new ArrayList<>();
        wListener = listener;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weather_forcast_item, parent, false);
        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        String weatherForcast = weatherData.get(position);
        holder.bind(weatherForcast);
    }

    public void addForcast(String weatherForcast) {
        weatherData.add(0, weatherForcast);
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }




    //Inner class which objects of this class represent individual items in your forecast list.
    class WeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView wWeatherTV;

        public WeatherViewHolder(final View itemView) {
            super(itemView);
            wWeatherTV = itemView.findViewById(R.id.tv_weather_data);

            wWeatherTV.setOnClickListener(new CompoundButton.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String weatherForcast = wWeatherTV.getText().toString();
                    wListener.onWeatherDetails(weatherForcast);
                }
            });

        }

        void bind(String newWeatherForcast) {
            wWeatherTV.setText(newWeatherForcast);
        }
    }

}
