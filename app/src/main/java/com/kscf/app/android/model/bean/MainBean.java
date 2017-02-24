package com.kscf.app.android.model.bean;

import java.util.List;

/**
 * Created by luoyl on 2016/12/26.
 */
public class MainBean {
    /**
     * success : true
     * code : 200
     * message : 成功
     * body : [{"comb_name":"开始10号","reason":"1","is_home":"0","group_count":"3","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"1","rate_exp":"1","update_time":"2016-12-20","issue_date":"2016-06-27","rate":"1","is_hot":"1","risktip":"1","reason_short":"1","id":"750200703682338816","disclaimer":"1","compare_type":"1","asc_amt":"1","create_time":"2016-07-05","comb_tag":"1","sort":"1","comb_code":"ks0010","inv_strategy":"1","risk_level":"1","fit_person":"1","comb_type":"4","min_amt":"1","setup_date":"2016-07-04","choice_type":"1","track_code":"1","status":"1"},{"comb_name":"凯石股基增长","reason":"无论是海外还是国内，股票资产长期来看能获得稳定且较优的资产回报率，而随着A股不断扩容，投资者自己选股的压力和难度越来越大，股混基金成为普通投资者参与股市投资的首选工具。精选基金并进行均衡的风格配置的股混基金组合能帮助投资者更好的分享股市收益。","is_home":"1","group_count":"5","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"股混基金平均收益","rate_exp":"高风险高收益","update_time":"2016-10-11","issue_date":"2015-12-31","rate":"基金费率包括：申购费、管理费、托管费、赎回费","is_hot":"1","risktip":"市场有风险，投资需谨慎。","reason_short":"哎呦!不错哦!","id":"692186160395608064","disclaimer":"基金净值及其收益可涨可跌，基金过往的业绩并不代表将来表现。","compare_type":"2","asc_amt":"1","create_time":"2016-04-28","comb_tag":"5","sort":"1","comb_code":"ks0004","inv_strategy":"\"精选基金获取超额收益：由于当前基金数量众多，无论是成长风格基金还是价值风格基金，基金之间业绩差异较大，通过专业的精选基金，能获取选基的超额收益。　　　　　　　　　　　　　　　　　\r\n\r\n组合优化风险收益：成长型基金弹性足，价值型基金弹性较低，但抗跌性强，二者具有一定的互补性，本组合按照优选成长型基金(40%)+价值型基金(40%)+稳健型基金(20%)构建，可以较好的控制回撤，降低组合波动。\"","risk_level":"5","fit_person":"风险偏好较高，追求资本的长期增值，能承受短期波动的投资者","comb_type":"5","min_amt":"1","setup_date":"2016-01-04","choice_type":"1","track_code":"沪深300指数   000300.sh","status":"1"},{"comb_name":"凯石债积财富","reason":"债券从长期看能为投资者带来高于货币市场的票息收益，并且风险低于股混基金，因此纯债基金组合是长期资产保值的良好配置工具。","is_home":"1","group_count":"3","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"纯债基金平均收益","group_type":"混合型","rate_exp":"中低风险中低收益","update_time":"2016-10-11","issue_date":"2015-12-31","rate":"基金费率包括：申购费、管理费、托管费、赎回费","is_hot":"1","risktip":"市场有风险，投资需谨慎。","reason_short":"纯债基金组合是长期资产保值的良好配置工具。","id":"692187717495336960","disclaimer":"基金净值及其收益可涨可跌，基金过往的业绩并不代表将来表现。","compare_type":"1","asc_amt":"1","create_time":"2016-04-28","comb_tag":"4","sort":"2","comb_code":"ks0005","inv_strategy":"精选债基，以期限在一年以内的短融类债基为主，增配中长期纯债基金构建相对稳健组合，期望为投资者带来稳定的收益。","risk_level":"2","fit_person":"风险偏好相对较低的投资者","comb_type":"2","min_amt":"1","setup_date":"2016-01-04","choice_type":"2","track_code":"中债总净价指数  0372.cs","status":"1"},{"comb_name":"凯石日日盈","reason":"货币基金是良好的流动性管理工具，而将货币基金构建组合能更好规避单只产品的短期流动性风险等。","is_home":"1","group_count":"3","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"中国人民银行公布的活期存款基准利率的收益率(税后)","rate_exp":"七日年化收益率","update_time":"2016-10-11","issue_date":"2015-12-01","rate":"管理费率（年）： 0.33%   　　　　　\r\n托管费率（年）： 0.10%  \r\n销售服务费（年）：0.25%  ","is_hot":"1","risktip":"市场有风险，投资需谨慎。","reason_short":"组合能更好规避单只产品的短期流动性风险等。","id":"692248097357377536","disclaimer":"基金净值及其收益可涨可跌，基金过往的业绩并不代表将来表现。","compare_type":"2","asc_amt":".1","create_time":"2016-04-28","comb_tag":"3","sort":"3","comb_code":"ks0006","inv_strategy":"精选货币基金，注重货币基金历史运作管理稳定性和业绩，关注流动性安全。","risk_level":"1","fit_person":"低风险，对资金流动性要求较高","comb_type":"1","min_amt":".1","setup_date":"2016-01-04","choice_type":"1","track_code":"银行一年定存利率   1.5%","status":"1"},{"comb_name":"凯石均衡稳健","reason":"1","is_home":"1","group_count":"5","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"1","group_type":"股混型","rate_exp":"1","update_time":"2016-10-11","issue_date":"2015-12-31","rate":"1","is_hot":"0","risktip":"1","reason_short":"这是一个好的组合,抢购中这是一个好的组合,抢购中这是一个好的组合,抢购中这是一个好的组合,抢购中这是一个好的组合,抢购中","id":"725516337540026368","disclaimer":"1","compare_type":"3","asc_amt":".5","create_time":"2016-04-28","comb_tag":"2","sort":"1","comb_code":"ks0008","inv_strategy":"1","risk_level":"3","fit_person":"1","comb_type":"3","min_amt":".5","setup_date":"2016-01-08","choice_type":"2","track_code":"1","status":"1"},{"comb_name":"凯石保本增利","reason":"1","is_home":"1","group_count":"4","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"1","rate_exp":"1","update_time":"2016-12-01","issue_date":"2015-12-31","rate":"1","is_hot":"1","risktip":"1","reason_short":"这是一个好的组合","id":"725518001817432064","disclaimer":"1","compare_type":"2","asc_amt":"5000","create_time":"2016-04-28","comb_tag":"1","sort":"1","comb_code":"ks0009","inv_strategy":"1","risk_level":"1","fit_person":"1","comb_type":"2","min_amt":"10000","setup_date":"2016-01-04","choice_type":"1","track_code":"1","status":"1"},{"comb_name":"开始10号","reason":"1","is_home":"0","group_count":"3","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"1","rate_exp":"1","update_time":"2016-12-20","issue_date":"2016-06-27","rate":"1","is_hot":"1","risktip":"1","reason_short":"1","id":"750200703682338816","disclaimer":"1","compare_type":"1","asc_amt":"1","create_time":"2016-07-05","comb_tag":"1","sort":"1","comb_code":"ks0010","inv_strategy":"1","risk_level":"1","fit_person":"1","comb_type":"4","min_amt":"1","setup_date":"2016-07-04","choice_type":"1","track_code":"1","status":"1"},{"comb_name":"凯石股基增长","reason":"无论是海外还是国内，股票资产长期来看能获得稳定且较优的资产回报率，而随着A股不断扩容，投资者自己选股的压力和难度越来越大，股混基金成为普通投资者参与股市投资的首选工具。精选基金并进行均衡的风格配置的股混基金组合能帮助投资者更好的分享股市收益。","is_home":"1","group_count":"5","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"股混基金平均收益","rate_exp":"高风险高收益","update_time":"2016-10-11","issue_date":"2015-12-31","rate":"基金费率包括：申购费、管理费、托管费、赎回费","is_hot":"1","risktip":"市场有风险，投资需谨慎。","reason_short":"哎呦!不错哦!","id":"692186160395608064","disclaimer":"基金净值及其收益可涨可跌，基金过往的业绩并不代表将来表现。","compare_type":"2","asc_amt":"1","create_time":"2016-04-28","comb_tag":"5","sort":"1","comb_code":"ks0004","inv_strategy":"\"精选基金获取超额收益：由于当前基金数量众多，无论是成长风格基金还是价值风格基金，基金之间业绩差异较大，通过专业的精选基金，能获取选基的超额收益。　　　　　　　　　　　　　　　　　\r\n\r\n组合优化风险收益：成长型基金弹性足，价值型基金弹性较低，但抗跌性强，二者具有一定的互补性，本组合按照优选成长型基金(40%)+价值型基金(40%)+稳健型基金(20%)构建，可以较好的控制回撤，降低组合波动。\"","risk_level":"5","fit_person":"风险偏好较高，追求资本的长期增值，能承受短期波动的投资者","comb_type":"5","min_amt":"1","setup_date":"2016-01-04","choice_type":"1","track_code":"沪深300指数   000300.sh","status":"1"},{"comb_name":"凯石债积财富","reason":"债券从长期看能为投资者带来高于货币市场的票息收益，并且风险低于股混基金，因此纯债基金组合是长期资产保值的良好配置工具。","is_home":"1","group_count":"3","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"纯债基金平均收益","group_type":"混合型","rate_exp":"中低风险中低收益","update_time":"2016-10-11","issue_date":"2015-12-31","rate":"基金费率包括：申购费、管理费、托管费、赎回费","is_hot":"1","risktip":"市场有风险，投资需谨慎。","reason_short":"纯债基金组合是长期资产保值的良好配置工具。","id":"692187717495336960","disclaimer":"基金净值及其收益可涨可跌，基金过往的业绩并不代表将来表现。","compare_type":"1","asc_amt":"1","create_time":"2016-04-28","comb_tag":"4","sort":"2","comb_code":"ks0005","inv_strategy":"精选债基，以期限在一年以内的短融类债基为主，增配中长期纯债基金构建相对稳健组合，期望为投资者带来稳定的收益。","risk_level":"2","fit_person":"风险偏好相对较低的投资者","comb_type":"2","min_amt":"1","setup_date":"2016-01-04","choice_type":"2","track_code":"中债总净价指数  0372.cs","status":"1"},{"comb_name":"凯石日日盈","reason":"货币基金是良好的流动性管理工具，而将货币基金构建组合能更好规避单只产品的短期流动性风险等。","is_home":"1","group_count":"3","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"中国人民银行公布的活期存款基准利率的收益率(税后)","rate_exp":"七日年化收益率","update_time":"2016-10-11","issue_date":"2015-12-01","rate":"管理费率（年）： 0.33%   　　　　　\r\n托管费率（年）： 0.10%  \r\n销售服务费（年）：0.25%  ","is_hot":"1","risktip":"市场有风险，投资需谨慎。","reason_short":"组合能更好规避单只产品的短期流动性风险等。","id":"692248097357377536","disclaimer":"基金净值及其收益可涨可跌，基金过往的业绩并不代表将来表现。","compare_type":"2","asc_amt":".1","create_time":"2016-04-28","comb_tag":"3","sort":"3","comb_code":"ks0006","inv_strategy":"精选货币基金，注重货币基金历史运作管理稳定性和业绩，关注流动性安全。","risk_level":"1","fit_person":"低风险，对资金流动性要求较高","comb_type":"1","min_amt":".1","setup_date":"2016-01-04","choice_type":"1","track_code":"银行一年定存利率   1.5%","status":"1"},{"comb_name":"凯石均衡稳健","reason":"1","is_home":"1","group_count":"5","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"1","group_type":"股混型","rate_exp":"1","update_time":"2016-10-11","issue_date":"2015-12-31","rate":"1","is_hot":"0","risktip":"1","reason_short":"这是一个好的组合,抢购中这是一个好的组合,抢购中这是一个好的组合,抢购中这是一个好的组合,抢购中这是一个好的组合,抢购中","id":"725516337540026368","disclaimer":"1","compare_type":"3","asc_amt":".5","create_time":"2016-04-28","comb_tag":"2","sort":"1","comb_code":"ks0008","inv_strategy":"1","risk_level":"3","fit_person":"1","comb_type":"3","min_amt":".5","setup_date":"2016-01-08","choice_type":"2","track_code":"1","status":"1"},{"comb_name":"凯石保本增利","reason":"1","is_home":"1","group_count":"4","type":"com.vstone.api.cache.fund.model.FundGroup","work_mark":"1","rate_exp":"1","update_time":"2016-12-01","issue_date":"2015-12-31","rate":"1","is_hot":"1","risktip":"1","reason_short":"这是一个好的组合","id":"725518001817432064","disclaimer":"1","compare_type":"2","asc_amt":"5000","create_time":"2016-04-28","comb_tag":"1","sort":"1","comb_code":"ks0009","inv_strategy":"1","risk_level":"1","fit_person":"1","comb_type":"2","min_amt":"10000","setup_date":"2016-01-04","choice_type":"1","track_code":"1","status":"1"}]
     */

