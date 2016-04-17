package cn.com.se.sharepictrue.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.quickadapter.BaseAdapterHelper;
import cn.com.se.sharepictrue.quickadapter.QuickAdapter;
import cn.com.se.sharepictrue.widget.SquareImageView;

/**
 * 发布界面
 * Created by KIDNG on 2015/11/2.
 */
public class PublishActivity extends Activity {
    @Bind(R.id.tv_publish_cancel)
    TextView mTvPublishCancel;
    @Bind(R.id.tv_publish_send)
    TextView mTvPublishSend;
    @Bind(R.id.et_publish_content)
    EditText mEtPublishContent;
    @Bind(R.id.gv_publish_grid)
    GridView mGvPublishGrid;

    private List<String> mList;
    private QuickAdapter<String> mAdapter;
    private final int PIC_REQUEST_CODE = 1;
    private final int MAX_PIC_NUM = 6;
    private int IMG_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        initImgSelectGrid();
    }

    private void initImgSelectGrid() {
        mList = new ArrayList<String>();
        mList.add("");
        mAdapter = new QuickAdapter<String>(this, R.layout.item_publish_grid, mList) {
            @Override
            protected void convert(final BaseAdapterHelper helper, String item) {
                SquareImageView add = helper.getView(R.id.iv_item_publish_img);
                ImageView del = helper.getView(R.id.iv_item_publish_del);
                Log.i("test",helper.getPosition()+"--"+item+"---"+item.isEmpty());
                if (item.isEmpty()) {
                    add.setImageResource(R.mipmap.ic_add_img);
                    add.clearColorFilter();
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, PIC_REQUEST_CODE);
                        }
                    });
                    del.setVisibility(View.GONE);
                } else {
                    add.setImageResource(R.mipmap.ic_change_password);
                    add.setColorFilter(getResources().getColor(R.color.color_item_share_collection),
                            PorterDuff.Mode.SRC_ATOP);
                    add.setOnClickListener(null);
                    del.setVisibility(View.VISIBLE);
                    del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Snackbar.make(mGvPublishGrid,""+helper.getPosition(),Snackbar.LENGTH_SHORT).show();
                            mAdapter.remove(helper.getPosition());
                            IMG_NUM--;
                            if (IMG_NUM == MAX_PIC_NUM-1) {
                                mAdapter.add("");
                            }
                        }
                    });
                }
            }
        };
        mGvPublishGrid.setAdapter(mAdapter);
    }

    @OnClick({R.id.tv_publish_cancel, R.id.tv_publish_send})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_publish_cancel:
                hideKeyboard();
                finish();
                break;
        }
    }
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode != PIC_REQUEST_CODE || resultCode != RESULT_OK)
            return;
        super.onActivityResult(requestCode, resultCode, data);
        //得到URI
        // 判断图片数是否到6张
        String path = mAdapter.getItem(IMG_NUM);
        path = "xxx";
        mAdapter.set(IMG_NUM,path);
        if (IMG_NUM < MAX_PIC_NUM-1) {
            mAdapter.add("");
        }
        //添加图片
        IMG_NUM++;
    }
}
