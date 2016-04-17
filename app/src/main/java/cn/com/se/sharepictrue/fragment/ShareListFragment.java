package cn.com.se.sharepictrue.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.activity.PersonalHomepageActivity;
import cn.com.se.sharepictrue.activity.ShareDetailActivity;
import cn.com.se.sharepictrue.bean.HomeShareBean;
import cn.com.se.sharepictrue.bean.ShareListBean;
import cn.com.se.sharepictrue.quickadapter.BaseAdapterHelper;
import cn.com.se.sharepictrue.quickadapter.QuickAdapter;

/**
 * 主页分享列表界面
 * Created by KIDNG on 2015/11/2.
 */
public class ShareListFragment extends Fragment {
    private static String SHARE_LIST_KEY = "share_list_key";
    public static String NEWEST = "newest";
    public static String PRAISE_RANK = "praise_rank";
    @Bind(R.id.ll_share_list)
    ListView llShareList;
    @Bind(R.id.srl_share_refresh)
    SwipeRefreshLayout mSrlShareRefresh;
    private List<HomeShareBean> beans;
    private QuickAdapter<HomeShareBean> mAdapter;
    private Context mContext;
    private String str;

    public static ShareListFragment getNewInstance() {
        return getNewInstance("");
    }

    public static ShareListFragment getNewInstance(String s) {
        ShareListFragment slf = new ShareListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SHARE_LIST_KEY, s);
        return slf;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        ButterKnife.bind(this, view);
        //9str=getArguments().getString(SHARE_LIST_KEY);
        mContext = getActivity();
        initList();
        return view;
    }

    Handler mHandler = new Handler();

    private void initList() {
        beans = new ArrayList<HomeShareBean>();
        for (int i = 0; i < 8; i++) {//模拟数据
            HomeShareBean bean = new HomeShareBean();
            bean.name = "asd" + i;
            bean.commentNum = i;
            bean.content = "描述" + i;
            bean.introduction = "who i am";
            bean.isCollection = i % 2 == 0;
            bean.praiseNum = i;
            bean.isConcern = false;
            beans.add(bean);
        }
        mAdapter = new QuickAdapter<HomeShareBean>(mContext, R.layout.item_share_list, beans) {
            @Override
            protected void convert(BaseAdapterHelper helper, final HomeShareBean item) {
                helper.setText(R.id.tv_item_share_name, item.name);
                helper.setText(R.id.tv_item_share_time, item.introduction);
                helper.setText(R.id.tv_item_share_content, item.content);
                helper.setText(R.id.tv_item_share_comment, item.commentNum + "");
                helper.setText(R.id.tv_item_share_praise, item.praiseNum + "");

                if (item.isCollection)
                    helper.setText(R.id.tv_item_share_collection, getResources().getString(R.string.item_share_collection_selected));
                else
                    helper.setText(R.id.tv_item_share_collection, getResources().getString(R.string.item_share_collection_normal));

                helper.getView(R.id.iv_item_share_avatar).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PersonalHomepageActivity.class);
                        startActivity(intent);
                    }
                });
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
        };
        llShareList.setAdapter(mAdapter);
        llShareList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ShareDetailActivity.class);
                startActivity(intent);
            }
        });
        doRefresh();
    }

    private void doRefresh() {
        mSrlShareRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                llShareList.setEnabled(false);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSrlShareRefresh.setRefreshing(false);

                        llShareList.setEnabled(true);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
