package icu.iseenu.fundbot;

import icu.iseenu.fundbot.crontab.Crontab;
import icu.iseenu.fundbot.service.FundService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;


@SpringBootTest
class FundBotApplicationTests {

    Crontab crontab = new Crontab();

    @Autowired
    FundService fundService;

    @Test
    void contextLoads() throws Exception {
        crontab.finalNotice();
    }

    @Test
    void test() throws Exception{
        fundService.TotalRevenue();
    }


}
