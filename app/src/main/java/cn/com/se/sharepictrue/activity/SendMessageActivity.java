package cn.com.se.sharepictrue.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.se.sharepictrue.R;

/**
 * 发送信息界面
 * Created by KIDNG on 2015/11/3.
 */
public class SendMessageActivity extends ActionBarActivity {
    @Bind(R.id.tb_send_comment_title)
    Toolbar mTbSendCommentTitle;
    @Bind(R.id.et_send_comment_content)
    EditText mEtSendCommentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_comment);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mTbSendCommentTitle);
        mTbSendCommentTitle.setTitleTextColor(Color.WHITE);
    }

    @OnClick(R.id.tv_send_comment_send)
    public void sendComment(View v) {

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
