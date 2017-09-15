package com.douliu.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.douliu.latte.ec.account.AccountManager;
import com.douliu.latte.ec.data.UserProfile;
import com.douliu.latte.ec.db.DbHelper;
import com.mac.latte.core.utils.Loger;


/**
 * Created by douliu on 2017/8/14.
 */

public class SignHandler {

    private static final String TAG = "SignHandler";

    public static void signIn(String response) {
        try {
            JSONObject jobj = (JSONObject) JSON.parse(response);
            JSONObject data = jobj.getJSONObject("data");
            UserProfile profile = JSON.parseObject(data.toString(), UserProfile.class);
            Loger.i(TAG, "profile :" + profile);
            DbHelper.save(UserProfile.class, profile);
            profile.save();
            AccountManager.setSignFlag(true);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static void signUp(String response) {
        signIn(response);
    }


}
