package icu.iseenu.fundbot.crontab;


import icu.iseenu.fundbot.Utils.WeekdayAndHolidayCheck;
import icu.iseenu.fundbot.common.Result;
import icu.iseenu.fundbot.service.impl.FundServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务列表
 */
@Component
@Slf4j
public class Crontab {

    private FundServiceImpl fundService = new FundServiceImpl();

    //14.30 操作提示
    @Scheduled(cron = "0 30 14 1/1 * *")
    public void operationRemind() {
        pushInfo("start job ; 14:30");
    }

    //15.00 收益提示
    @Scheduled(cron = "0 1 15 1/1 * *")
    public void incomeNotice() {
        pushInfo("start job ; 15:01");
    }


    //21:00 收益提示
    @Scheduled(cron = "0 0 21 1/1 * *")
    public void finalNotice() {
        pushInfo("start job ; 21:00");
    }

    @Scheduled(cron = "0 0 23 1/1 * *")
    public void totalRevenue() {
        pushInfo("start job ; 23:00");
    }

    private Result pushInfo(String time) {
        log.info(time);
        if (WeekdayAndHolidayCheck.checkWeekdayAndHoliday()) {
            return fundService.TotalRevenue();
        } else {
            log.info("非工作时间不执行");
            return Result.error("非工作时间不执行");
        }
    }
}
