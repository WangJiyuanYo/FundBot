package icu.iseenu.fundbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
public class FundBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundBotApplication.class, args);
    }

}
