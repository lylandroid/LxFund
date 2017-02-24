package com.kscf.app.android.ui.activity;

import android.content.Intent;

import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.databinding.ActivityWelcomeBinding;
import com.kscf.app.android.model.bean.MainBean;
import com.kscf.app.android.presenter.WelcomePresenter;
import com.kscf.app.android.presenter.contract.WelcomeContract;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by Administrator on 2016/12/22.
 */

public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding, WelcomePresenter> implements WelcomeContract.View{


    @Override
    public int getLayoutResId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initEventAndData() {
        App.getInstance().postDelayed(mRunnable, 2000);
       mPresenter.start();
    }

    @Override
    public void showContent(MainBean mainBean) {

    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            toMain();
        }
    };

    public void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        App.getInstance().startTargetActivity(this,intent,false);
        finish();
    }

    @Override
    public void free() {
        super.free();
        if (mRunnable != null) {
            App.getInstance().removeCallbacks(mRunnable);
        }
        mRunnable = null;
    }
}
