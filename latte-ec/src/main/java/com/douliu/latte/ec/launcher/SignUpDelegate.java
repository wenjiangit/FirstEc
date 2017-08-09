package com.douliu.latte.ec.launcher;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.douliu.latte.ec.R;
import com.douliu.latte.ec.R2;
import com.mac.latte.core.delegate.LatteDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac on 2017/8/9.
 */

public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_name)
    TextInputEditText mEditName;
    @BindView(R2.id.edit_phone)
    TextInputEditText mEditPhone;
    @BindView(R2.id.edit_email)
    TextInputEditText mEditEmail;
    @BindView(R2.id.edit_password)
    TextInputEditText mEditPassword;
    @BindView(R2.id.edit_re_password)
    TextInputEditText mEditRePassword;
    @BindView(R2.id.tv_sign_up)
    Button mTvSignUp;
    @BindView(R2.id.tv_go_sign_in)
    TextView mTvGoSignIn;

    @Override
    protected Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }


    private boolean checkForm() {
        String name = mEditName.getText().toString();
        String phone = mEditPhone.getText().toString();
        String email = mEditEmail.getText().toString();
        String password = mEditPassword.getText().toString();
        String rePassword = mEditRePassword.getText().toString();

        boolean valid = true;

        if (TextUtils.isEmpty(name)) {
            mEditName.setError("用户名不能为空！");
            valid = false;
        } else {
            mEditName.setError(null);
        }

        if (TextUtils.isEmpty(phone) || phone.length() != 11) {
            mEditPhone.setError("手机号不合法！");
            valid = false;
        } else {
            mEditPhone.setError(null);
        }

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

        if (TextUtils.isEmpty(rePassword) || !rePassword.equals(password)) {
            mEditRePassword.setError("密码校验失败！！");
            valid = false;
        } else {
            mEditRePassword.setError(null);
        }

        return valid;
    }


    @OnClick(R2.id.tv_sign_up)
    public void onViewClicked() {
        if (checkForm()) {
            Toast.makeText(getContext(),"校验通过",Toast.LENGTH_SHORT).show();
        }
    }
}
