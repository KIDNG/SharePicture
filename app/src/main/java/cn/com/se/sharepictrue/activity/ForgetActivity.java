package cn.com.se.sharepictrue.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.utils.LoginUtils;

/**
 * 修改密码界面
 * Created by KIDNG on 2015/11/3.
 */
public class ForgetActivity extends Activity {

    @Bind(R.id.til_forget_phone)
    TextInputLayout mTilForgetPhone;
    @Bind(R.id.til_forget_old_password)
    TextInputLayout mTilForgetOldPassword;
    @Bind(R.id.til_forget_new_password)
    TextInputLayout mTilForgetNewPassword;
    @Bind(R.id.til_forget_ensure_password)
    TextInputLayout mTilForgetEnsurePassword;
    @Bind(R.id.btn_forget_forget)
    Button mBtnForgetForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mTilForgetPhone.setHint(getResources().getString(R.string.forget_enter_phone));
        mTilForgetOldPassword.setHint(getResources().getString(R.string.forget_enter_old_password));
        mTilForgetNewPassword.setHint(getResources().getString(R.string.forget_enter_new_password));
        mTilForgetEnsurePassword.setHint(getResources().getString(R.string.forget_enter_ensure_password));
        setTextInputListener();
    }

    private void setTextInputListener() {
        mTilForgetPhone.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTilForgetPhone.setErrorEnabled(false);
            }
        });
        mTilForgetOldPassword.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTilForgetOldPassword.setErrorEnabled(false);
            }
        });
        mTilForgetNewPassword.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTilForgetNewPassword.setErrorEnabled(false);
            }
        });
        mTilForgetEnsurePassword.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTilForgetEnsurePassword.setErrorEnabled(false);
            }
        });
    }

    @OnClick(R.id.btn_forget_forget)
    public void onClick(View v) {
        hideKeyboard();

        String username = mTilForgetPhone.getEditText().getText().toString();
        String oldPassword = mTilForgetOldPassword.getEditText().getText().toString();
        String newPassword = mTilForgetNewPassword.getEditText().getText().toString();
        String ensurePassword = mTilForgetEnsurePassword.getEditText().getText().toString();

        if (!LoginUtils.validatePhone(username)) {
            mTilForgetPhone.setError(getResources().getString(R.string.phone_error));
        } else if (!LoginUtils.validatePassword(oldPassword)) {
            mTilForgetPhone.setErrorEnabled(false);
            mTilForgetOldPassword.setError(getResources().getString(R.string.password_length_error));
        } else if(!LoginUtils.validatePassword(newPassword)){
            mTilForgetPhone.setErrorEnabled(false);
            mTilForgetOldPassword.setErrorEnabled(false);
            mTilForgetNewPassword.setError(getResources().getString(R.string.password_length_error));
        }else if (LoginUtils.validateSamePassword(oldPassword, newPassword)) {
            mTilForgetPhone.setErrorEnabled(false);
            mTilForgetOldPassword.setErrorEnabled(false);
            mTilForgetNewPassword.setError(getResources().getString(R.string.password_ensure_same_error));
        } else if (!LoginUtils.validateEnsurePassword(newPassword, ensurePassword)) {
            mTilForgetPhone.setErrorEnabled(false);
            mTilForgetOldPassword.setErrorEnabled(false);
            mTilForgetNewPassword.setErrorEnabled(false);
            mTilForgetEnsurePassword.setError(getResources().getString(R.string.password_ensure_no_same_error));
        } else {
            mTilForgetPhone.setErrorEnabled(false);
            mTilForgetOldPassword.setErrorEnabled(false);
            mTilForgetNewPassword.setErrorEnabled(false);
            mTilForgetEnsurePassword.setErrorEnabled(false);
            doChangePassword();
        }
    }

    private void doChangePassword() {

    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
