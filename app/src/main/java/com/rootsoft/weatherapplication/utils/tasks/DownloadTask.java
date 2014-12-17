package com.rootsoft.weatherapplication.utils.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.rootsoft.weatherapplication.managers.OnCityListener;
import com.rootsoft.weatherapplication.utils.constants.AppConstants;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

import utils.json.JSONParser;

/**
 * Created by RootSoft on 17/12/14.
 */
public class DownloadTask extends AsyncTask<String, String, String> {

    //Constants
    public static final String TAG = "download_task";

    //Attributes
    private JSONParser jsonParser;
    private ProgressDialog progressDialog;
    private OnCityListener cityListener;
    private Context context;
    String result = "";

    //Properties


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    //Constructor
    public DownloadTask(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Downloading your data...");

        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
                cancel(true);
            }
        });
    }

    public void setOnCityListener(OnCityListener cityListener) {
        this.cityListener = cityListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();

    }

    @Override
    protected String doInBackground(String... city) {
        URL url = null;
        try {
            this.cityListener.onCityDownloading();
            Log.d(TAG, "City: " + city[0]);
            url = new URL(AppConstants.API_URL + city[0]);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpURLConnection.getInputStream();
                processStream(in);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    protected void onPostExecute(String result) {
        try {
            jsonParser = new JSONParser(result);
            String cod = jsonParser.getError();
            if (cod.equals("200")) {
                this.cityListener.onCityReceived(true, result);
            } else {
                this.cityListener.onCityReceived(false, result);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.dismiss();

        super.onPostExecute(result);
    }

    private void processStream(InputStream inputStream) {
        // Convert response to string using String Builder
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
            StringBuilder sBuilder = new StringBuilder();

            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }

            inputStream.close();
            result = sBuilder.toString();

        } catch (Exception e) {
            Log.e("StringBuilding & BufferedReader", "Error converting result " + e.toString());
        }
    }
}
