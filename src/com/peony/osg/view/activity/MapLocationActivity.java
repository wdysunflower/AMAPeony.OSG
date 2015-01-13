package com.peony.osg.view.activity;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.model.Marker;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.peony.osg.R;
import com.peony.osg.model.object.MapLocation;
import com.peony.osg.util.ToastUtil;
import com.peony.osg.view.adapter.MapLocationAdapter;
import com.peony.osg.view.widget.listview.PagingListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdynetposa on 2014/12/5.
 */
public class MapLocationActivity extends Activity {
    private TextView mLocationResultTV;
    private PagingListView mPoiResultLV;

    // 定位
    private LocationManagerProxy mAMapLocationManager;
    OnAMapLocationEvent locationEvent;

    // 搜索
    private String[] itemDeep = {"餐饮", "景区", "酒店", "影院"};
    private String[] itemTypes = {"所有poi", "有团购", "有优惠", "有团购或者优惠"};
    private String deepType = ""; // poi搜索类型
    private int searchType = 0; // 搜索类型

    private int currentPage = 0; // 当前页面，从0开始计数

    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;

    private PoiResult poiResult;

    // Adapter
    MapLocationAdapter locationAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);

        initView();
        initData();
    }

    private void initView() {
        mLocationResultTV = (TextView) findViewById(R.id.location_result_tv);
        mPoiResultLV = (PagingListView) findViewById(R.id.poi_result_lv);
    }

    private void initData() {
        locationAdapter = new MapLocationAdapter(this);
        mPoiResultLV.setHasMoreItems(true);
        mPoiResultLV.setAdapter(locationAdapter);
        mPoiResultLV.setPagingListener(pagingEvent);

        locationEvent = new OnAMapLocationEvent();
        mAMapLocationManager = LocationManagerProxy.getInstance(this);
    }

    public void locationSearch(View view) {
        // showProgressDialog();// 显示进度框
        mAMapLocationManager.requestLocationData(LocationProviderProxy.AMapNetwork, 60 * 1000, 10,
                locationEvent);
    }

    private PagingListView.onPagingListener pagingEvent = new PagingListView.onPagingListener() {
        @Override
        public void onLoadMoreItems() {
            if (query != null && poiSearch != null && poiResult != null) {
                if (poiResult.getPageCount() - 1 > currentPage) {
                    currentPage++;

                    query.setPageNum(currentPage);// 设置查后一页
                    poiSearch.searchPOIAsyn();
                } else {
                    ToastUtil.show(MapLocationActivity.this, R.string.no_result);
                }
            }
        }
    };

    private class OnAMapLocationEvent implements AMapLocationListener {

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null && aMapLocation.getAMapException().getErrorCode() == 0) {
                mAMapLocationManager.removeUpdates(locationEvent);

                String floor = aMapLocation.getFloor() == null ? "" : aMapLocation.getFloor();
                String address = aMapLocation.getAddress();
                mLocationResultTV.setText(address);

                double a = aMapLocation.getLatitude();
                double b = aMapLocation.getLongitude();
                LatLonPoint point = new LatLonPoint(a, b);
                String province = aMapLocation.getProvince();
                doSearchQuery(point, province);
            } else {
                Toast.makeText(MapLocationActivity.this, "定位失败！", Toast.LENGTH_SHORT);
            }
        }
    }

    /**
     * 开始进行poi搜索
     */
    private void doSearchQuery(LatLonPoint lp, String province) {
        currentPage = 0;
        query = new PoiSearch.Query("", deepType, province);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        // searchType = tsearchType;

        switch (searchType) {
            case 0: {// 所有poi
                query.setLimitDiscount(false);
                query.setLimitGroupbuy(false);
            }
                break;
            case 1: {// 有团购
                query.setLimitGroupbuy(true);
                query.setLimitDiscount(false);
            }
                break;
            case 2: {// 有优惠
                query.setLimitGroupbuy(false);
                query.setLimitDiscount(true);
            }
                break;
            case 3: {// 有团购或者优惠
                query.setLimitGroupbuy(true);
                query.setLimitDiscount(true);
            }
                break;
        }

        if (lp != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(new OnPoiSearchEvent());
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 2000, true));//
            // 设置搜索区域为以lp点为圆心，其周围2000米范围
            /*
             * List<LatLonPoint> list = new ArrayList<LatLonPoint>(); list.add(lp);
             * list.add(AMapUtil.convertToLatLonPoint(Constants.BEIJING)); poiSearch.setBound(new
             * SearchBound(list));// 设置多边形poi搜索范围
             */
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }

    private class OnPoiSearchEvent implements PoiSearch.OnPoiSearchListener {
        @Override
        public void onPoiSearched(PoiResult result, int rCode) {
            if (rCode == 0) {
                if (result != null && result.getQuery() != null) {// 搜索poi的结果
                    poiResult = result;
                    if (result.getQuery().equals(query)) {// 是否是同一条
                        ArrayList<PoiItem> poiItems = result.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                        List<SuggestionCity> suggestionCities = result.getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                        if (poiItems != null && poiItems.size() > 0) {
                            final List<MapLocation> mapLocationList = new ArrayList<MapLocation>();
                            for (int i = 0; i < poiItems.size(); i++) {
                                MapLocation location = new MapLocation();
                                location.Title = poiItems.get(i).getTitle();
                                location.Details = poiItems.get(i).getSnippet();
                                mapLocationList.add(location);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    locationAdapter.addList(mapLocationList);
                                    mPoiResultLV.onFinishLoading(true);
                                }
                            });
                        } else if (suggestionCities != null && suggestionCities.size() > 0) {
                            // showSuggestCity(suggestionCities);
                        } else {
                            // ToastUtil.show(PoiAroundSearchActivity.this,
                            // R.string.no_result);
                            mPoiResultLV.onFinishLoading(false);
                        }
                    }
                } else {
                    ToastUtil.show(MapLocationActivity.this, R.string.no_result);
                }
            } else if (rCode == 27) {
                ToastUtil.show(MapLocationActivity.this, R.string.error_network);
            } else if (rCode == 32) {
                ToastUtil.show(MapLocationActivity.this, R.string.error_key);
            } else {
                ToastUtil.show(MapLocationActivity.this, getString(R.string.error_other) + rCode);
            }
        }

        private Marker detailMarker;// 显示Marker的详情

        @Override
        public void onPoiItemDetailSearched(PoiItemDetail result, int rCode) {
            // dissmissProgressDialog();// 隐藏对话框
            if (rCode == 0) {
                if (result != null) {// 搜索poi的结果
                    if (detailMarker != null) {
                        StringBuffer sb = new StringBuffer(result.getSnippet());
                        if ((result.getGroupbuys() != null && result.getGroupbuys().size() > 0)
                                || (result.getDiscounts() != null && result.getDiscounts().size() > 0)) {

                            if (result.getGroupbuys() != null && result.getGroupbuys().size() > 0) {// 取第一条团购信息
                                sb.append("\n团购：" + result.getGroupbuys().get(0).getDetail());
                            }
                            if (result.getDiscounts() != null && result.getDiscounts().size() > 0) {// 取第一条优惠信息
                                sb.append("\n优惠：" + result.getDiscounts().get(0).getDetail());
                            }
                        } else {
                            sb =
                                    new StringBuffer("地址：" + result.getSnippet() + "\n电话："
                                            + result.getTel() + "\n类型：" + result.getTypeDes());
                        }
                        // 判断poi搜索是否有深度信息
                        if (result.getDeepType() != null) {
                            // sb = getDeepInfo(result, sb);
                            // detailMarker.setSnippet(sb.toString());
                        } else {
                            // ToastUtil.show(PoiAroundSearchActivity.this,
                            // "此Poi点没有深度信息");
                        }
                    }

                } else {
                    // ToastUtil
                    // .show(PoiAroundSearchActivity.this, R.string.no_result);
                }
            } else if (rCode == 27) {
                // ToastUtil
                // .show(PoiAroundSearchActivity.this, R.string.error_network);
            } else if (rCode == 32) {
                // ToastUtil.show(PoiAroundSearchActivity.this, R.string.error_key);
            } else {
                // ToastUtil.show(PoiAroundSearchActivity.this,getString(R.string.error_other) +
                // rCode);
            }
        }
    }
}
