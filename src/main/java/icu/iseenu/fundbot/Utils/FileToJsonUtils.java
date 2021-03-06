package icu.iseenu.fundbot.Utils;

import icu.iseenu.fundbot.common.FundsJSON;
import icu.iseenu.fundbot.common.JSONFilesIOC;
import icu.iseenu.fundbot.domain.Fund;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
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

    //获取假日信息
    public JSONArray getHolidayInfo() {
        File file = new File(filePath);
        String content = null;
        try {
            content = FileUtils.readFileToString(file, "UTF-8");
            JSONObject arr = new JSONObject(content);
            return arr.getJSONArray("days");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }


}
