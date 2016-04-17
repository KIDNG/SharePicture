package cn.com.se.sharepictrue.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.bean.ShareListBean;
import cn.com.se.sharepictrue.quickadapter.BaseAdapterHelper;
import cn.com.se.sharepictrue.quickadapter.QuickAdapter;

/**
 * 点赞榜界面
 * Created by KIDNG on 2015/11/5.
 */
public class RankActivity extends ActionBarActivity {
    @Bind(R.id.tb_concern_title)
    Toolbar mTbConcernTitle;
    @Bind(R.id.ll_rank_list)
    ListView mLlRankList;
    @Bind(R.id.srl_rank_refresh)
    SwipeRefreshLayout mSrlRankRefresh;
    private List<ShareListBean> beans;
    private QuickAdapter<ShareListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ButterKnife.bind(this);
        initToolbar();
        initList();
    }

    private void initToolbar() {
        setSupportActionBar(mTbConcernTitle);
        mTbConcernTitle.setTitleTextColor(Color.WHITE);
    }

    Handler mHandler = new Handler();

    private void initList() {
        beans = new ArrayList<ShareListBean>();
        for (int i = 0; i < 8; i++) {//模拟数据
            ShareListBean bean = new ShareListBean();
            bean.name = "asd" + i;
            bean.commentNum = i;
            bean.content = "描述" + i;
            bean.introduction = "who i am";
            bean.isCollection = i % 2 == 0;
            bean.praiseNum = i;
            beans.add(bean);
        }
        mAdapter = new QuickAdapter<ShareListBean>(this, R.layout.item_share_list, beans) {
            @Override
            protected void convert(BaseAdapterHelper helper, ShareListBean item) {
                helper.setText(R.id.tv_item_share_name, item.name);
                helper.setText(R.id.tv_item_share_time, item.introduction);
                helper.setText(R.id.tv_item_share_content, item.content);
                helper.setText(R.id.tv_item_share_comment, item.commentNum+"");
                helper.setText(R.id.tv_item_share_praise, item.praiseNum+"");

                if(item.isCollection)
                    helper.setText(R.id.tv_item_share_collection, getResources().getString(R.string.item_share_collection_selected));
                else
                    helper.setText(R.id.tv_item_share_collection, getResources().getString(R.string.item_share_collection_normal));
                helper.getView(R.id.iv_item_share_avatar).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RankActivity.this, PersonalHomepageActivity.class);
                        startActivity(intent);
                    }
                });
                helper.getView(R.id.iv_item_share_collection).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(mLlRankList, "弹窗", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        };
        mLlRankList.setAdapter(mAdapter);
        mLlRankList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RankActivity.this, ShareDetailActivity.class);
                startActivity(intent);
            }
        });
        doRefresh();
    }

    private void doRefresh() {
        mSrlRankRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLlRankList.setEnabled(false);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSrlRankRefresh.setRefreshing(false);

                        mLlRankList.setEnabled(true);
                    }
                }, 2000);
            }
        });
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
