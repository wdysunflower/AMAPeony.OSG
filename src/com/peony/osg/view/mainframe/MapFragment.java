package com.peony.osg.view.mainframe;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.peony.osg.R;
import com.peony.osg.view.MainActivity;

/**
 * Created by wdynetposa on 2014/11/29.
 */
public class MapFragment extends Fragment {
    private View mRoot;
    private AMap aMap;
    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_map, container, false);
        setView();
        iniMap(savedInstanceState);
        return mRoot;
    }

    private void setView() {}

    private void iniMap(Bundle savedInstanceState) {
        mapView = (MapView) mRoot.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        MainActivity.getMainResideMenu().addIgnoredView(mapView);

        aMap = mapView.getMap();
        setUpMap();
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        aMap.setLocationSource(new OnLocationSource());// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    public class OnLocationSource implements LocationSource {
        @Override
        public void activate(OnLocationChangedListener onLocationChangedListener) {

        }

        @Override
        public void deactivate() {

        }
    }
}
