package com.douliu.latte.ec.account;

import com.mac.latte.core.constans.LattePrefKey;
import com.mac.latte.core.utils.LattePreferences;

/**
 *
 * Created by douliu on 2017/8/14.
 */

public class AccountManager {

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

    /**
     * 保存登录标识
     * @param flag 标记
     */
    public static void setSignFlag(boolean flag) {
        LattePreferences.setAppFlag(LattePrefKey.HAS_SIGN_IN, flag);
    }

    /**
     * 判断是否登录
     * @return 登录返回true
     */
    public static boolean isSignIn() {
        return LattePreferences.getAppFlag(LattePrefKey.HAS_SIGN_IN);
    }

}
