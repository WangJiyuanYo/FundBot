package icu.iseenu.fundbot.Utils;

import icu.iseenu.fundbot.bot.WechatBot;
import icu.iseenu.fundbot.common.FundsJSON;
import icu.iseenu.fundbot.domain.Fund;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class FileToJsonUtils {

    private String filePath = "";

    public FileToJsonUtils(String path) {
        this.filePath += path;
    }

    //从JSON中读取基金信息
    public List<Fund> getTodayFundInfo() throws Exception {
        File file = new File(filePath);
        if (file == null) {
            file.createNewFile();
        }
        String content = FileUtils.readFileToString(file, "UTF-8");
        JSONArray arr = new JSONArray(content);
        List<Fund> res = new ArrayList<Fund>();

        for (int i = 0; i < arr.length(); i++) {
            Fund fund = new Fund();
            fund.setCode(arr.getJSONObject(i).getString(FundsJSON.FundCode.toString()));
            float hp = Float.valueOf(arr.getJSONObject(i).getString(FundsJSON.HoldingPrice.toString()));
            fund.setHoldingPrice(new BigDecimal(hp));
            float ps = Float.valueOf(arr.getJSONObject(i).getString(FundsJSON.PositionShare.toString()));
            fund.setHoldShare(new BigDecimal(ps));
            res.add(fund);
        }
        return res;
    }

    //获取推送配置信息
    public String getBotConfig() throws Exception {
        File file = new File(filePath);
        String content = FileUtils.readFileToString(file, "UTF-8");
        JSONObject arr = new JSONObject(content);
        String wechatKey = arr.getJSONObject("wechat").getString("key");
        return wechatKey;
    }


}
