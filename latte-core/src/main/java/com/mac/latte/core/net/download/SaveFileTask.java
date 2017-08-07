package com.mac.latte.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.mac.latte.core.app.Latte;
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

    private  ISuccess success;
    private  IRequest request;

    SaveFileTask(ISuccess success, IRequest request) {
        this.success = success;
        this.request = request;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        final String name = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        String extension = (String) params[3];
        final InputStream is = body.byteStream();
        if (TextUtils.isEmpty(downloadDir)) {
            downloadDir = "downloads";
        }
        if (TextUtils.isEmpty(extension)) {
            extension = "";
        }

        if (TextUtils.isEmpty(name)) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
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

    /**
     * 如果下载的文件是apk结尾的,则自动进行安装
     * @param file 下载的文件
     */
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
