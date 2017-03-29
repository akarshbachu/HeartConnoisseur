package akarshb.heartconnoisseur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.fitness.data.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.GregorianCalendar;

public class GraphView extends AppCompatActivity {

    LineGraphSeries<com.jjoe64.graphview.series.DataPoint> series;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);
        double x,y;
        com.jjoe64.graphview.GraphView graph=(com.jjoe64.graphview.GraphView)findViewById(R.id.graph);
        graph.getViewport().setScrollable(true);//enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling
        series=new LineGraphSeries<com.jjoe64.graphview.series.DataPoint>();
        int yAxis[]=new int[]{73,75,76,75,78,79,78,79,78,79,78,76,77,79,78};
        int xAxis[]=new int[]{2,4,6,8,10,12,14,16,18,20,22,24,26,28,30};
        for(int i=0;i<15;i++){
            x= xAxis[i];
            y=yAxis[i];
            series.appendData(new com.jjoe64.graphview.series.DataPoint(x,y),true,500);
        }
        graph.addSeries(series);
    }
}
