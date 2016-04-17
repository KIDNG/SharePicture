package cn.com.se.sharepictrue.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.se.sharepictrue.R;

/**
 * 分享图片详情界面
 * Created by KIDNG on 2015/11/3.
 */
public class ShareDetailActivity extends ActionBarActivity {
    @Bind(R.id.tb_share_detail_toolbar)
    Toolbar mTbShareDetailToolbar;
    @Bind(R.id.lv_share_detail_list)
    ListView mLvShareDetailList;
    @Bind(R.id.tv_share_detail_collection)
    TextView mTvShareDetailCollection;
    @Bind(R.id.tv_share_detail_comment)
    TextView mTvShareDetailComment;
    @Bind(R.id.tv_share_detail_praise)
    TextView mTvShareDetailPraise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_detail);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mTbShareDetailToolbar);
        mTbShareDetailToolbar.setTitleTextColor(Color.WHITE);
    }

    @OnClick({R.id.tv_share_detail_collection,R.id.tv_share_detail_comment,R.id.tv_share_detail_praise})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_share_detail_comment:
                Intent intent=new Intent(this,SendMessageActivity.class);
                startActivity(intent);
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
