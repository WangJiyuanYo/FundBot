# Fund Push Bot

## 项目描述

> 每天14：30分和15：00推送当日基金的估算收益情况到机器人上
>
>现在仅支持企业微信机器人

## 版本预览

| 日期          | 版本号   | 描述                                                         |
| ------------- | -------- | ------------------------------------------------------------ |
| 2021年6月28日 | Alpha1.0 | 初步完成简单的计算和推送功能                                 |
|2021年7月6日   | Alpha1.1 | 代码重构 重写计算规则|
|2021年10月23日  |Alpha4.0|修Bugs增加节假日判断21.00推送|

### 项目计划

| 版本号   | 增加计划                                       |
| -------- | ---------------------------------------------- |
| Alpha4.0 | 增加当日收益最终推动，暂定为21：00执行自动推送 |

**不要在json文件中写注释，更不要修改key**

## 配置文件

> `bot_config`目录下存储的是推送机器人配置信息 （自定义未完善 ）
>
>`funds`存储基金信息，存放个人用户基金的信息内容
>
> `years`存储当年的节假日信息不要修改

## 配置详解

### bot_config/bot.json

`wechat-key`：配置企业微信机器人的key

```json
{
  "wechat": {
    "key": "企业微信机器人的Key"
  },
  "serverAPI": "Server酱预留"
}

```

### fund_file.json

一个基金包含三个重要信息

`FundCode`：基金编码

`HoldingPrice`：持仓价格

`PositionShare`：持仓份额

---



单个基金示例

```json
[
  {
    "FundCode": "001156",
    "HoldingPrice": "2.6846",
    "PositionShare": "279.37"
  }
]
```

多个基金示例：

```json
[
  {
    "FundCode": "001156",
    "HoldingPrice": "2.6846",
    "PositionShare": "279.37"
  },
  {
    "FundCode": "161725",
    "HoldingPrice": "1.5017",
    "PositionShare": "66.59"
  },
  {
    "FundCode": "400015",
    "HoldingPrice": "3.2592",
    "PositionShare": "223.37"
  }
]
```

## 打包

```shell
# 安装maven 依赖
mvn install 

# 打包项目
mvn package

# 将打包好的jar包放入服务器中运行
```

## 运行

Windows下

```shell
# 运行
javaw -jar FundBot.jar

#停止
netstat -aon|findstr "8080"
taskkill /F /pid [pid]
```

Linux下

```shell
nohup java -jar FundBot.jarnohup &

#停止
lsof -i:8080
kill -9 [pid]
```

>
>
>运行后默认情况下，定时推送服务。当然你也可以使用主动调用的方式

```http
<!-- 主动调用方式 -->

http://localhost:8080/gssy
```

