package com.douliu.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.douliu.latte.ec.R;
import com.douliu.latte.ec.R2;
import com.douliu.latte.ec.api.Api;
import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.net.RestClient;
import com.mac.latte.core.net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;

/**
 * Description:分类详情页面
 * Author:douliu
 * Date:Created on 2017/9/25.
 */

public class SortContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "content_id";

    private int mContentId = -1;

    @BindView(R2.id.recycler)
    RecyclerView mRecyclerView;

    public static SortContentDelegate newInstance(int contentId) {
        Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        SortContentDelegate fragment = new SortContentDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mContentId = arguments.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_sort_content;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        initRecyclerView();
        initData();

    }

    private void initData() {
        RestClient.buider()
                .url(mContentId == 1 ? Api.SORT_CONTENT_DATA_1 : Api.SORT_CONTENT_DATA_2)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        List<SectionBean> sectionBeans = new ContentDataConverter(response).convert();
                        ContentAdapter adapter = new ContentAdapter(R.layout.item_sort_content
                                , R.layout.item_sort_header, sectionBeans);
                        mRecyclerView.setAdapter(adapter);
                    }
                }).build().get();

    }

    private void initRecyclerView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }
}
