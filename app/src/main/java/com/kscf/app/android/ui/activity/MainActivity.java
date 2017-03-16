package com.kscf.app.android.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.framework.util.SizeUtils;
import com.framework.util.ToastUtils;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.databinding.ActivityMainBinding;
import com.kscf.app.android.model.bean.MainBean;
import com.kscf.app.android.presenter.MainPresenter;
import com.kscf.app.android.presenter.contract.MainContract;
import com.kscf.app.android.ui.HomeTabs;
import com.kscf.app.android.widget.LoadingPage;

import me.shaohui.bottomdialog.BottomDialog;

/**
 * Created by luoyl on 2016/12/4.
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainPresenter> implements MainContract.View, View.OnClickListener {

    private BottomDialog mBottomDialog;
    private long mBackPressedTime;
    private int mTabId = -1;
    private boolean mIsJump;

    //点击我的账户页面到登陆页面，如果在登录页面登录成功跳转到【我的账户页面】的tag
    public static final String sRxBusLoginToHomeMyAccountTag = "RxBusLoginToHomeMyAccountTag";

    private RunToFragmentImp mRunToFragmentImp;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected boolean isRegisterRxBus() {
        return true;
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);
        initTabs();
        //initViewPager();


//        mBottomDialog = BottomDialog.create(getSupportFragmentManager())
//                .setLayoutRes(R.layout.dialog_layout);
//        mBottomDialog.setViewListener(new BottomDialog.ViewListener() {
//            @Override
//            public void bindView(View v) {
//                v.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mBottomDialog.dismiss();
//                    }
//                });
//            }
//        });
        //mBottomDialog.show();

    }

    @Override
    public void initListener() {
        mDataBinding.tvHomeMyAccount.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {
        mPresenter.start();
    }

    @Override
    public void showContent(MainBean mainBean) {
        mDataBinding.setMainBean(mainBean);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mIsJump) {
            if (mRunToFragmentImp == null) {
                mRunToFragmentImp = new RunToFragmentImp();
            }
            App.getInstance().post(mRunToFragmentImp);
        }
    }

    private void initTabs() {
        mDataBinding.tabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);
        for (HomeTabs tab : HomeTabs.values()) {
            TabHost.TabSpec tabSpec = mDataBinding.tabHost.newTabSpec(/*getResources().getString(tab.txtResId)*/String.valueOf(tab.index));
            TextView indicatorTextView = (TextView) View.inflate(getBaseContext(), R.layout.item_home_tabhost_indicator, null);
            Drawable topDrawable = getResources().getDrawable(tab.iconSelectorId);
            int width = SizeUtils.dp2px(getApplicationContext(), 23);
            topDrawable.setBounds(0, 0, width, width);
            indicatorTextView.setCompoundDrawables(null, topDrawable, null, null);
            indicatorTextView.setText(tab.txtResId);
            tabSpec.setIndicator(indicatorTextView);
            mDataBinding.tabHost.addTab(tabSpec, tab.fragmentClazz, null);
        }
    }

    @Subscribe(tags = {@Tag(sRxBusLoginToHomeMyAccountTag)})
    public void rxBusEvent(String tag) {
        //ToastUtils.show("rxBusEvent tabId:" + mTabId + "  tag:" + tag);
        if ("0".equals(tag) || "2".equals(tag)) {//登录成功后跳转
            mTabId = Integer.valueOf(tag);
            mIsJump = true;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_my_account://点击我的账户
                //点击我的账户记录tabId
                mTabId = 2;
                toMyAccountPage();
                break;
        }
    }

    public class RunToFragmentImp implements Runnable {
        @Override
        public void run() {
            toMyAccountPage();
        }
    }

    private void toMyAccountPage() {
        if (App.getInstance().isLogin() || mTabId == 0) {//登录直接跳转到目标页面
            selectTabPage(mTabId);
        } else {//没有登录记录下目标页面的下标，跳转到登录页面
            Intent intent = new Intent(this, LoginActivity.class);
            App.getInstance().startTargetActivity(this, intent, false);
            //如果没有登录，跳转到登录页面退出时，如果登录使用RxBus跳转到[我的账户页面]
        }
    }


    public void selectTabPage(int tabId) {
        mDataBinding.tabHost.setCurrentTab(tabId);
        //设置Tab为选中状态
        mDataBinding.tabHost.getTabWidget().getChildTabViewAt(tabId).setSelected(true);
        mTabId = -1;
        mIsJump = false;
    }

    @Override
    public void onBackPressed() {
        boolean isDoubleClick = true;//是否需要双击退出【声明变量的目的是方便后续切换】
        if (isDoubleClick) {
            long curTime = SystemClock.uptimeMillis();
            if ((curTime - mBackPressedTime) < (3 * 1000)) {
                finish();
            } else {
                mBackPressedTime = curTime;
                Toast.makeText(this, R.string.tip_double_click_exit, Toast.LENGTH_LONG).show();
            }
        } else {
            finish();
        }
    }


    @Override
    public void free() {
        mDataBinding.tabHost.setup(App.getInstance(), null);
        mDataBinding.tabHost.clearAllTabs();
        mDataBinding.flContent.removeAllViews();
        App.getInstance().exit();
        super.free();
    }


}
