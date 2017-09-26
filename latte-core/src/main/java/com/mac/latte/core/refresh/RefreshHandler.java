package com.mac.latte.core.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mac.latte.core.net.RestClient;
import com.mac.latte.core.net.callback.ISuccess;
import com.mac.latte.core.recycler.DataConverter;
import com.mac.latte.core.recycler.MultiRecyclerAdapter;
import com.mac.latte.core.utils.Loger;

/**
 * RefreshHandler刷新帮助类
 * Created by douliu on 2017/9/22.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout mRefreshLayout;
    private final RecyclerView mRecyclerView;
    private final PagingBean mPagingBean;
    private final DataConverter mConverter;
    private MultiRecyclerAdapter mAdapter;

    private RefreshHandler(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView
            , DataConverter converter, PagingBean bean) {
        this.mRecyclerView = recyclerView;
        this.mConverter = converter;
        this.mPagingBean = bean;
        this.mRefreshLayout = refreshLayout;
        init();
    }

    private void init() {
        mRefreshLayout.setOnRefreshListener(this);

    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());
    }

    private void refresh() {
        mRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);

    }

    public void firstPager(String url) {
        RestClient.buider()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Loger.json("response: ", response);
                        JSONObject object = JSON.parseObject(response);
                        mPagingBean.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        mAdapter = MultiRecyclerAdapter.create(mConverter.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, mRecyclerView);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                }).build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
