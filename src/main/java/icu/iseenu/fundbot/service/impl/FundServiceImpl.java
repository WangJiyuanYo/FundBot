package icu.iseenu.fundbot.service.impl;

import icu.iseenu.fundbot.Utils.FileToJsonUtils;
import icu.iseenu.fundbot.Utils.GetUtils;
import icu.iseenu.fundbot.bot.WechatBot;
import icu.iseenu.fundbot.domain.Fund;
import icu.iseenu.fundbot.service.FundService;
import org.springframework.stereotype.Service;

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
            List<Fund> funds = new FileToJsonUtils("fund_file/user_fund.json").getTodayFundInfo();
            if (funds == null) {
                return false;
            }
            List<Fund> results = get(funds);
            float todayTotal = 0.00f;
            for (Fund result : results) {
                result.setGssy(result.getGszzl() * result.getHoldShare() / 100.0f);
                todayTotal += result.getGssy();
            }
            Fund fund = new Fund();
            fund.setName("总收益");
            fund.setGssy(todayTotal);
            results.add(fund);

            String keyStatus = new FileToJsonUtils("bot_config/bot.json").getBotConfig();
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
