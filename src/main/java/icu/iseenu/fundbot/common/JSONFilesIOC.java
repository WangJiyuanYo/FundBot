package icu.iseenu.fundbot.common;

/**
 * 文件路径映射
 */
public enum JSONFilesIOC {

    FUNDS("fund_file/funds.json"),
    BOTS("bot_config/bot.json"),
    YEARS("years/");

    private String path;

    JSONFilesIOC(String filePath) {
        this.path = filePath;
    }

    public String getPath() {
        return this.path;
    }

}
