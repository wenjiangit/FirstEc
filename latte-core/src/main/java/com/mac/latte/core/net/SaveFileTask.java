package com.mac.latte.core.net;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.mac.latte.core.app.Latte;
import com.mac.latte.core.net.callback.IError;
import com.mac.latte.core.net.callback.IFailure;
import com.mac.latte.core.net.callback.IRequest;
import com.mac.latte.core.net.callback.ISuccess;
import com.mac.latte.core.utils.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 *
 * Created by mac on 2017/8/6.
 */

class SaveFileTask extends AsyncTask<Object, Void, File> {

    private String mDownloadDir;
    private String mExtension;
    private String mName;
    private ResponseBody mBody;

    private  ISuccess success;
    private  IRequest request;

    SaveFileTask(ISuccess success, IRequest request) {
        this.success = success;
        this.request = request;
    }

    @Override
    protected File doInBackground(Object... params) {
        mDownloadDir = (String) params[0];
        mName = (String) params[1];
        mBody = (ResponseBody) params[2];
        mExtension = (String) params[3];
        InputStream is = mBody.byteStream();
        if (TextUtils.isEmpty(mDownloadDir)) {
            mDownloadDir = "downloads";
        }
        if (TextUtils.isEmpty(mExtension)) {
            mExtension = "";
        }

        if (TextUtils.isEmpty(mName)) {
            return FileUtil.writeToDisk(is, mDownloadDir, mExtension.toUpperCase(), mExtension);

        } else {
            return FileUtil.writeToDisk(is, mDownloadDir, mName);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (success != null) {
            success.onSuccess(file.getAbsolutePath());
        }

        if (request != null) {
            request.onRequestEnd();
        }

        autoInstallApk(file);

    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplication().startActivity(intent);
        }
    }
}
