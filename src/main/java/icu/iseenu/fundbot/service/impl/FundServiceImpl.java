package icu.iseenu.fundbot.service.impl;

import icu.iseenu.fundbot.Utils.FileToJsonUtils;
import icu.iseenu.fundbot.Utils.GetUtils;
import icu.iseenu.fundbot.bot.WechatBot;
import icu.iseenu.fundbot.common.JSONFilesIOC;
import icu.iseenu.fundbot.common.Result;
import icu.iseenu.fundbot.domain.Fund;
import icu.iseenu.fundbot.service.FundService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class FundServiceImpl implements FundService {

    /**
     * 估算收益
     *
     * @return true : false ? 推送成功后的返回结果
     */
    @Override
    public Result getTodayInfo() {
        try {
            List<Fund> funds = new FileToJsonUtils(JSONFilesIOC.FUNDS.getPath()).getTodayFundInfo();
            if (funds == null) {
                return Result.error("Getting Funds Is Empty");
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
            results.add(fund);
            String keyStatus = new FileToJsonUtils(JSONFilesIOC.BOTS.getPath()).getBotConfig();
            if (keyStatus != null && results != null) {
                return WechatBot.push(keyStatus, results);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 总收益计算Methods
     *
     * @return
     */
    @Override
    public Result TotalRevenue() {
        try {
            List<Fund> funds = new FileToJsonUtils(JSONFilesIOC.FUNDS.getPath()).getTodayFundInfo();
            if (Objects.isNull(funds)) {
                return Result.error("Getting Funds Is Empty");
            }
            List<Fund> results = get(funds);
            BigDecimal totalRevenue = new BigDecimal(0.0);
            for (Fund result : results) {
                result.setTotalRevenue((result.getDwjz()
                        .subtract(result.getHoldingPrice()))
                        .multiply(result.getHoldShare())
                        .setScale(2, BigDecimal.ROUND_HALF_UP));
                totalRevenue = totalRevenue.add(result.getTotalRevenue())
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            String key = new FileToJsonUtils(JSONFilesIOC.BOTS.getPath()).getBotConfig();
            return WechatBot.push(key, results);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }


    //对每个基金进行处理拿到返回结果
    private List<Fund> get(List<Fund> fundList) throws Exception {
        for (Fund fund : fundList) {
            new GetUtils().getFundInfo(fund);
        }
        return fundList;
    }
}
