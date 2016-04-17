package cn.com.se.sharepictrue.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.bean.HomeShareBean;
import cn.com.se.sharepictrue.bean.ShareListBean;
import cn.com.se.sharepictrue.quickadapter.BaseAdapterHelper;
import cn.com.se.sharepictrue.quickadapter.QuickAdapter;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人主页界面
 * Created by KIDNG on 2015/11/2.
 */
public class PersonalHomepageActivity extends Activity {
    @Bind(R.id.lv_personal_list)
    ListView mLvPersonalList;
    @Bind(R.id.iv_personal_back)
    ImageView mIvPersonalBack;
    @Bind(R.id.iv_personal_back_top)
    ImageView mIvPersonalBackTop;
    @Bind(R.id.tv_personal_name_top)
    TextView mTvPersonalNameTop;
    @Bind(R.id.rl_personal_top)
    RelativeLayout mRlPersonalTop;
    private View mHeader;
    private List<HomeShareBean> beans;
    private QuickAdapter<HomeShareBean> mAdapter;
    private int headerHeight = 0;
    private int backHeight = 0;
    public static String HOMEPAGE_KEY = "key";
    public static String HOMEPAGE_ME = "me";
    public static String HOMEPAGE_OTHER = "other";
    private boolean isMineHomepage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_homepage);
        ButterKnife.bind(this);
        initWhatHomepage();
        initHeader();
        initList();

    }

    private void initWhatHomepage() {
        Intent intent = getIntent();
        String str = intent.getExtras().getString(HOMEPAGE_KEY,HOMEPAGE_ME);
       // String str = intent.getStringExtra(HOMEPAGE_KEY);
        if(str.equals(HOMEPAGE_ME)) isMineHomepage = true;
        else isMineHomepage = false;
    }

    private void initList() {
        beans = new ArrayList<>();
        for (int i = 0; i < 8; i++) {//模拟数据
            HomeShareBean bean = new HomeShareBean();
            bean.name = "asd" + i;
            bean.commentNum = i;
            bean.content = "描述" + i;
            bean.introduction = "who i am";
            bean.isCollection = i % 2 == 0;
            bean.praiseNum = i;
            beans.add(bean);
        }
        mAdapter = new QuickAdapter<HomeShareBean>(this, R.layout.item_share_list, beans) {
            @Override
            protected void convert(BaseAdapterHelper helper, final HomeShareBean item) {
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
                        Intent intent = new Intent(PersonalHomepageActivity.this, PersonalHomepageActivity.class);
                        startActivity(intent);
                    }
                });

                if(isMineHomepage) helper.getView(R.id.iv_item_share_collection).setVisibility(View.GONE);
                else {
                    ImageView iv = helper.getView(R.id.iv_item_share_collection);
                    if (item.isConcern)
                        iv.setColorFilter(getResources().getColor(R.color.color_item_share_collection),
                                PorterDuff.Mode.SRC_ATOP);//position error
                    else
                        iv.clearColorFilter();
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                        int[] location = new int[2];
//                        iv.getLocationOnScreen(location);
//                        Snackbar.make(llShareList, location[0]+"--"+location[1], Snackbar.LENGTH_SHORT).show();
                            if(item.isConcern)
                                item.isConcern=false;
                            else item.isConcern=true;
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        };
        mLvPersonalList.setAdapter(mAdapter);

    }

    private void initHeader() {
        mHeader = getLayoutInflater().inflate(R.layout.header_personal_homepage, null);
        mHeader.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (headerHeight == 0) {
                    headerHeight = mHeader.getHeight();
                }
            }
        });
        mLvPersonalList.addHeaderView(mHeader);
        if(isMineHomepage){
            CircleImageView civ = (CircleImageView) mHeader.findViewById(R.id.iv_personal_avatar);
            civ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PersonalHomepageActivity.this,MineInfoActivity.class);
                    startActivity(intent);
                }
            });
        }
        mLvPersonalList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    if (backHeight >= (headerHeight + mHeader.getTop())) {
                        if (mRlPersonalTop.getVisibility() == View.GONE)
                            mRlPersonalTop.setVisibility(View.VISIBLE);
                    } else {
                        if (mRlPersonalTop.getVisibility() == View.VISIBLE)
                            mRlPersonalTop.setVisibility(View.GONE);
                    }
                } else {
                    mRlPersonalTop.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick({R.id.iv_personal_back,R.id.iv_personal_back_top})
    public void back(View v){
        finish();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            backHeight = mRlPersonalTop.getHeight();
        }
        super.onWindowFocusChanged(hasFocus);
    }
}
