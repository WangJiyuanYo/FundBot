package icu.iseenu.fundbot;

import icu.iseenu.fundbot.crontab.Crontab;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;


@SpringBootTest
class FundBotApplicationTests {

    Crontab crontab = new Crontab();

    @Test
    void contextLoads() throws Exception {
        crontab.finalNotice();
    }

    @Test
    void readFile() throws Exception {
        File file = new File("/test.txt");
        System.out.println(file.getName());

    }
}
