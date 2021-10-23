/**
 * @program: FundBot
 * @description: 节假日及周末校验
 * @author: Qing Miao Dan Xie
 * @create: 2021-10-21 17:18
 **/
package icu.iseenu.fundbot.Utils;

import icu.iseenu.fundbot.common.JSONFilesIOC;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class WeekdayAndHolidayCheck {
    /**
     * 判断今天是否为工作日，是：false
     *
     * @return isWork
     */
    public static boolean checkWeekdayAndHoliday() {
        boolean isWork = false;
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            log.warn("Today is " + today + "it's Not work");
            return false;
        }
        try {
            isWork = checkHoliday(today);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("Today is " + today + " it's work ?" + isWork);
        return isWork;

    }

    /**
     * 判断是否为假日，是:false
     *
     * @param date
     * @return isWork
     */
    private static boolean checkHoliday(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(date);
        JSONArray jsonArray = new FileToJsonUtils(JSONFilesIOC.YEARS + "/2021.json").getHolidayInfo();
        if (Objects.isNull(jsonArray)) {
            log.error("Getting year info error");
            return true;
        } else {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String jsonDay = jsonObject.getString("date");
                if (Objects.equals(jsonDay, today)) {
                    log.info("Today is holiday,It's not work");
                    // help GC
                    jsonObject = null;
                    jsonArray = null;
                    return false;
                }
            }
        }
        jsonArray = null;
        return true;
    }
}
