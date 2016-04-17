package cn.com.se.sharepictrue.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.utils.LoginUtils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 登录界面
 * Created by KIDNG on 2015/11/2.
 */
public class LoginActivity extends Activity {
    @Bind(R.id.til_login_phone)
    TextInputLayout mTilLoginPhone;
    @Bind(R.id.til_login_password)
    TextInputLayout mTilLoginPassword;
    @Bind(R.id.btn_login_login)
    Button mBtnLoginLogin;
    @Bind(R.id.iv_forget_avatar)
    CircleImageView mIvLoginAvatar;
    @Bind(R.id.tv_login_register)
    TextView mTvLoginRegister;
    @Bind(R.id.tv_login_forget)
    TextView mTvLoginForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mIvLoginAvatar.setFocusable(true);
        mIvLoginAvatar.requestFocus();
        mTilLoginPhone.setHint(getResources().getString(R.string.login_enter_phone));
        mTilLoginPassword.setHint(getResources().getString(R.string.login_enter_password));
        setTextInputListener();
    }

    private void setTextInputListener() {
        mTilLoginPhone.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTilLoginPhone.setErrorEnabled(false);
            }
        });
        mTilLoginPassword.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTilLoginPassword.setErrorEnabled(false);
            }
        });
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @OnClick({R.id.btn_login_login,R.id.tv_login_register,R.id.tv_login_forget})
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_login_login:
                logicalForLogin();
                break;
            case R.id.tv_login_register:
                intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_forget:
                intent = new Intent(this,ForgetActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void logicalForLogin() {
        hideKeyboard();
        String username = mTilLoginPhone.getEditText().getText().toString();
        String password = mTilLoginPassword.getEditText().getText().toString();
        if (!LoginUtils.validatePhone(username)) {
            mTilLoginPhone.setError(getResources().getString(R.string.phone_error));
        } else if (!LoginUtils.validatePassword(password)) {
            mTilLoginPhone.setErrorEnabled(false);
            mTilLoginPassword.setError(getResources().getString(R.string.password_length_error));
        } else {
            mTilLoginPhone.setErrorEnabled(false);
            mTilLoginPassword.setErrorEnabled(false);
            doLogin();
        }
    }

    private void doLogin() {

    }
}
