package cn.com.se.sharepictrue.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.utils.SystemUtils;

/**
 * 关于界面
 * Created by KIDNG on 2015/11/5.
 */
public class AboutActivity extends ActionBarActivity {
    @Bind(R.id.tb_about_title)
    Toolbar mTbAboutTitle;
    @Bind(R.id.tv_about_version)
    TextView mTvAboutVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initToolBar();
        setVersion();
    }

    private void setVersion() {
        mTvAboutVersion.setText(SystemUtils.getVersion(this));
    }

    private void initToolBar() {
        setSupportActionBar(mTbAboutTitle);
        mTbAboutTitle.setTitleTextColor(Color.WHITE);
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
