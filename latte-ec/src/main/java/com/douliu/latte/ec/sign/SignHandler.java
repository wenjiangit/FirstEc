package com.douliu.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.douliu.latte.ec.account.AccountManager;
import com.douliu.latte.ec.data.UserProfile;
import com.mac.latte.core.utils.Loger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Created by douliu on 2017/8/14.
 */

public class SignHandler {

    private static final String TAG = "SignHandler";

    public static void signIn(String response,ISignListener listener) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            UserProfile profile = JSON.parseObject(data.toString(), UserProfile.class);
            Loger.i(TAG, "profile :" + profile);
//            DbHelper.save(UserProfile.class, profile);
            profile.save();
            AccountManager.setSignFlag(true);
            if (listener != null) {
                listener.onSignInSuccess();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public static void signUp(String response,ISignListener listener) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            UserProfile profile = JSON.parseObject(data.toString(), UserProfile.class);
            Loger.i(TAG, "profile :" + profile);
//            DbHelper.save(UserProfile.class, profile);
            AccountManager.setSignFlag(true);
            if (listener != null) {
                listener.onSignUpSuccess();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
