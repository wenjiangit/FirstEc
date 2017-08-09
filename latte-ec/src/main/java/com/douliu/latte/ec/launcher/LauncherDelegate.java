package com.douliu.latte.ec.launcher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.douliu.latte.ec.R;
import com.douliu.latte.ec.R2;
import com.mac.latte.core.constans.LattePrefKey;
import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.utils.LattePreferences;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 *
 * Created by douliu on 2017/8/8.
 */

public class LauncherDelegate extends LatteDelegate {

    @BindView(R2.id.tv_timer)
    Button mTvTimer;

    private Disposable mDisposable;
    private int count = 5;

    @Override
    protected Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        startTimer();
    }

    @OnClick(R2.id.tv_timer)
    public void onViewClicked() {
        checkFirstEnter();
    }

    private void checkFirstEnter() {
        boolean hasEnter = LattePreferences.getFlag(LattePrefKey.IS_FIRST_ENTER_APP);
        if (!hasEnter) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            // TODO: 2017/8/9 跳转到主界面
            Toast.makeText(getContext(), "跳转到主界面", Toast.LENGTH_SHORT).show();
        }
    }

    private void startTimer() {
        //interval操作符执行定时任务,默认在新线程执行
        //UI操作需要切换到主线程执行
        mDisposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mTvTimer.setText(String.format(Locale.getDefault(),
                                "跳过\n%ds", count));
                        count--;
                        if (count < 0) {
                            mDisposable.dispose();
                            checkFirstEnter();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
