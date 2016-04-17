package cn.com.se.sharepictrue.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.utils.DataCleanManager;

/**
 * 设置界面
 * Created by KIDNG on 2015/11/5.
 */
public class SettingActivity extends ActionBarActivity {
    @Bind(R.id.tb_setting_title)
    Toolbar mTbSettingTitle;
    @Bind(R.id.switch_setting_hide)
    SwitchCompat mSwitchSettingHide;
    @Bind(R.id.tv_seeting_manager_title)
    TextView mTvSeetingManagerTitle;
    @Bind(R.id.ll_setting_ban)
    LinearLayout mLlSettingBan;
    @Bind(R.id.ll_setting_hind_key)
    LinearLayout mLlSettingHindKey;
    @Bind(R.id.ll_setting_clear)
    LinearLayout mLlSettingClear;
    @Bind(R.id.ll_setting_about)
    LinearLayout mLlSettingAbout;
    @Bind(R.id.tv_setting_cache_size)
    TextView mTvSettingCacheSize;
    @Bind(R.id.ll_setting_mine_info)
    LinearLayout mLlSettingMineInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    private void initToolbar() {
        setSupportActionBar(mTbSettingTitle);
        mTbSettingTitle.setTitleTextColor(Color.WHITE);
    }

    private void initView() {
        getCacheSize();
    }

    private void getCacheSize() {
        try {
            mTvSettingCacheSize.setText(DataCleanManager.getCacheSize(getCacheDir()));
        } catch (Exception e) {
            e.printStackTrace();
            mTvSettingCacheSize.setText(getResources().getString(R.string.setting_no_cache));
        }
    }

    @OnClick({R.id.ll_setting_clear, R.id.ll_setting_ban, R.id.ll_setting_hind_key,
            R.id.ll_setting_about,R.id.ll_setting_mine_info})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_setting_clear:
                DataCleanManager.cleanInternalCache(this);
                getCacheSize();
                break;
            case R.id.ll_setting_ban:
                Intent banIntent = new Intent(this, ManagerUserActivity.class);
                startActivity(banIntent);
                break;
            case R.id.ll_setting_hind_key:
                Intent hideIntent = new Intent(this, HideKeyActivity.class);
                startActivity(hideIntent);
                break;
            case R.id.ll_setting_about:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.ll_setting_mine_info:
                Intent mineInfoIntent = new Intent(this, MineInfoActivity.class);
                startActivity(mineInfoIntent);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
