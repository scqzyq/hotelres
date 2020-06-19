package com.hotelres.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.hotelres.bean.Hotel;
import com.hotelres.bean.Order;
import com.hotelres.bean.User;
import com.hotelres.service.impl.HotelServiceImpl;
import com.hotelres.service.impl.OrderServiceImpl;
import com.hotelres.service.impl.UserServiceImpl;
import com.show.api.ShowApiRequest;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DecimalFormat;

/**
 * @author scqzy
 */
@Controller
@RequestMapping("/analyse")
public class AnalyseController {
    final static int NEIGHBORHOOD_NUM = 20;

    @Resource
    private UserServiceImpl userService;
    @Resource
    private HotelServiceImpl hotelService;
    @Resource
    private OrderServiceImpl orderService;

    @RequestMapping(value = "/res", produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    public void UserCFAnalyse(String datein, String dateout, HttpServletRequest request, HttpServletResponse response) throws TasteException, IOException, InterruptedException, ServletException, AlipayApiException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        try {
            File file = new File("p.data");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getName(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //String username = (String) session.getAttribute("username");

            User user = userService.findByUsername(username);

            String data = 9999 + "," + 1 + "," + user.getJiudianweizhi() + "\r\n";
            bufferedWriter.write(data);
            data = 9999 + "," + 2 + "," + user.getWeishengqingjie() + "\r\n";
            bufferedWriter.write(data);
            data = 9999 + "," + 3 + "," + user.getShebeisheshi() + "\r\n";
            bufferedWriter.write(data);
            data = 9999 + "," + 4 + "," + user.getFuwuzhiliang() + "\r\n";
            bufferedWriter.write(data);

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        准备数据 这里是电影评分数据
        数据集，其中第一列表示用户id；第二列表示商品id；第三列表示评分，评分是5分制
        */
        String url = "/home/scqzy/Downloads/apache-tomcat-8.5.39/bin/p.data";
        /*
        将数据加载到内存中
        基于文件的model，通过文件形式来读入,且此类型所需要读入的数据的格式要求很低，只需要满足每一行是用户id，物品id，用户偏好，且之间用tab或者是逗号隔开即可
        */
        DataModel dataModel = new FileDataModel(new File(url));

        /*
        计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        基于用户的协同过滤算法，基于物品的协同过滤算法，这里使用了EuclideanDistanceSimilarity
        计算欧式距离，欧式距离来定义相似性，用s=1/(1+d)来表示，范围在[0,1]之间，值越大，表明d越小，距离越近，则表示相似性越大
        */
        UserSimilarity similarity = new EuclideanDistanceSimilarity(dataModel);
        /*
        计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居。
        NEIGHBORHOOD_NUM指定用户邻居数量
        */
        NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, similarity, dataModel);

        long[] userNeighborhood = neighbor.getUserNeighborhood(9999);

        File file = new File("p.data");
        File file2 = new File("p1.data");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter writer = new PrintWriter(file2);
        String line;
        while ((line = reader.readLine()) != null) {
            // 判断条件，根据自己的情况书写，会删除所有符合条件的行
            if (line.contains("9999")) {
                // 读取后面的几行,废弃
//        reader.readLine();
//        reader.readLine();
//        reader.readLine();
                continue;
            }
            writer.println(line);
            writer.flush();
        }
        reader.close();
        writer.close();

        // 删除老文件
        file.delete();
        file2.renameTo(file);

        User user = userService.findByUsername(username);

        for (int i = 0; i < 20; ++i) {
            int hid = (int) userNeighborhood[i];
            Hotel hotel = hotelService.findByHid(hid);

            //获取价格计划
            String res1 = new ShowApiRequest("http://route.showapi.com/1653-4", "183050", "97aafb54f9ff4a93b5378595dd4d0475")
                    .addTextPara("hotelId", String.valueOf(hid))
                    .addTextPara("inDate", datein)
                    .addTextPara("outDate", dateout)
                    .post();
            JSONObject jsonObject1 = JSONObject.parseObject(res1);
            JSONArray jsonArray = jsonObject1.getJSONObject("showapi_res_body").getJSONArray("roomInfo");
            String ratePlanId;
            if (jsonArray.isEmpty() || jsonArray.size() < 1) {
                continue;
            } else {
                ratePlanId = (String) jsonObject1.getJSONObject("showapi_res_body").getJSONArray("roomInfo").getJSONObject(0).getJSONArray("ratePlanInfo").getJSONObject(0).get("id");
            }


            //创建订单
            String res2 = new ShowApiRequest("https://route.showapi.com/1653-6", "183050", "97aafb54f9ff4a93b5378595dd4d0475")
                    //入住人信息，每个房间仅需填写1人。【多个人代表多个房间、使用逗号‘,’分隔】
                    .addTextPara("customerName", user.getRealname())
                    //价格计划Id
                    .addTextPara("ratePlanId", ratePlanId)
                    //酒店ID
                    .addTextPara("hotelId", String.valueOf(hid))
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
                    .addTextPara("contactName", user.getRealname())
                    .addTextPara("contactPhone", user.getTel())
                    .addTextPara("contactEmail", "")
                    //入住时间，格式为：YYYY-MM-DD
                    .addTextPara("inDate", datein)
                    .addTextPara("outDate", dateout)
                    //入住成人数，需和实时询价时填的一样
                    .addTextPara("man", "1")
                    //到达时间，格式HH:mm:ss 例如09:20:30 表示早上9点20分30秒
                    .addTextPara("customerArriveTime", "18:20:30")
                    //可不填
                    .addTextPara("callback", "")
                    .addTextPara("childNum", "")
                    .addTextPara("childAges", "")
                    .post();
            JSONObject jsonObject2 = JSONObject.parseObject(res2);
            int ret_code1 = (int) jsonObject2.getJSONObject("showapi_res_body").get("ret_code");
            //订单创建失败
            if (ret_code1 != 0) {
                String tip = (String) jsonObject2.getJSONObject("showapi_res_body").get("remark");
                System.out.println(tip);
//                response.setContentType("text/html;charset=utf-8");
//                response.getWriter().write("<script>alert('" + tip + "'); window.location='/index.jsp'; window.close();</script>");
//                response.getWriter().flush();
                continue;
            }
            String orderId = (String) jsonObject2.getJSONObject("showapi_res_body").get("orderId");
            System.out.println(orderId);

            //查询订单
            String res3 = new ShowApiRequest("http://route.showapi.com/1653-7", "183050", "97aafb54f9ff4a93b5378595dd4d0475")
                    .addTextPara("orderId", orderId)
                    .post();

            JSONObject jsonObject3 = JSONObject.parseObject(res3);
            int ret_code2 = (int) jsonObject3.getJSONObject("showapi_res_body").get("ret_code");
            if (ret_code2 != 0) {
                String tip = (String) jsonObject3.getJSONObject("showapi_res_body").get("remark");
                System.out.println(tip);
//                response.setContentType("text/html;charset=utf-8");
//                response.getWriter().write("<script>alert('" + tip + "'); window.location='/index.jsp'; window.close();</script>");
//                response.getWriter().flush();
                continue;
            }
            //持久化订单
            Order order = new Order();
            order.setOid(Integer.valueOf(orderId));
            order.setUsername(username);
            //contactName
            String contactName = (String) jsonObject3.getJSONObject("showapi_res_body").getJSONObject("contactInfo").get("contactName");
            order.setContactName(contactName);
            //hotelName
            String hotelName = (String) jsonObject3.getJSONObject("showapi_res_body").getJSONObject("hotelInfo").get("hotelName");
            order.setHotelName(hotelName);
            //roomType
            String roomType = (String) jsonObject3.getJSONObject("showapi_res_body").getJSONObject("hotelInfo").get("roomType");
            order.setRoomType(roomType);
            //checkin
            String checkin = (String) jsonObject3.getJSONObject("showapi_res_body").getJSONObject("orderInfo").get("checkin");
            order.setCheckin(checkin);
            //checkout
            String checkout = (String) jsonObject3.getJSONObject("showapi_res_body").getJSONObject("orderInfo").get("checkout");
            order.setCheckout(checkout);
            //totalPrice
            Object totalPrice = jsonObject3.getJSONObject("showapi_res_body").getJSONObject("orderInfo").get("totalPrice");
            order.setTotalPrice(String.valueOf(totalPrice));
            //orderTime
            String orderTime = (String) jsonObject3.getJSONObject("showapi_res_body").getJSONObject("orderInfo").get("orderTime");
            order.setOrderTime(orderTime);

            System.out.println(order);

            double num_str = Double.parseDouble(totalPrice.toString());

            DecimalFormat df = new DecimalFormat(".00");
            String tem = df.format(num_str);


            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", "2016102400750255", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTQpM8SV5kfVcnc3S6rnRGIH6v/hOllnyPKndBeWE7KST4HrV0VQNpkcApU2SUyzVmALiOEgxVoUcULhBsp4Oq4hSeN57aLFHlT4uMbRCzmCGTDkVwnoxfOtxLjzDOZVrxEwNt1dKKUiTbLx4gbmub3C4QTKWpJAwQXEOLRQ3CgjhvPsI85PzI1U6bvlTuYV+DAk9WqvDtkMOZakO5NZLhJRq0WdQcZF19RXE9lWugoy77/xI5IqtSzNmYy1ytYDBxfTTeMig/dw4dMWNzQh6DGq/g36MWPLQ8H1+j7BnUTbs1ZQ1+wtRdREMHsZByhlx+NQpVsQv+QEzST/CW0cQvAgMBAAECggEANaRdv+s2AEMMOWm8Trh30IfLvk4+V+Zej0Q31tjhS0ysk9mT3cGOEamvJx6il/UCCZsfn1kBtzudicNR5lKLCvd57TxzmCPm3TJBsjCUkedL66aaMvD+Tz5AuyF49OZE32Y+rIbkP9C2bkVWoxe2CTuGaBeTjO59moQRcqz/2ydGXjHDD5xAVbxGZpsPcVeLHCl0TyaSx/+xr5HTYTGuhGFC48C/T0LUb5Ztt8wieZrWDyR1iZqI0D+1HQtzimywULh9W1CpC/8ssleCkiU7uLpbIOFHnNh09cR97FZhoDk7KoQccGsUKm+Au/jGuaOhkTKewb4WKxqV7yk9D9drcQKBgQDrB3acqcQHdt6eKCDi7qZpS+Foa2AE4xNQaA/lmT9vF13whATCHGgfJDCjVKQgS8aGlaiA1JfSNLW67j0nilgdBHlCLLnD8/sdtcGEDBnlXPOApCtQMqc9EPdsUsBL9KY8X/Vi/OspnmPJM7R09EjSBsY4Sn8pq98n9VSoDRIMKQKBgQCgZko1UsuvsXW05HPZQtZraie6UF9vyF1IeoYpoi+ZU6w2xyZ3N9eSnmjZ5XLEDDfdMPvO9IZce02nuY0uvH3oUvWp4iTHB2uW02tm5Z7TWiOrNTyMeO2U9K/ffcb46/HhK42Ne+VoeupZr1dhXMePSH6YWQ8poWRnTCY6bNrYlwKBgQCjKV49qlmNeQdZMrCJ+nWKmgQVEp3pJp9M7cFDep0gCU/M3knTXBcor14YtXTvesuKuTDupyfy5WbYK/ZGkfNTybxIpXYdRC6r1HdQrXn40gu13cK3/UI+0Z2DFPusf148gb2AB+dV0ECj+IIpw0EelVdc0tLu4gOc4OLxe4lw6QKBgBWzoUizqhFcXkOaQT5c96vS0gs4QqFaJ5zdzvclFXGWS4+hxEePC6CvxBnvHGcADDLmJLusg91RZYFS7pIrOJHNX6WbjkRLcbgXZMmoOaP45yiL7GRJHEPyEzWWy+vz6GcBwIYrBC/tqXV27siih/GP6mjsa8vDJVWAjQ+MmNODAoGAdADUDLStvAKAfhMxEvjZWfIroEj2zMn1I+wXBKdGjblMUeB6T4vuvfRGD0YNK/4NRMi2KHSJkDoYmBQ9F8iwQR87Psrz466Hj/fwM71yaEzHCPLVs7MaXSVCoctXTWWDco/qCTaZgcqk9UUFu//2agFO4aoGlgV1BmFn49ywTUM=", "json", "utf-8",
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm3WEPgzEygPVb28UGtMtzErCpEVBBYPhTfbiqUQFdq8tv+hAR9eofC+FWCUOqpRl3RjhB49WxrvPx1UNPXIoq92SenwjdJhU+3BvTFlfDJXVj2+4xTM6h7B2gFionVY9OnL4FqcsX4obd5mhuQJQX8SpwXDd2WI2FOWreSEPBTyUjEgS4SL8tpMps0UOaoOb1Z1fpydGob+9r15FApfYIG/vh32dAx7LGyRWZXtt2pQ9Siz7TLH48+/jouoQ5GamPVTRdqAjTG+sIZYCJttIkZgZjQYc9egXhhf4Kanc6NLU+0z8v92RCfN7siyS6jubBd8LYbeMmiPn8ZWcmTgmGQIDAQAB", "RSA2");
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); //创建API对应的request
            alipayRequest.setReturnUrl("http://localhost:8080/order.jsp");
            alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp"); //在公共参数中设置回跳和通知地址
            alipayRequest.setBizContent("{" +
                    "\"out_trade_no\":\""+orderId+"\"," +
                    "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                    "\"total_amount\":\""+tem+"\"," +
                    "\"subject\":\""+hotelName+"\"," +
                    "\"body\":\""+roomType+"\"," +
                    "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                    "\"extend_params\":{" +
                    "\"sys_service_provider_id\":\"2088102180866952\"" +
                    "    }" +
                    "  }"); //填充业务参数
            AlipayTradePagePayResponse alipayTradePagePayResponse = alipayClient.pageExecute(alipayRequest);

            if(alipayTradePagePayResponse.isSuccess()){
                String form = "";
                try {
                    form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                }
                orderService.addOrder(order);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write(form); //直接将完整的表单html输出到页面
                response.getWriter().flush();
                response.getWriter().close();
                AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
                alipayTradeQueryRequest.setBizContent("{" +
                        "\"out_trade_no\":\""+orderId+"\"," +
                        "      \"query_options\":[" +
                        "        \"TRADE_SETTLE_INFO\"" +
                        "      ]" +
                        "  }");
                AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.pageExecute(alipayTradeQueryRequest);
                if(alipayTradeQueryResponse.isSuccess()){
                    System.out.println(alipayTradeQueryResponse.getMsg());
                    System.out.println("调用成功");
                } else {
                    System.out.println("调用失败");
                }
            }else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("<script>alert('支付失败！'); window.location='/order.jsp'; window.close();</script>");
                response.getWriter().flush();
            }
            break;


        }
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().write(form); //直接将完整的表单html输出到页面
//            response.getWriter().flush();
//            response.getWriter().close();

/*
            //Thread.sleep(20000);
            //支付订单
            String res4 = new ShowApiRequest("http://route.showapi.com/1653-9", "183050", "97aafb54f9ff4a93b5378595dd4d0475")
                    .addTextPara("o", orderId)
                    .post();
            JSONObject jsonObject4 = JSONObject.parseObject(res4);
            int ret_code3 = (int) jsonObject4.getJSONObject("showapi_res_body").get("ret_code");
            //支付失败
            if (ret_code3 != 0) {
                String tip = (String) jsonObject4.getJSONObject("showapi_res_body").get("remark");
                System.out.println(tip);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("<script>alert('" + tip + "'); window.location='/index.jsp'; window.close();</script>");
                response.getWriter().flush();
            }
            System.out.println("test go on");*/
//            response.sendRedirect("/order.jsp");
    }

    @RequestMapping("/testget")
    public String testget(String datein, String dateout) {
        System.out.println(datein);
        System.out.println(dateout);
        return "save";
    }
}
