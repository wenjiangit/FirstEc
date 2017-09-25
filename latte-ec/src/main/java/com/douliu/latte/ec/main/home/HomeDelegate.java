package com.douliu.latte.ec.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.douliu.latte.ec.R;
import com.douliu.latte.ec.R2;
import com.douliu.latte.ec.api.Api;
import com.joanzapata.iconify.widget.IconTextView;
import com.mac.latte.core.delegate.bottom.BottomItemDelegate;
import com.mac.latte.core.net.RestClient;
import com.mac.latte.core.net.callback.ISuccess;
import com.mac.latte.core.recycler.MultipleFields;
import com.mac.latte.core.recycler.MultipleItemEntity;
import com.mac.latte.core.refresh.RefreshHandler;
import com.mac.latte.core.utils.Loger;

import java.util.List;

import butterknife.BindView;

/**
 *
 * Created by douliu on 2017/9/22.
 */

public class HomeDelegate extends BottomItemDelegate implements View.OnClickListener{

    @BindView(R2.id.recycler)
    RecyclerView mRecycler;
    @BindView(R2.id.sr_refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.icon_scan)
    IconTextView mIconScan;
    @BindView(R2.id.edit_search)
    EditText mEditSearch;
    @BindView(R2.id.icon_more)
    IconTextView mIconMore;
    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    private RefreshHandler mRefreshHandler;

    public static HomeDelegate newInstance() {
        Bundle args = new Bundle();
        HomeDelegate fragment = new HomeDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_home;
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light
        );
        mRefreshLayout.setProgressViewOffset(true, 100, 300);
    }

    private void initRecyclerView() {
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout,mRecycler,new HomeDataConverter());
        mRefreshHandler.firstPager(Api.INDEX);
        mIconScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RestClient.buider()
                .url(Api.INDEX)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Loger.json("json", response);
                        HomeDataConverter converter = new HomeDataConverter();
                        converter.setJsonData(response);
                        List<MultipleItemEntity> entities = converter.convert();
                        Loger.d("实体: ", entities);
                        String url = entities.get(1).getField(MultipleFields.IMAGE_URL);
                        Loger.d("url: ", url);
                    }
                }).build()
                .get();
    }
}
