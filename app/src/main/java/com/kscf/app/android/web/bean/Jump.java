package com.kscf.app.android.web.bean;

import java.util.Map;

/**
 * Created by luoyl on 2017/3/24.
 */

public class Jump {
    //动作类型，见JumpConstants.ActionType
    public int actionType;
    //动作连接到目标系统，见JumpConstants.ActionSystem
    public String linkTosys;
    //url地址，http开头的为绝对地址，否则为相对地址，需要根据linktosys加上相应的前缀
    public String linkUrl;


    //动作连接的路由key,接受系统提供，并提供router程序
    //定义格式一般为xxx.xxx.xxx.xxx数字字母全小写

    //app跳转以app. 开头，凯石财富的以app.wealth. 开头
    //app内嵌h5以apph5. 开头，凯石财富内嵌的以apph5.wealth. 开头
    public String linkRouteKey;

    //动作参数,k,v
    public Map<String, String> linkParams;
    //跟踪代码
    public String trackCode;
    //自定义数据
    public String customData;

}
