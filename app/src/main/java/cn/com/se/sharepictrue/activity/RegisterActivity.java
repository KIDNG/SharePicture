package cn.com.se.sharepictrue.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.utils.LoginUtils;

/**
 * 注册界面
 * Created by KIDNG on 2015/11/3.
 */
public class RegisterActivity extends Activity {
    @Bind(R.id.til_register_phone)
    TextInputLayout mTilRegisterPhone;
    @Bind(R.id.til_register_password)
    TextInputLayout mTilRegisterPassword;
    @Bind(R.id.btn_register_register)
    Button mBtnRegisterRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTilRegisterPassword.setHint(getResources().getString(R.string.register_enter_password));
        mTilRegisterPhone.setHint(getResources().getString(R.string.register_enter_phone));
        setTextInputListener();
    }

    private void setTextInputListener() {
        mTilRegisterPassword.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTilRegisterPassword.setErrorEnabled(false);
            }
        });
        mTilRegisterPassword.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTilRegisterPassword.setErrorEnabled(false);
            }
        });
        mTilRegisterPhone.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTilRegisterPhone.setErrorEnabled(false);
            }
        });
    }

    @OnClick(R.id.btn_register_register)
    public void onClick(View v) {
        hideKeyboard();
        String username = mTilRegisterPhone.getEditText().getText().toString();
        String password = mTilRegisterPassword.getEditText().getText().toString();
        if (!LoginUtils.validatePhone(username)) {
            mTilRegisterPhone.setError(getResources().getString(R.string.phone_error));
        } else if (!LoginUtils.validatePassword(password)) {
            mTilRegisterPhone.setErrorEnabled(false);
            mTilRegisterPassword.setError(getResources().getString(R.string.password_length_error));
        } else {
            mTilRegisterPhone.setErrorEnabled(false);
            mTilRegisterPassword.setErrorEnabled(false);
            doRegister();
        }
    }

    private void doRegister() {

    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
