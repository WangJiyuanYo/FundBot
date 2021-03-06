package icu.iseenu.fundbot.bot;

import icu.iseenu.fundbot.Utils.JsonResponse;
import icu.iseenu.fundbot.common.Result;
import icu.iseenu.fundbot.domain.Fund;
import okhttp3.*;
import org.json.JSONObject;

import java.util.List;

public class WechatBot {

    private static final String BOT_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    public static Result push(String key, List<Fund> funds) throws Exception {
        String url = BOT_URL + key;

        StringBuilder markdown = new StringBuilder();
        markdown.append("# 收益提示 \n");
        for (Fund fund : funds) {
            markdown.append(">").
                    append(fund.getName())
                    .append(fund.getGssy())
                    .append(" 持有收益 ")
                    .append(fund.getTotalRevenue())
                    .append("\n");
        }
        //传输的数据
        JSONObject data = new JSONObject();
        //context 字段
        JSONObject context = new JSONObject();
        context.put("content", markdown);
        data.put("msgtype", "markdown");
        data.put("markdown", context);
        RequestBody body = RequestBody.create(JSON, String.valueOf(data));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        JSONObject responseData = new JSONObject(response.body().string());
        boolean result = JsonResponse.checkResult(responseData);
        return result ? Result.ok() : Result.error("推送结果异常");
    }

}
