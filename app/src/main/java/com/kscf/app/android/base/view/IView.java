package com.kscf.app.android.base.view;

/**
 * View逻辑接口
 * Created by luoyl on 2016/12/22.
 */

public interface IView {

    /**
     * 获取资源id
     *
     * @return
     */
    int getLayoutResId();


    /**
     * Daager2注解
     */
    void initInject();

    /**
     * 初始化view
     */
    void initView();


    void initListener();

    /**
     * 事件绑定 & 数据绑定
     */
    void initEventAndData();


    /**
     * 资源释放
     */
    void free();


}
