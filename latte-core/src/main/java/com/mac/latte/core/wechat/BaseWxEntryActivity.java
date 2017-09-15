package com.mac.latte.core.wechat;

import android.net.Uri;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mac.latte.core.R;
import com.mac.latte.core.net.RestClient;
import com.mac.latte.core.net.callback.ISuccess;
import com.mac.latte.core.utils.Loger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 *
 * Created by douliu on 2017/8/29.
 */

public abstract class BaseWxEntryActivity extends BaseWxActivity{

    private static final String TAG = "BaseWxEntryActivity";

    private static final String ACCESS_TOKEN = "access_token";
    private static final String OPEN_ID = "openid";

    protected abstract void onSignInSuccess(String userInfo);


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        int result;

        Toast.makeText(this, "baseresp.getType = " + resp.getType(), Toast.LENGTH_SHORT).show();

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                String code = ((SendAuth.Resp) resp).code;
                String authUrl = Uri.parse("https://api.weixin.qq.com/sns/oauth2/access_token")
                        .buildUpon()
                        .appendQueryParameter("appid", LatteWechat.getAppId())
                        .appendQueryParameter("secret", LatteWechat.getAppSecret())
                        .appendQueryParameter("code", code)
                        .appendQueryParameter("grant_type", "grant_type")
                        .build().toString();

                Loger.i(TAG,"authUrl : "+authUrl);

                getAuth(authUrl);

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = R.string.errcode_unsupported;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }


    private void getAuth(String authUrl) {
        RestClient.buider()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Loger.json(TAG, response);
                        JSONObject jsonObject = (JSONObject) JSON.parse(response);
                        String accessToken = jsonObject.getString(ACCESS_TOKEN);
                        String openid = jsonObject.getString(OPEN_ID);
                        String userInfoUrl = Uri.parse("https://api.weixin.qq.com/sns/userinfo")
                                .buildUpon()
                                .appendQueryParameter(ACCESS_TOKEN, accessToken)
                                .appendQueryParameter(OPEN_ID, openid)
                                .build()
                                .toString();

                        Loger.i(TAG, "userInfoUrl :" + userInfoUrl);
                        getUserInfo(userInfoUrl);
                    }
                })
                .build()
                .get();
    }

    private void getUserInfo(String userInfoUrl) {
        RestClient.buider()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Loger.json(TAG, response);
                        onSignInSuccess(response);
                    }
                })
                .build()
                .get();
    }
}
