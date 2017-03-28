package akarshb.heartconnoisseur;

/**
 * Created by Dell on 28-03-2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 26-02-2017.
 */

public class ParametersAdapter extends ArrayAdapter {
    List list=new ArrayList();

    public ParametersAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Parameters object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        ParametersHolder parametersHolder;
        if(row==null){//creating row if not present
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            parametersHolder=new ParametersHolder();
            parametersHolder.TxParam1=(TextView) row.findViewById(R.id.param1);
            parametersHolder.TxParam2=(TextView)row.findViewById(R.id.param2);
            row.setTag(parametersHolder);
        }
        else{
            parametersHolder=(ParametersHolder)row.getTag();
        }
        Parameters parameters=(Parameters) this.getItem(position);
        parametersHolder.TxParam1.setText(parameters.getParam1());
        parametersHolder.TxParam2.setText(parameters.getParam2());
        return row;
    }
    static class ParametersHolder{
        TextView TxParam1,TxParam2;
    }
}
