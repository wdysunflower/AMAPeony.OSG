package com.peony.osg.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.peony.osg.R;
import com.peony.osg.model.object.MapLocation;
import com.peony.osg.view.holder.MapLocationHolder;

/**
 * Created by wdynetposa on 2014/12/5.
 */
public class MapLocationAdapter extends ListBaseAdapter<MapLocation, MapLocationHolder> {

    public MapLocationAdapter(Context context) {
        super(context);
    }

    @Override
    protected View getView(ViewGroup viewGroup) {
        return mInflater.inflate(R.layout.listitem_map_location, viewGroup, false);
    }

    @Override
    protected MapLocationHolder getViewHolder(View view) {
        MapLocationHolder holder = new MapLocationHolder();
        holder.TitleTV = (TextView) view.findViewById(R.id.location_title_tv);
        holder.DetailsTV = (TextView) view.findViewById(R.id.location_details_tv);
        return holder;
    }

    @Override
    protected void setView(int position, MapLocation obj, MapLocationHolder holder) {
        holder.TitleTV.setText(obj.Title);
        holder.DetailsTV.setText(obj.Details);
    }
}
