package com.kscf.app.android.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.kscf.app.android.model.bean.HomeFundSelectedBannerBean;
import com.kscf.app.android.util.framing.LoadImageUtils;

public class BannerImageHolderView implements Holder<HomeFundSelectedBannerBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        /*imageView.setScaleType(ImageView.ScaleType.FIT_XY);*/
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, HomeFundSelectedBannerBean bannerBean) {
        LoadImageUtils.load(context, imageView, bannerBean.imgUrl, bannerBean.imgResId);
    }
}