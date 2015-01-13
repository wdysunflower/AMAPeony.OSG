package com.peony.osg.model.manager;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.peony.osg.R;
import com.peony.osg.log.CLog;
import com.peony.osg.model.object.MapLocation;
import com.peony.osg.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdynetposa on 2015/1/8.
 */
public class LocationManager {
    public interface LocationListener {
        public void UpdateData(List<MapLocation> locations);

        public void UpdateFail();
    }

    private Context mContext;

    // 定位
    private LocationManagerProxy mAMapLocationManager;
    private OnAMapLocationEvent locationEvent;
    private PoiSearch.Query query;// Poi查询条件类

    private PoiSearch poiSearch;
    private String deepType = ""; // poi搜索类型
    private int searchType = 0; // 搜索类型
    private PoiResult poiResult;

    public int getCurrentPage() {
        return currentPage;
    }

    private int currentPage = 0;
    private LocationListener mListener;

    public String oldKey;

    public LocationManager(Context context, LocationListener listener) {
        mContext = context;
        mListener = listener;
        initData();
    }

    private void initData() {
        locationEvent = new OnAMapLocationEvent();
        mAMapLocationManager = LocationManagerProxy.getInstance(mContext);
    }

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
                CLog.d("requestLocationData End - onLocationChanged");
                mAMapLocationManager.removeUpdates(locationEvent);
                double a = aMapLocation.getLatitude();
                double b = aMapLocation.getLongitude();
                LatLonPoint point = new LatLonPoint(a, b);
                String province = aMapLocation.getProvince();
                doSearchQuery(point, province);
            } else {
                Toast.makeText(mContext, R.string.get_location_error, Toast.LENGTH_SHORT).show();
                if (mListener != null) {
                    mListener.UpdateFail();
                }
            }
        }
    }

    /**
     * 开始进行poi搜索
     */
    private void doSearchQuery(LatLonPoint lp, String province) {
        CLog.d("doSearchQuery Begin");
        currentPage = 0;
        query = new PoiSearch.Query("", deepType, province);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        query.setLimitDiscount(false);
        query.setLimitGroupbuy(false);

        if (lp != null) {
            poiSearch = new PoiSearch(mContext, query);
            poiSearch.setOnPoiSearchListener(new OnPoiSearchEvent());
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 1000, true));
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }

    private class OnPoiSearchEvent implements PoiSearch.OnPoiSearchListener {
        @Override
        public void onPoiSearched(PoiResult result, int rCode) {
            CLog.d("onPoiSearched Result");
            if (rCode == 0) {
                if (result != null && result.getQuery() != null) {// 搜索poi的结果
                    poiResult = result;
                    if (result.getQuery().equals(query)) {// 是否是同一条
                        ArrayList<PoiItem> poiItems = result.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                        List<SuggestionCity> suggestionCities = result.getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                        if (poiItems != null && poiItems.size() > 0) {
                            final List<MapLocation> mapLocationList = new ArrayList<MapLocation>();
                            for (int i = 0; i < poiItems.size(); i++) {
                                PoiItem item = poiItems.get(i);
                                if (!TextUtils.isEmpty(oldKey) && item.getPoiId().equals(oldKey)) {
                                    continue;
                                }
                                MapLocation location = new MapLocation();
                                location.Key = item.getPoiId();
                                location.Title = item.getTitle();
                                location.Details = item.getSnippet();
                                mapLocationList.add(location);
                            }

                            if (mListener != null) {
                                mListener.UpdateData(mapLocationList);
                            }
                        } else if (suggestionCities != null && suggestionCities.size() > 0) {
                            if (mListener != null) {
                                mListener.UpdateFail();
                            }
                        } else {
                            if (mListener != null) {
                                mListener.UpdateFail();
                            }
                        }
                    }
                } else {
                    ToastUtil.show(mContext, R.string.no_result);
                    if (mListener != null) {
                        mListener.UpdateFail();
                    }
                }
            } else if (rCode == 27) {
                ToastUtil.show(mContext, R.string.error_network);
                if (mListener != null) {
                    mListener.UpdateFail();
                }
            } else if (rCode == 32) {
                ToastUtil.show(mContext, R.string.error_key);
                if (mListener != null) {
                    mListener.UpdateFail();
                }
            } else {
                ToastUtil.show(mContext, mContext.getString(R.string.error_other) + rCode);
                if (mListener != null) {
                    mListener.UpdateFail();
                }
            }
        }

        @Override
        public void onPoiItemDetailSearched(PoiItemDetail result, int rCode) {

        }
    }

    public void searchFirst() {
        CLog.d("requestLocationData Begin");
        mAMapLocationManager.requestLocationData(LocationProviderProxy.AMapNetwork, 60 * 1000, 10,
                locationEvent);
    }

    public void cleanData() {
        mAMapLocationManager.removeUpdates(locationEvent);
    }

    public void Refresh() {
        if (query != null && poiSearch != null && poiResult != null) {
            CLog.d("onRefresh Begin");
            if (poiResult.getPageCount() - 1 > currentPage) {
                currentPage++;

                query.setPageNum(currentPage);// 设置查后一页
                poiSearch.searchPOIAsyn();
            } else {
                // ToastUtil.show(MapLocationActivity.this, R.string.no_result);
            }
        }
    }
}