    public boolean success;
    public String code;
    public String message;
    public List<BodyBean> body;

    public static class BodyBean {
        /**
         * comb_name : 开始10号
         * reason : 1
         * is_home : 0
         * group_count : 3
         * type : com.vstone.api.cache.fund.model.FundGroup
         * work_mark : 1
         * rate_exp : 1
         * update_time : 2016-12-20
         * issue_date : 2016-06-27
         * rate : 1
         * is_hot : 1
         * risktip : 1
         * reason_short : 1
         * id : 750200703682338816
         * disclaimer : 1
         * compare_type : 1
         * asc_amt : 1
         * create_time : 2016-07-05
         * comb_tag : 1
         * sort : 1
         * comb_code : ks0010
         * inv_strategy : 1
         * risk_level : 1
         * fit_person : 1
         * comb_type : 4
         * min_amt : 1
         * setup_date : 2016-07-04
         * choice_type : 1
         * track_code : 1
         * status : 1
         * group_type : 混合型
         */

        public String comb_name;
        public String reason;
        public String is_home;
        public String group_count;
        public String type;
        public String work_mark;
        public String rate_exp;
        public String update_time;
        public String issue_date;
        public String rate;
        public String is_hot;
        public String risktip;
        public String reason_short;
        public String id;
        public String disclaimer;
        public String compare_type;
        public String asc_amt;
        public String create_time;
        public String comb_tag;
        public String sort;
        public String comb_code;
        public String inv_strategy;
        public String risk_level;
        public String fit_person;
        public String comb_type;
        public String min_amt;
        public String setup_date;
        public String choice_type;
        public String track_code;
        public String status;
        public String group_type;

