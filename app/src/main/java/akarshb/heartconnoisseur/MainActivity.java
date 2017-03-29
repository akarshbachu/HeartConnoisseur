package akarshb.heartconnoisseur;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String json_result;
    ImageButton parseJson,location,about;
    public TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseJson=(ImageButton) findViewById(R.id.parsejson);
        location=(ImageButton) findViewById(R.id.loc);
        about=(ImageButton)findViewById(R.id.about);
        tv=(TextView) findViewById(R.id.tvdata);
        //json data will be loaded in background asynchronously
        new BackgroundTask().execute();

        parseJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (json_result == null) {
                    Toast.makeText(getApplicationContext(), "First Get Json Data", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, DisplayListView.class);
                    i.putExtra("Json data", json_result);//this is used to send data to nect activity so that we can retrieve json_result in that activity
                    startActivity(i);
                }
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Map.class);
                startActivity(i);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,About.class);
                startActivity(i);
            }
        });
    }
    class BackgroundTask extends AsyncTask<Void,Void,String>{
        String JSON_STRING;
        String json_url;
        @Override
        protected void onPreExecute() {
            //this comment line is for google spreadsheet values un comment if u want this and uncomment other two param lines in DisplayListView.java file

            //json_url="https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1rrgzNmpgz2GfsQv7S6LavNMiFmWcXH5ks18ZgqmA3gM&sheet=Sheet1";

            json_url="https://thingspeak.com/channels/245802/field/1.json";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while((JSON_STRING=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            tv=(TextView)findViewById(R.id.tvdata);
            //tv.setText(result);//result in JSON format
            json_result=result;//storing result in this var
        }
    }

}
