package com.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.kscf.app.android.app.App;
import com.framework.util.L;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by luoyl on 2016/12/12.
 */

public class SupportFragment extends Fragment {


    private boolean mIsHidden;
    public static final String FRAGMENT_STATE_IS_HIDDEN = "FRAGMENT_IS_HIDDEN_KEY";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {

        } else {
            mIsHidden = savedInstanceState.getBoolean(FRAGMENT_STATE_IS_HIDDEN);
        }

        if (restoreInstanceState()) {
            // 解决重叠问题
            processRestoreInstanceState(savedInstanceState);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FRAGMENT_STATE_IS_HIDDEN, isHidden());
    }

    private void processRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden()) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    /**
     * 仅在内存重启后有意义(saveInstanceState!=null时)
     *
     * @return Fragment状态 hide : show
     */
    public boolean isSupportHidden() {
        return mIsHidden;
    }

    /**
     * 内存重启后,是否让Fragmentation帮你恢复子Fragment状态
     */
    protected boolean restoreInstanceState() {
        return true;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (L.isDebug) {
            RefWatcher refWatcher = App.getRefWatcher(getActivity());
            refWatcher.watch(this);
        }
    }


}
