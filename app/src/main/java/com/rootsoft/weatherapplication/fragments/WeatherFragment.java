package com.rootsoft.weatherapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.rootsoft.weatherapplication.R;
import com.rootsoft.weatherapplication.managers.OnCityListener;
import com.rootsoft.weatherapplication.models.City;
import com.rootsoft.weatherapplication.utils.tasks.DownloadTask;
import com.rootsoft.weatherapplication.views.adapters.CityAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import utils.json.JSONParser;

/**
 * Created by RootSoft on 17/12/14.
 */
public class WeatherFragment extends Fragment implements OnCityListener{

    //Attributes
    private ArrayList<City> cities;
    private CityAdapter cityAdapter;
    private EditText etCity;

    //Constructors

    public WeatherFragment() {
        this.cities = new ArrayList<City>();
    }

    //Properties

    //Methods


    //Callbacks

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        etCity = (EditText) rootView.findViewById(R.id.et_city);
        Button btnOk = (Button) rootView.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DownloadTask task = new DownloadTask(getActivity());
                task.setOnCityListener(WeatherFragment.this);
                task.execute(etCity.getText().toString());
            }
        });


        //Initialize ListView
        cityAdapter = new CityAdapter(this.getActivity(), R.layout.lv_row_city, cities);
        ListView lvCities = (ListView) rootView.findViewById(R.id.lv_cities);
        lvCities.setAdapter(cityAdapter);


        return rootView;
    }


    @Override
    public void onCityDownloading() {

    }

    @Override
    public void onCityReceived(boolean success, String result) {
        if (success) {
            Log.d("weater_fragment", result);

            JSONParser jsonParser = null;
            try {
                jsonParser = new JSONParser(result);
                cityAdapter.addCity(jsonParser.parseWeather());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
