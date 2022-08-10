package sg.edu.rp.c346.id21010860.l13_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    ListView lvCarpark;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCarpark = findViewById(R.id.listview);
        client = new AsyncHttpClient();
    }
        @Override
        protected void onResume() {
            super.onResume();

            ArrayList<carpark> alCarpark = new ArrayList<carpark>();    //arraylist to store the data

            client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {    //object of asynchttpclient

                int lots;
                String type;
                int available;
                String carparknum;


                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                try {
                        JSONArray jsonArritems = response.getJSONArray("items");
                        JSONObject firstObj = jsonArritems.getJSONObject(0);
                        JSONArray jsonArrcarpark = firstObj.getJSONArray("carpark_data");
                        for (int i = 0; i < jsonArrcarpark.length(); i++) {
                            JSONObject jsonObjcarpark = jsonArrcarpark.getJSONObject(i);
                            JSONArray jsonArrInfo = jsonObjcarpark.getJSONArray("carpark_info");
                            JSONObject jsonObjCarpork = jsonArrInfo.getJSONObject(0);


                            lots = Integer.parseInt(jsonObjCarpork.getString("total_lots"));
                            type = jsonObjCarpork.getString("lot_type");
                            available = Integer.parseInt(jsonObjCarpork.getString("lots_available"));
                            carparknum = jsonObjcarpark.getString("carpark_number");
                            carpark Carpark = new carpark(lots, type, available, carparknum);
                            alCarpark.add(Carpark);
                        }
                    } catch (JSONException e) {
                        Log.d("exception", e.toString());//app wont crash but go to the
                    }
                    ArrayAdapter<carpark> aaCarpark = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alCarpark);
                    lvCarpark.setAdapter(aaCarpark);
                }


            });
        }
    }