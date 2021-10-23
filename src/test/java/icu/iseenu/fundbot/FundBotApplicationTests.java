package icu.iseenu.fundbot;

import icu.iseenu.fundbot.Utils.GetUtils;
import icu.iseenu.fundbot.crontab.Crontab;
import icu.iseenu.fundbot.domain.Fund;
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
    void test() throws Exception {
        fundService.TotalRevenue();
    }

    /**
     * 17点00分 获取到的值
     * {"gztime":"2021-10-21 15:00","gszzl":"-0.87","fundcode":"001156","name":"申万菱信新能源汽车混合","dwjz":"2.9610","jzrq":"2021-10-20","gsz":"2.9352"}
     * @throws Exception
     */
    @Test
    void testGetFunds() throws Exception {
        Fund fund = new Fund();
        fund.setCode("001156");
        GetUtils getUtils = new GetUtils();
        getUtils.getFundInfo(fund);
    }


}
