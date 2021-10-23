package icu.iseenu.fundbot.controller;


import icu.iseenu.fundbot.Utils.WeekdayAndHolidayCheck;
import icu.iseenu.fundbot.crontab.Crontab;
import icu.iseenu.fundbot.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


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

@RestController
@Slf4j
public class FundController {

    @Autowired
    private FundService fundService;

    @GetMapping("/gssy")
    public boolean run() {
        if (WeekdayAndHolidayCheck.checkWeekdayAndHoliday()) {
            return fundService.getTodayInfo();
        }
        return false;
    }

}
