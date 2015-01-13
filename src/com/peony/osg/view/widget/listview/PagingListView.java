package com.peony.osg.view.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;
import com.peony.osg.view.widget.loading.PageLoadingView;

import java.util.List;

/**
 * Created by wdynetposa on 2014/12/5.
 */
public class PagingListView extends ListView {

    public interface onPagingListener {
        void onLoadMoreItems();
    }

    private boolean isLoading;
    private boolean hasMoreItems;
    private onPagingListener mPagingL;
    private PageLoadingView mLoadingView;

    private OnScrollListener onScrollListener;

    public PagingListView(Context context) {
        super(context);
        init();
    }

    public PagingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PagingListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public void setPagingListener(onPagingListener mPagingL) {
        this.mPagingL = mPagingL;
    }

    public void setHasMoreItems(boolean hasMoreItems) {
        this.hasMoreItems = hasMoreItems;
        if(!this.hasMoreItems) {
            removeFooterView(mLoadingView);
        }
    }

    public boolean hasMoreItems() {
        return this.hasMoreItems;
    }

    public void onFinishLoading(boolean hasMoreItems) {
        setHasMoreItems(hasMoreItems);
        setIsLoading(false);
    }

    private void init() {
        isLoading = false;
        mLoadingView = new PageLoadingView(getContext());
        addFooterView(mLoadingView);
        super.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //Dispatch to child OnScrollListener
                if (onScrollListener != null) {
                    onScrollListener.onScrollStateChanged(view, scrollState);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                //Dispatch to child OnScrollListener
                if (onScrollListener != null) {
                    onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }

                if (totalItemCount > 0) {
                    int lastVisibleItem = firstVisibleItem + visibleItemCount;
                    if (!isLoading && hasMoreItems && (lastVisibleItem == totalItemCount)) {
                        if (mPagingL != null) {
                            isLoading = true;
                            mPagingL.onLoadMoreItems();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setOnScrollListener(OnScrollListener listener) {
        onScrollListener = listener;
    }
}

