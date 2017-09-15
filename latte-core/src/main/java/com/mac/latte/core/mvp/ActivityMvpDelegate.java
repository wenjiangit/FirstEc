package com.mac.latte.core.mvp;

import android.os.Bundle;

/**
 *
 * Created by douliu on 2017/8/31.
 */

public interface ActivityMvpDelegate {

    void onCreate(Bundle savedInstanceState);

    void onResume();

    void onRestart();

    void onPause();

    void onDestroy();

    void onStart();

    void onStop();

}
