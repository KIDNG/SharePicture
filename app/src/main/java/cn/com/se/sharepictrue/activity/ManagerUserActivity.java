package cn.com.se.sharepictrue.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.bean.PersonBean;
import cn.com.se.sharepictrue.quickadapter.BaseAdapterHelper;
import cn.com.se.sharepictrue.quickadapter.QuickAdapter;

/**
 * 用户管理界面
 * Created by KIDNG on 2015/11/5.
 */
public class ManagerUserActivity extends ActionBarActivity {
    @Bind(R.id.tb_manager_user_title)
    Toolbar mTbManagerUserTitle;
    @Bind(R.id.et_manager_user_input)
    EditText mEtManagerUserInput;
    @Bind(R.id.til_manager_user_input)
    TextInputLayout mTilManagerUserInput;
    @Bind(R.id.tv_manager_user_ban)
    TextView mTvManagerUserBan;
    @Bind(R.id.lv_manager_user_ban_list)
    ListView mLvManagerUserBanList;
    private ArrayList<PersonBean> mBeans;
    private QuickAdapter<PersonBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_user);
        ButterKnife.bind(this);
        initToolbar();
        mTilManagerUserInput.setHint(getResources().getString(R.string.manager_user_ban_input));
        initList();
    }

    private void initToolbar() {
        setSupportActionBar(mTbManagerUserTitle);
        mTbManagerUserTitle.setTitleTextColor(Color.WHITE);
    }

    private void initList() {
        mBeans = new ArrayList<PersonBean>();
        for (int i = 0; i < 10; i++) {
            //模拟数据
            PersonBean bean = new PersonBean();
            bean.name = "zhutou";
            bean.introduction = "12312r4124124124r5234t534636234532464345645";
            mBeans.add(bean);
        }

        mAdapter = new QuickAdapter<PersonBean>(this, R.layout.item_person_list, mBeans) {
            @Override
            protected void convert(final BaseAdapterHelper helper, PersonBean item) {
                TextView concern = helper.getView(R.id.tv_person_list_concern);
                concern.setBackgroundResource(R.drawable.bg_person_list_concerned);
                concern.setText(getResources().getString(R.string.manager_user_cancel_ban));
                concern.setTextColor(Color.WHITE);
                concern.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAdapter.remove(helper.getPosition());
                    }
                });
                helper.setText(R.id.tv_person_list_name, item.name);
                helper.setText(R.id.tv_person_list_introduction, item.introduction);
            }
        };
        mLvManagerUserBanList.setAdapter(mAdapter);

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
