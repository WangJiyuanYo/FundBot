package icu.iseenu.fundbot.common;


public enum JSONFilesIOC {

    FUNDS("fund_file/funds.json"),
    BOTS("bot_config/bot.json");

    private String path;


    JSONFilesIOC(String filePath) {
        this.path = filePath;
    }

    public String getPath() {
        return this.path;
    }

}