package com.mac.firstec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.net.RestClient;
import com.mac.latte.core.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * Created by mac on 2017/8/2.
 */

public class ExampleDelegate extends LatteDelegate {

    private static final String TAG = "ExampleDelegate";
    @Override
    protected Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

        rootView.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request(v);
            }
        });

    }

    @OnClick(R.id.test)
    public void request(View view) {

        Toast.makeText(getContext(),"hahahha",Toast.LENGTH_SHORT).show();

        RestClient.buider()
                .url("https://www.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();

                        Log.d(TAG, "onSuccess: "+response);
                    }
                })
                .build()
                .get();
    }

}
