package com.hotelres.controller;

import com.alibaba.fastjson.JSONObject;
import com.show.api.ShowApiRequest;


public class ReservationDemo {
    public static void main(String[] args) throws InterruptedException {
        String hid = "1749052815";

        //获取价格计划
        String res1 = new ShowApiRequest("http://route.showapi.com/1653-4", "183050", "97aafb54f9ff4a93b5378595dd4d0475")
                .addTextPara("hotelId", hid)
                .addTextPara("inDate", "")
                .addTextPara("outDate", "")
                .post();
        JSONObject jsonObject1 = JSONObject.parseObject(res1);
        String ratePlanId = (String) jsonObject1.getJSONObject("showapi_res_body").getJSONArray("roomInfo").getJSONObject(0).getJSONArray("ratePlanInfo").getJSONObject(0).get("id");
        System.out.println(ratePlanId);
        //创建订单
        String res2 = new ShowApiRequest("https://route.showapi.com/1653-6", "183050", "97aafb54f9ff4a93b5378595dd4d0475")
                //入住人信息，每个房间仅需填写1人。【多个人代表多个房间、使用逗号‘,’分隔】
                .addTextPara("customerName", "张三")
                //价格计划Id
                .addTextPara("ratePlanId", ratePlanId)
                //酒店ID
                .addTextPara("hotelId", hid)
                /**
                 * 特殊需求 可不填 可传入多个，格式：2,8。
                 *  0   无要求
                 *          2   尽量安排无烟房
                 *          8   尽量安排大床   仅当床型为“X张大床或X张双床”时，此选项才有效
                 *          10   尽量安排双床房   仅当床型为“X张大床或X张双床”时，此选项才有效
                 *          11   尽量安排吸烟房
                 *          12   尽量高楼层
                 *          15   尽量安排有窗房
                 *          16   尽量安排安静房间
                 *          18   尽量安排相近房间
                 */
                .addTextPara("specialRemarks", "")
                .addTextPara("contactName", "李四")
                .addTextPara("contactPhone", "17635709314")
                .addTextPara("contactEmail", "")
                //入住时间，格式为：YYYY-MM-DD
                .addTextPara("inDate", "2020-07-30")
                .addTextPara("outDate", "2020-07-31")
                //入住成人数，需和实时询价时填的一样
                .addTextPara("man", "1")
                //到达时间，格式HH:mm:ss 例如09:20:30 表示早上9点20分30秒
                .addTextPara("customerArriveTime", "09:20:30")
                //可不填
                .addTextPara("callback", "")
                .addTextPara("childNum", "")
                .addTextPara("childAges", "")
                .post();
        JSONObject jsonObject2 = JSONObject.parseObject(res2);
        int ret_code1 = (int) jsonObject2.getJSONObject("showapi_res_body").get("ret_code");
        //订单创建失败
        if (ret_code1!=0){
            String tip = (String) jsonObject2.getJSONObject("showapi_res_body").get("remark");
            System.out.println(tip);
        }
        String orderId = (String) jsonObject2.getJSONObject("showapi_res_body").get("orderId");
        System.out.println(orderId);
        Thread.sleep(30000);

        //支付订单
        String res3=new ShowApiRequest("http://route.showapi.com/1653-9","183050","97aafb54f9ff4a93b5378595dd4d0475")
                .addTextPara("o",orderId)
                .post();
        JSONObject jsonObject3 = JSONObject.parseObject(res3);
        int ret_code2 = (int) jsonObject3.getJSONObject("showapi_res_body").get("ret_code");
        //支付失败
        if (ret_code2!=0){
            String tip = (String) jsonObject3.getJSONObject("showapi_res_body").get("remark");
            System.out.println(tip);
        }
    }

}
