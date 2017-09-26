package com.douliu.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.choices.divider.DividerItemDecoration;
import com.douliu.latte.ec.R;
import com.douliu.latte.ec.R2;
import com.douliu.latte.ec.api.Api;
import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.net.RestClient;
import com.mac.latte.core.net.callback.ISuccess;
import com.mac.latte.core.recycler.MultipleItemEntity;
import com.mac.latte.core.utils.Loger;

import java.util.List;

import butterknife.BindView;

/**
 * Description:分类列表
 * Author:douliu
 * Date:Created on 2017/9/25.
 */

public class SortListDelegate extends LatteDelegate{

    @BindView(R2.id.recycler)
    RecyclerView mRecyclerView;

    public static SortListDelegate newInstance() {
        Bundle args = new Bundle();
        SortListDelegate fragment = new SortListDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_sort_list;
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.addItemDecoration(new DividerItemDecoration());
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.buider()
                .url(Api.SORT_LIST_DATA)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Loger.json("sort_list", response);
                        List<MultipleItemEntity> entities = new SortListDataConverter()
                                .setJsonData(response)
                                .getEntities();
                        SortListAdapter adapter = new SortListAdapter(entities,getParentDelegate());
                        mRecyclerView.setAdapter(adapter);
                    }
                }).build().get();
    }


}
