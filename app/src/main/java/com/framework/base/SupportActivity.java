package com.framework.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by luoyl on 2017/1/17.
 */

public class SupportActivity extends AppCompatActivity {


    public void addFragment(int fragmentLayoutId, FragmentManager fragmentManager, Fragment showFragment) {
        showHideFragment(fragmentLayoutId, fragmentManager, showFragment, (Fragment) null);
    }

    public void hideFragment(int fragmentLayoutId, FragmentManager fragmentManager, Fragment hideFragment) {
        showHideFragment(fragmentLayoutId, fragmentManager, null, hideFragment);
    }

    /**
     * 显示和隐藏Fragment
     *
     * @param frameLayoutId
     * @param fragmentManager
     * @param showFragment
     * @param hideFragmentClazz
     */
    public void showHideFragment(int frameLayoutId, FragmentManager fragmentManager, Fragment showFragment, Class hideFragmentClazz) {
        showHideFragment(frameLayoutId
                , fragmentManager
                , showFragment
                , hideFragmentClazz != null ? fragmentManager.findFragmentByTag(hideFragmentClazz.getName()) : null);
    }

    /**
     * 显示和隐藏Fragment
     *
     * @param frameLayoutId
     * @param fragmentManager
     * @param showFragment
     * @param hideFragment
     */
    public void showHideFragment(int frameLayoutId, FragmentManager fragmentManager, Fragment showFragment, Fragment hideFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (hideFragment != null && hideFragment.isAdded() && !hideFragment.isHidden()) {
            transaction.hide(hideFragment);
        }

        String fragmentTag = showFragment.getClass().getName();
        if (showFragment != null) {
            if (showFragment.isAdded()) {
                transaction.show(showFragment);
            } else {
                transaction.add(frameLayoutId, showFragment, fragmentTag);
                transaction.addToBackStack(fragmentTag);
            }
            //transaction.commit();
        }
        //transaction.addToBackStack(fragmentTag);
        transaction.commitAllowingStateLoss();
    }

    /**
     * Fragment退出
     *
     * @param fragmentManager
     */
    public void pop(FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            return;
        }
        int count = fragmentManager.getBackStackEntryCount();
        if (count > 0) {
            pop(null, fragmentManager);
        }
    }

    public void pop(SupportFragment from, FragmentManager fragmentManager) {
        if (from == null) {
            //暂时无用代码，用于扩展
            from = getTopFragment(fragmentManager);
        }
        fragmentManager.popBackStackImmediate();
    }

    /**
     * 获得栈顶SupportFragment
     */
    SupportFragment getTopFragment(FragmentManager fragmentManager) {
        if (fragmentManager == null) return null;
        List<Fragment> fragmentList = fragmentManager.getFragments();
        if (fragmentList == null) return null;

        for (int i = fragmentList.size() - 1; i >= 0; i--) {
            Fragment fragment = fragmentList.get(i);
            if (fragment instanceof SupportFragment) {
                return (SupportFragment) fragment;
            }
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        onBackPressedSupport();
    }

    /**
     * FragmentManager管理器中是否存在Fragment(true:有，false:无)
     *
     * @return
     */
    public boolean onBackPressedSupport() {
        boolean result = true;
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop(getSupportFragmentManager());
            result = true;
        } else {
            finish();
            result = false;
        }
        return result;
    }
}
