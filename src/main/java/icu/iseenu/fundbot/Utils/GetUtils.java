package icu.iseenu.fundbot.Utils;

import icu.iseenu.fundbot.domain.Fund;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetUtils {
    private final static String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:22.0) Gecko/20100101 Firefox/22.0";
    private final static String CONTENT_TYPE = "application/json";
    private final static String FUND_URL = "http://fundgz.1234567.com.cn/js/";

    public Fund getFundInfo(@NotNull Fund fund) throws Exception {
        String url = FUND_URL + fund.getCode() + ".js";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //默认值我GET
        con.setRequestMethod("GET");

        //添加请求头
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("content-type", CONTENT_TYPE);
        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            return null;
        }

        BufferedReader responseReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String message = IOUtils.toString(responseReader);

        //  正则处理返回结果
        String res = message.replaceAll("[(^)]+", "");
        res = res.replaceAll("jsonpgz", "");


        //获取josn对应结果
        JSONObject jsonObject = new JSONObject(res);
        fund.setName(jsonObject.getString("name"));
        String longres = jsonObject.getString("gszzl");
        fund.setGszzl(BigDecimal.valueOf(Float.valueOf(longres)));
        String dwjz = jsonObject.getString("dwjz");
        fund.setDwjz(BigDecimal.valueOf(Float.valueOf(dwjz)));
        return fund;
    }
}
