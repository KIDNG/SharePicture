package cn.com.se.sharepictrue.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.se.sharepictrue.R;

/**
 * 个人信息界面
 * Created by KIDNG on 2015/11/6.
 */
public class MineInfoActivity extends ActionBarActivity {
    @Bind(R.id.tb_mine_info_title)
    Toolbar mTbMineInfoTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);
        ButterKnife.bind(this);
        initToolBar();
    }

    private void initToolBar() {
        setSupportActionBar(mTbMineInfoTitle);
        mTbMineInfoTitle.setTitleTextColor(Color.WHITE);
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
