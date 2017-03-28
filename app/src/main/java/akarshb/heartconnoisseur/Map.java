package akarshb.heartconnoisseur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity  implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);//this is call back function executes parallely in background
    }

    @Override
    public void onMapReady(GoogleMap map) {
        m_map = map;
        mapReady = true;
        LatLng nallakunta = new LatLng(17.390131, 78.321375);
        //target() is where camera should focus on, tilt() is by default 90 degrees, bearing() is rotating the map
        CameraPosition target = CameraPosition.builder().target(nallakunta).zoom(18).tilt(65).bearing(112).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));

        //CameraPosition target2=CameraPosition.builder().target(nallakunta).tilt(90).build();
        //m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target2),3000,null);

        //to add marker
        MarkerOptions home = new MarkerOptions().position(nallakunta).title("Target");
        m_map.addMarker(home);
    }
}
