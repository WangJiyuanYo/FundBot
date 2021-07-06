package icu.iseenu.fundbot.service.impl;

import icu.iseenu.fundbot.Utils.FileToJsonUtils;
import icu.iseenu.fundbot.Utils.GetUtils;
import icu.iseenu.fundbot.bot.WechatBot;
import icu.iseenu.fundbot.common.JSONFilesIOC;
import icu.iseenu.fundbot.domain.Fund;
import icu.iseenu.fundbot.service.FundService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FundServiceImpl implements FundService {

    /**
     * 估算收益
     *
     * @return true : false ? 推送成功后的返回结果
     */
    @Override
    public boolean getTodayInfo() {
        try {
            List<Fund> funds = new FileToJsonUtils(JSONFilesIOC.FUNDS.getPath()).getTodayFundInfo();
            if (funds == null) {
                return false;
            }
            List<Fund> results = get(funds);
            BigDecimal todayTotal = new BigDecimal(0.00);
            for (Fund result : results) {
                result.setGssy((result.getGszzl().multiply(result.getHoldShare()).multiply(result.getHoldingPrice())).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
                todayTotal = todayTotal.add(result.getGssy());
            }
            Fund fund = new Fund();
            fund.setName("总收益");
            fund.setGssy(todayTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
            System.out.println(fund.getGssy());
            results.add(fund);
            String keyStatus = new FileToJsonUtils(JSONFilesIOC.BOTS.getPath()).getBotConfig();
            if (keyStatus != null) {
                return WechatBot.push(keyStatus, results);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //对每个基金进行处理拿到返回结果
    private List<Fund> get(List<Fund> fundList) throws Exception {
        for (Fund fund : fundList) {
            new GetUtils().getFundInfo(fund);
        }
        return fundList;
    }
}
