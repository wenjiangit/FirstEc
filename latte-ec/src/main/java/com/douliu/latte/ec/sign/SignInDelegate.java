package com.douliu.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.douliu.latte.ec.R;
import com.douliu.latte.ec.R2;
import com.douliu.latte.ec.api.Api;
import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.net.RestClient;
import com.mac.latte.core.net.callback.ISuccess;
import com.mac.latte.core.utils.Loger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 *
 * Created by douliu on 2017/8/10.
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_email)
    TextInputEditText mEditEmail;
    @BindView(R2.id.edit_password)
    TextInputEditText mEditPassword;

    private ISignListener mSignListener;

    public static SignInDelegate newInstance() {
        Bundle args = new Bundle();
        SignInDelegate fragment = new SignInDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mSignListener = ((ISignListener) activity);
        }
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R2.id.tv_go_sign_up)
    void linkSignUp() {
        start(SignUpDelegate.newInstance());
    }


    @OnClick(R2.id.tv_sign_in)
    void doSignIn() {
        if (checkForm()) {
            RestClient.buider()
                    .url(Api.USER_PROFILE)
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Loger.i(TAG, response);
                            SignHandler.signIn(response,mSignListener);
                        }
                    }).build()
                    .get();
        }
    }

    /**
     * 检查登录格式
     * @return 检查通过返回true,否则返回false
     */
    private boolean checkForm() {

        String email = mEditEmail.getText().toString();
        String password = mEditPassword.getText().toString();

        boolean valid = true;

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEditEmail.setError("邮箱地址不合法！");
            valid = false;
        } else {
            mEditEmail.setError(null);
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mEditPassword.setError("密码不能少于6位！");
            valid = false;
        } else {
            mEditPassword.setError(null);
        }

        return valid;
    }

}
