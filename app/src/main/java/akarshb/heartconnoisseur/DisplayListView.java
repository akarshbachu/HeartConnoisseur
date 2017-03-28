package akarshb.heartconnoisseur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    JSONObject jsonObject;
    JSONArray jsonArray;
    String json_result;
    ParametersAdapter parametersAdapter;
    ListView listView;
    TextView tvRawData,highValue;
    int count=0,sum=0,tempMax=0,max=0,heartRate=0;
    String param1="",param2="";
    float avg=0.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        tvRawData=(TextView)findViewById(R.id.rawdata);
        highValue=(TextView)findViewById(R.id.peakval);
        listView=(ListView)findViewById(R.id.list_item);
        parametersAdapter=new ParametersAdapter(this,R.layout.row_layout);

        listView.setAdapter(parametersAdapter);
        json_result=getIntent().getExtras().getString("Json data");//its the key which we have mentioned in main activity in putExtras()
        try {
            jsonObject=new JSONObject(json_result);

            //this comment line is for google spread sheet values
            //jsonArray=jsonObject.getJSONArray("Sheet1");//sheet1 is the json array name which was created when google spreadsheet was created

            jsonArray=jsonObject.getJSONArray("feeds");

            while(count<jsonArray.length()){
                JSONObject jo=jsonArray.getJSONObject(count);//like every row in json array

                //param1=jo.getString("name");
                //param2=jo.getString("country");

                param1=jo.getString("entry_id");
                param2=jo.getString("field1");
                heartRate=Integer.parseInt(param2);
                //for avg of heart rate values
                sum=sum+heartRate;
                if(heartRate>max)
                    max=heartRate;
                Parameters parameters=new Parameters(param1,param2);
                parametersAdapter.add(parameters);//adding parameters to adapter
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            avg=(float)sum/(float)count;
            String av=avg+"";
            tvRawData.setText(av);
            highValue.setText(max+"");
        }

    }
}
