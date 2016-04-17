package cn.com.se.sharepictrue.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.se.sharepictrue.R;
import cn.com.se.sharepictrue.fragment.ShareListFragment;
import cn.com.se.sharepictrue.utils.SystemUtils;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends ActionBarActivity {

    @Bind(R.id.tb_home_toolbar)
    Toolbar tbHomeToolbar;
    @Bind(R.id.fl_home_content)
    FrameLayout flHomeContent;
    @Bind(R.id.btn_drawer_login)
    Button btnDrawerLogin;
    @Bind(R.id.iv_drawer_avatar)
    CircleImageView ivDrawerAvatar;
    @Bind(R.id.rl_drawer_share)
    RelativeLayout rlDrawerShare;
    @Bind(R.id.rl_drawer_publish)
    RelativeLayout rlDrawerPublish;
    @Bind(R.id.rl_drawer_following)
    RelativeLayout rlDrawerFollowing;
    @Bind(R.id.rl_drawer_collection)
    RelativeLayout rlDrawerCollection;
    @Bind(R.id.rl_drawer_setting)
    RelativeLayout rlDrawerSetting;
    @Bind(R.id.rl_drawer_logout)
    RelativeLayout rlDrawerLogout;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.tv_drawer_name)
    TextView mTvDrawerName;
    @Bind(R.id.rl_drawer_follower)
    RelativeLayout mRlDrawerFollower;

    private ActionBarDrawerToggle drawerToggle;

    private ShareListFragment mShareListFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mFragmentManager = getFragmentManager();
        initToolbar();
        initDrawerLayout();
        initHomePage();
    }

    private void initToolbar() {
        setSupportActionBar(tbHomeToolbar);
        tbHomeToolbar.setTitleTextColor(Color.WHITE);
        tbHomeToolbar.setNavigationIcon(R.mipmap.ic_menu);
    }

    private void initHomePage() {
        mShareListFragment = ShareListFragment.getNewInstance();
        mFragmentManager.beginTransaction()
                .replace(R.id.fl_home_content, mShareListFragment)
                .commit();
    }

    private void initDrawerLayout() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, tbHomeToolbar, R.string.app_name, R.string.app_name);

        drawerLayout.setDrawerListener(drawerToggle);
    }

    @OnClick({R.id.rl_drawer_share, R.id.btn_drawer_login, R.id.rl_drawer_publish,
            R.id.rl_drawer_following,R.id.rl_drawer_follower,R.id.iv_drawer_avatar,
            R.id.rl_drawer_setting,R.id.rl_drawer_collection})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_drawer_share://分享
                doShare();
                break;
            case R.id.btn_drawer_login:// 登录/注册
                Intent LoginIntent = new Intent(this, LoginActivity.class);
                startActivity(LoginIntent);
                break;
            case R.id.rl_drawer_publish:// 我的发表
                Intent PersonIntent = new Intent(this, PersonalHomepageActivity.class);
                PersonIntent.putExtra(PersonalHomepageActivity.HOMEPAGE_KEY,PersonalHomepageActivity.HOMEPAGE_ME);
                startActivity(PersonIntent);
                break;
            case R.id.iv_drawer_avatar:// 点击头像
                Intent MineInfoIntent = new Intent(this,MineInfoActivity.class);
                startActivity(MineInfoIntent);
                break;
            case R.id.rl_drawer_following: //我的关注
                Intent concernIntent= new Intent(this,ConcernActivity.class);
                concernIntent.putExtra(ConcernActivity.CONCERN_WHAT_TITLE,
                        getResources().getString(R.string.concern_following_title));
                startActivity(concernIntent);
                break;
            case R.id.rl_drawer_follower: //我的粉丝
                Intent followerIntent = new Intent(this, ConcernActivity.class);
                followerIntent.putExtra(ConcernActivity.CONCERN_WHAT_TITLE,
                        getResources().getString(R.string.concern_follower_title));
                startActivity(followerIntent);
                break;
            case R.id.rl_drawer_setting:// 设置
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.rl_drawer_collection://我的收藏
                Intent collectionIntent = new Intent(this, MineCollectionActivity.class);
                startActivity(collectionIntent);
                break;
        }
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_home_search:
                showSearchWindow(item);
                return true;
            case R.id.action_home_rank:
                showRankWindow();
                return true;
            case R.id.action_home_share:
                doShare();
        }

        return super.onOptionsItemSelected(item);
    }

    private void doShare() {
        Intent intent = new Intent(this, PublishActivity.class);
        startActivity(intent);
    }

    private void showRankWindow() {
        Intent intent = new Intent(this, RankActivity.class);
        startActivity(intent);
    }

    private void showSearchWindow(MenuItem item) {
    // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(this).inflate(
                R.layout.layout_search_popup_window, null);
        initPopupView(contentView);


        PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.bg_search_popup));

        // 设置好参数之后再show
        popupWindow.showAtLocation(tbHomeToolbar, Gravity.NO_GRAVITY, (int) tbHomeToolbar.getX(), (int) tbHomeToolbar.getY()+ SystemUtils.getStatusBarHeight(this));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
    }

    private void initPopupView(final View contentView) {
        // 设置按钮的点击事件
        TextView search = (TextView) contentView.findViewById(R.id.tv_search_popup_search);
        final EditText input = (EditText) contentView.findViewById(R.id.et_search_popup_input);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Snackbar.make(tbHomeToolbar, input.getText().toString(), Snackbar.LENGTH_SHORT).show();
                doSearch();
            }
        });
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    doSearch();
                    return true;
                }
                return false;
            }
        });
    }
    private void doSearch() {
        hideKeyboard();

        Intent intent = new Intent();
        intent.setClass(HomeActivity.this, ShareDetailActivity.class);
        startActivity(intent);
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
