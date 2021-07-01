package icu.iseenu.fundbot.crontab;

import icu.iseenu.fundbot.controller.FundController;
import icu.iseenu.fundbot.service.FundService;
import icu.iseenu.fundbot.service.impl.FundServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        fundService.getTodayInfo();
    }

    //15.00 收益提示
    @Scheduled(cron = "0 1 15 1/1 * *")
    public void incomeNotice() {
        log.info("start job ; 15:01");
        fundService.getTodayInfo();
    }


    //21:00 收益提示
    @Scheduled(cron = "0 0 21 1/1 * *")
    public void finalNotice() {
        System.out.println(fundService);
        log.info("start job ; 21:00");
        fundService.getTodayInfo();
    }
}
