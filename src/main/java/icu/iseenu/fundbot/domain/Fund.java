package icu.iseenu.fundbot.domain;

import lombok.Data;

/**
 * {
 * ["fundcode"]=>"519983"           //基金代码
 * ["name"]=>"长信量化先锋混合A"    //基金名称
 * ["jzrq"]=>"2018-09-21"           //净值日期
 * ["dwjz"]=>"1.2440"               //当日净值
 * ["gsz"]=>"1.2388"                //估算净值
 * ["gszzl"]=>"-0.42"               //估算涨跌百分比 即-0.42%
 * ["gztime"]=>"2018-09-25 15:00"   //估值时间
 * }
 */

@Data
public class Fund {
    //基金名称
    private String name = "";

    //基金代码
    private String code = "";

    //持仓成本价
    private float holdingPrice = 0.00f;

    //持仓份额
    private float holdShare = 0.00f;

    //估算涨跌百分比
//    private float gszzl = 0.00f;

    //估算值
    private float gsz = 0.00f;

    //估算收益
    private float gssy = 0.00f;

    //估算涨跌
    private float gszzl = 0.00f;

    //收益率
//    private float syl = 0.00f;
}
