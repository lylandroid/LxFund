package com.kscf.app.android.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

/**
 * Created by luoyl on 2017/1/22.
 * 风险测评Bean
 */

public class RiskEvaluationBean {

    public List<PageItem> pageItems;

    public class PageItem {
        public String title;
        public List<RecyclerItem> recyclerItems;

        public void setTitle(String title) {
            this.title = title;
        }

        public class RecyclerItem extends BaseObservable {

            public boolean isCheck /*= true*/;
            public String des;

            @Bindable
            public boolean getIsCheck() {
                return isCheck;
            }

            public void setIsCheck(boolean isCheck) {
                this.isCheck = isCheck;
                notifyPropertyChanged(BR.isCheck);
            }


        }
    }
}
