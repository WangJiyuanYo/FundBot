package icu.iseenu.fundbot.Utils;

import org.json.JSONObject;

/**
 * 针对返回的结果进行处理
 */
public class JsonResponse {
    public static boolean checkResult(JSONObject response) {
        if (response == null) {
            return false;
        }
        return response.getInt("errcode") == 0;
    }
}
