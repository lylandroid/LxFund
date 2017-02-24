package com.kscf.app.android.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.kscf.app.android.model.bean.HomeFundSelectedBannerBean;
import com.kscf.app.android.other.GlideUtils;

public class BannerImageHolderView implements Holder<HomeFundSelectedBannerBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, HomeFundSelectedBannerBean bannerBean) {
        //Glide.with(context).load(bannerBean.imgUrl).into(imageView);
        GlideUtils.load(context, imageView, bannerBean.imgUrl, bannerBean.imgResId);
    }
}