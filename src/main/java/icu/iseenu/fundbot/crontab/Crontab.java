package icu.iseenu.fundbot.crontab;


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

//    private FundController fundController = new FundController();

    private FundServiceImpl fundService = new FundServiceImpl();

    //14.30 操作提示
    @Scheduled(cron = "0 30 14 1/1 * *")
    public void operationRemind() {
        log.info("start job ; 14:30");
        boolean res = fundService.getTodayInfo();
        log.info("执行结果 " + res);
    }

    //15.00 收益提示
    @Scheduled(cron = "0 1 15 1/1 * *")
    public void incomeNotice() {
        log.info("start job ; 15:01");
        boolean res = fundService.getTodayInfo();
        log.info("执行结果 " + res);
    }


    //21:00 收益提示
    @Scheduled(cron = "0 0 21 1/1 * *")
    public void finalNotice() {
        log.info("start job ; 21:00");
        boolean res = fundService.getTodayInfo();
        log.info("执行结果 " + res);
    }

    @Scheduled(cron = "0 0 23 1/1 * *")
    public void totalRevenue() {
        log.info("start job ; 23:00");
        boolean res = fundService.TotalRevenue();
        log.info("执行结果 " + res);
    }
}
