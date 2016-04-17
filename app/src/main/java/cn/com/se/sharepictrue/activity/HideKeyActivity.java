package cn.com.se.sharepictrue.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wefika.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.utils.DensityUtil;
import cn.com.se.sharepictrue.widget.FlowLayoutForScroll;

/**
 * 屏蔽关键词界面
 * Created by KIDNG on 2015/11/5.
 */
public class HideKeyActivity extends ActionBarActivity {


    @Bind(R.id.tb_manager_hide_key_title)
    Toolbar mTbManagerHideKeyTitle;
    @Bind(R.id.et_manager_hide_key_input)
    EditText mEtManagerHideKeyInput;
    @Bind(R.id.til_manager_hide_key_input)
    TextInputLayout mTilManagerHideKeyInput;
    @Bind(R.id.tv_manager_hide_key_ban)
    TextView mTvManagerHideKeyBan;
    @Bind(R.id.fl_hide_key_flow)
    FlowLayoutForScroll mFlHideKeyFlow;


    private List<View> mViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_hide_key);
        ButterKnife.bind(this);
        initToolBar();
        mTilManagerHideKeyInput.setHint(getResources().getString(R.string.manager_hide_key_hint_input));
        initView();
    }

    private void initToolBar() {
        setSupportActionBar(mTbManagerHideKeyTitle);
        mTbManagerHideKeyTitle.setTitleTextColor(Color.WHITE);
    }

    private void initView() {
        mViewList = new ArrayList<View>();
        FlowLayout.LayoutParams params = mFlHideKeyFlow.new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int defaultMargin = DensityUtil.dip2px(this, 8);
        params.setMargins(defaultMargin, defaultMargin, defaultMargin, defaultMargin);
        for (int i = 0; i < 10; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_hide_key_flow, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_hide_key_text);
            tv.setText("asd" + i);
            ImageView img = (ImageView) view.findViewById(R.id.iv_hide_key_delete);
            img.setTag(view);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFlHideKeyFlow.removeView((View) v.getTag());
                    mViewList.remove(v.getTag());
                }
            });
            view.setLayoutParams(params);
            mViewList.add(view);
            mFlHideKeyFlow.addView(view, i);
        }
    }

    @OnClick(R.id.tv_manager_hide_key_ban)
    public void add(View v) {
        String str = mEtManagerHideKeyInput.getText().toString();
        initBanView(str);
    }

    private void initBanView(String str) {
        FlowLayout.LayoutParams params = mFlHideKeyFlow.new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int defaultMargin = DensityUtil.dip2px(this, 8);
        params.setMargins(defaultMargin, defaultMargin, defaultMargin, defaultMargin);
        View view = getLayoutInflater().inflate(R.layout.item_hide_key_flow, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_hide_key_text);
        tv.setText(str);
        ImageView img = (ImageView) view.findViewById(R.id.iv_hide_key_delete);
        img.setTag(view);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlHideKeyFlow.removeView((View) v.getTag());
            }
        });
        view.setLayoutParams(params);
        mFlHideKeyFlow.addView(view);
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
