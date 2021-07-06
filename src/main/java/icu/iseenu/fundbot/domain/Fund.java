package icu.iseenu.fundbot.domain;

import lombok.Data;

import java.math.BigDecimal;

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
    private BigDecimal holdingPrice;

    //持仓份额
    private BigDecimal holdShare;

    //估算涨跌百分比
//    private BigDecimal gszzl = 0.00f;

    //估算值
    private BigDecimal gsz;

    //估算收益
    private BigDecimal gssy;

    //估算涨跌
    private BigDecimal gszzl;

    //当日净值
    private BigDecimal dwjz;

    private BigDecimal totalRevenue;

}
