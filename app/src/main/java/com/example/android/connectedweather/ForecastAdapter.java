package com.example.android.connectedweather;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.connectedweather.data.ForecastData;

import java.util.ArrayList;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastItemViewHolder> {

    private ArrayList<ForecastData> mForecastData;
    private ArrayList<ForecastData> mDetailedForecastData;
    private OnForecastItemClickListener mOnForecastItemClickListener;

    public interface OnForecastItemClickListener {
        void onForecastItemClick(ForecastData detailedForecast);
    }

    public ForecastAdapter(OnForecastItemClickListener onForecastItemClickListener) {
        mOnForecastItemClickListener = onForecastItemClickListener;
    }

    public void updateForecastData(ArrayList<ForecastData> forecastData, ArrayList<ForecastData> detailedForecastData) {
        mForecastData = forecastData;
        mDetailedForecastData = detailedForecastData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mForecastData != null && mDetailedForecastData != null) {
            return Math.min(mForecastData.size(), mDetailedForecastData.size());
        } else {
            return 0;
        }
    }

    @Override
    public ForecastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastItemViewHolder holder, int position) {
        holder.bind(mForecastData.get(position));
    }

    class ForecastItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mForecastTextView;

        public ForecastItemViewHolder(View itemView) {
            super(itemView);
            mForecastTextView = itemView.findViewById(R.id.tv_weather_data);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ForecastData detailedForecast = mDetailedForecastData.get(getAdapterPosition());
                    mOnForecastItemClickListener.onForecastItemClick(detailedForecast);
                }
            });
        }

        void bind(ForecastData forecast) {
            String forecastTemp = Double.toString(forecast.main.temp);
            String forecastText = forecast.dt_txt + "\n"
                    + forecast.weather.get(0).main + "\n"
                    + forecastTemp + "\u00B0" + "F";

            mForecastTextView.setText(forecastText);
        }
    }
}
