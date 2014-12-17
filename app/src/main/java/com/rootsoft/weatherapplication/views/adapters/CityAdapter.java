package com.rootsoft.weatherapplication.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rootsoft.weatherapplication.R;
import com.rootsoft.weatherapplication.models.City;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by RootSoft on 17/12/14.
 */
public class CityAdapter extends ArrayAdapter<City> {

    //Constants
    public static final String TAG = "city_adapter";

    //Attributes
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<City> cities;

    //Constructor
    public CityAdapter(Context mContext, int layoutResourceId, ArrayList<City> cities) {
        super(mContext, layoutResourceId, cities);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.cities = cities;
    }

    //Properties

    //Methods

    //Callbacks


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        CityViewHolder holder = null;

        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
           // frameContent = (FrameLayout) convertView;
            holder = new CityViewHolder();
            holder.tvCityNaam = (TextView)convertView.findViewById(R.id.tv_city_name);
            holder.tvMaxTemp = (TextView)convertView.findViewById(R.id.tv_max_temp);
            holder.tvMinTemp = (TextView)convertView.findViewById(R.id.tv_min_temp);
            convertView.setTag(holder);
        } else {
            holder = (CityViewHolder) convertView.getTag();
        }

        City c = cities.get(position);

        holder.tvCityNaam.setText(c.getCityName());
        holder.tvMaxTemp.setText(String.valueOf(c.getMaxTemp()));
        holder.tvMinTemp.setText(String.valueOf(c.getMinTemp()));

        return convertView;
    }

    public void addCity(City city) {
        this.add(city);
        notifyDataSetChanged();

    }

    public static class CityViewHolder {
        public TextView tvCityNaam;
        public TextView tvMinTemp;
        public TextView tvMaxTemp;
    }








}