        @Override
        public String toString() {
            return "BodyBean{" +
                    "comb_name='" + comb_name + '\'' +
                    ", reason='" + reason + '\'' +
                    ", is_home='" + is_home + '\'' +
                    ", group_count='" + group_count + '\'' +
                    ", type='" + type + '\'' +
                    ", work_mark='" + work_mark + '\'' +
                    ", rate_exp='" + rate_exp + '\'' +
                    ", update_time='" + update_time + '\'' +
                    ", issue_date='" + issue_date + '\'' +
                    ", rate='" + rate + '\'' +
                    ", is_hot='" + is_hot + '\'' +
                    ", risktip='" + risktip + '\'' +
                    ", reason_short='" + reason_short + '\'' +
                    ", id='" + id + '\'' +
                    ", disclaimer='" + disclaimer + '\'' +
                    ", compare_type='" + compare_type + '\'' +
                    ", asc_amt='" + asc_amt + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", comb_tag='" + comb_tag + '\'' +
                    ", sort='" + sort + '\'' +
                    ", comb_code='" + comb_code + '\'' +
                    ", inv_strategy='" + inv_strategy + '\'' +
                    ", risk_level='" + risk_level + '\'' +
                    ", fit_person='" + fit_person + '\'' +
                    ", comb_type='" + comb_type + '\'' +
                    ", min_amt='" + min_amt + '\'' +
                    ", setup_date='" + setup_date + '\'' +
                    ", choice_type='" + choice_type + '\'' +
                    ", track_code='" + track_code + '\'' +
                    ", status='" + status + '\'' +
                    ", group_type='" + group_type + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MainBean{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
