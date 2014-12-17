package utils.json;

import com.rootsoft.weatherapplication.models.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by RootSoft on 17/12/14.
 */
public class JSONParser {

    //Attributes
    private String result;
    private JSONObject json;

    //Constructor
    public JSONParser(String result) throws JSONException {
        this.result = result;
        json = new JSONObject(result);

    }

    //Properties
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    //Methods

    public String getError() {
        try {
            String cod = json.getString("cod");
            return cod;
        } catch (JSONException e) {
            e.printStackTrace();
            return "404";
        }
    }

    public City parseWeather() {
        City city = null;
        double temp_min = 0;
        double temp_max= 0;
        try {
            JSONObject records = json.getJSONObject("main");
            temp_min = records.getDouble("temp_min");
            temp_max = records.getDouble("temp_max");

            String cityName = json.getString("name");
            city = new City(cityName, temp_max, temp_min);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return city;
    }
}
