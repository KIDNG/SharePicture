package cn.com.se.sharepictrue.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.bean.PersonBean;
import cn.com.se.sharepictrue.bean.ShareListBean;
import cn.com.se.sharepictrue.quickadapter.BaseAdapterHelper;
import cn.com.se.sharepictrue.quickadapter.QuickAdapter;

/**
 * 我的关注、我的粉丝界面
 * Created by KIDNG on 2015/11/4.
 */
public class ConcernActivity extends ActionBarActivity {
    public static final String CONCERN_WHAT_TITLE = "concern_title_key";
    public static final String CONCERN_BUNDLE_KEY = "concern_bundle_key";
    @Bind(R.id.tb_concern_title)
    Toolbar mTbConcernTitle;
    @Bind(R.id.lv_concern_list)
    ListView mLvConcernList;
    @Bind(R.id.srl_concern_refresh)
    SwipeRefreshLayout mSrlConcernRefresh;
    private List<PersonBean> mBeans;
    private QuickAdapter<PersonBean> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concern);
        ButterKnife.bind(this);

        setToolBar();
        initList();
    }

    private void setToolBar() {
        Intent intent = getIntent();
        String titleText = getResources().getString(R.string.concern_default_title);
        if(null != intent && !intent.getStringExtra(CONCERN_WHAT_TITLE).isEmpty())
            titleText = intent.getStringExtra(CONCERN_WHAT_TITLE);
        mTbConcernTitle.setTitle(titleText);
        mTbConcernTitle.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mTbConcernTitle);
    }
    Handler mHandler = new Handler();
    private void initList() {
        mBeans = new ArrayList<PersonBean>();
        for(int i = 0;i<10;i++){
            //模拟数据
            PersonBean bean = new PersonBean();
            bean.name="zhutou";
            bean.introduction="12312r4124124124r5234t534636234532464345645";
            mBeans.add(bean);
        }

        mAdapter = new QuickAdapter<PersonBean>(this,R.layout.item_person_list,mBeans) {
            @Override
            protected void convert(BaseAdapterHelper helper, PersonBean item) {
                TextView concern = helper.getView(R.id.tv_person_list_concern);
                if(helper.getPosition()%2 == 1){
                    //未关注
                    concern.setBackgroundResource(R.drawable.bg_person_list_no_concern);
                    concern.setTextColor(getResources().getColor(R.color.colorPrimaryText));
                }else{
                    //已关注
                    concern.setBackgroundResource(R.drawable.bg_person_list_concerned);
                    concern.setTextColor(Color.WHITE);
                }
                helper.setText(R.id.tv_person_list_name,item.name);
                helper.setText(R.id.tv_person_list_introduction,item.introduction);
            }
        };
        mLvConcernList.setAdapter(mAdapter);

        doRefresh();
    }

    private void doRefresh() {
        mSrlConcernRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLvConcernList.setEnabled(false);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSrlConcernRefresh.setRefreshing(false);
                        //改数据
                        mLvConcernList.setEnabled(true);
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
