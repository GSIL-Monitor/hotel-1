package com.travel.hotel.dlt.schedule;

import com.alibaba.fastjson.JSON;
import com.travel.channel.dao.DltMapRoomPOMapper;
import com.travel.hotel.BaseTest;
import com.travel.hotel.dlt.response.dto.DltOrderInfo;
import com.travel.hotel.dlt.service.DltHotelOrderService;
import com.travel.hotel.product.entity.po.PricePlanPO;
import org.junit.Test;

/**
 *   2018/5/14.
 */
public class GetDltOrderInfoScheduleTest extends BaseTest{


    private GetDltOrderInfoSchedule getDltOrderInfoSchedule;

    private DltHotelOrderService dltHotelOrderService;

    private DltMapRoomPOMapper dltMapRoomPOMapper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getDltOrderInfoSchedule = (GetDltOrderInfoSchedule) context.getBean("getDltOrderInfoSchedule");
        dltHotelOrderService = (DltHotelOrderService)context.getBean("dltHotelOrderService");
        dltMapRoomPOMapper = (DltMapRoomPOMapper)context.getBean("dltMapRoomPOMapper");
    }

    @Test
    public void execute() throws Exception {
//        getDltOrderInfoSchedule.getDltOrderInfo();
    }

//    @Test
    public void getDltOrderInfo() throws Exception {
        getDltOrderInfoSchedule.getDltOrderInfo("2446465EBK","M10000001");
    }

//    @Test
    public void createOrder() throws Exception{
        String merchantCode = "M10000001";
//        String respStr = "{\"ResponseStatus\":{\"Timestamp\":\"/Date(1537013400071+0800)/\",\"Ack\":\"Success\",\"Errors\":[],\"Build\":null,\"Version\":\"v1\",\"Extension\":[{\"Id\":\"CLOGGING_TRACE_ID\",\"Version\":null,\"ContentType\":null,\"Value\":\"6373614289265404130\"},{\"Id\":\"RootMessageId\",\"Version\":null,\"ContentType\":null,\"Value\":\"921813-0a1c5313-426948-1559445\"}]},\"resultStatus\":{\"resultCode\":0,\"resultMsg\":\"success\"},\"pagingType\":{\"pageSize\":100,\"pageIndex\":1,\"totalRecords\":20,\"totalPages\":1},\"dltOrderIds\":[\"552304692EBK\",\"37447388834SMS\",\"39458788811SMS\",\"552268175EBK\",\"552286294EBK\",\"552306927EBK\",\"552292150EBK\",\"552280585EBK\",\"751649B2BOffLine\",\"5588260Qunar\",\"552334243EBK\",\"552338588EBK\",\"552340932EBK\",\"38505288851SMS\",\"39444788854SMS\",\"552339974EBK\",\"5560299Qunar\",\"5588364Qunar\",\"5588365Qunar\",\"751860B2BOffLine\"],\"dltOrderList\":[{\"dltOrderId\":\"552304692EBK\",\"orderId\":\"7298335842\",\"channel\":\"EBK\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537009980000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"100\",\"checkinDate\":\"/Date(1537250857000+0800)/\",\"checkoutDate\":\"/Date(1537337244000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"fu8fP25qMld5mFYnGSWNWQ==\"},{\"dltOrderId\":\"37447388834SMS\",\"orderId\":\"7298335842\",\"channel\":\"SMS\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537009980000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"0\",\"checkinDate\":\"/Date(1537027200000+0800)/\",\"checkoutDate\":\"/Date(1537200000000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"stVP5gGYJ2aYt1d0+IagOg==\"},{\"dltOrderId\":\"39458788811SMS\",\"orderId\":\"7298353419\",\"channel\":\"SMS\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537010141000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"0\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"nj8SlVk9lDD/XCWI0Z2aIw==\"},{\"dltOrderId\":\"552268175EBK\",\"orderId\":\"7297973435\",\"channel\":\"EBK\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537007062000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"100\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"EiiC4ca5VZllU/gG3DX6sA==\"},{\"dltOrderId\":\"552286294EBK\",\"orderId\":\"7298155418\",\"channel\":\"EBK\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537008487000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"100\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"19o0n/6ANaTRxbODeihIzg==\"},{\"dltOrderId\":\"552306927EBK\",\"orderId\":\"7298353419\",\"channel\":\"EBK\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537010141000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"100\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"dNZMqT/l+E+8o5m1vGAniA==\"},{\"dltOrderId\":\"552292150EBK\",\"orderId\":\"7298208633\",\"channel\":\"EBK\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537008968000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"100\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"g9TBDe1GkeSz4qqNEvazMA==\"},{\"dltOrderId\":\"552280585EBK\",\"orderId\":\"7298084328\",\"channel\":\"EBK\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537007922000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"100\",\"checkinDate\":\"/Date(1537027200000+0800)/\",\"checkoutDate\":\"/Date(1537113600000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"Ponp9CF+JzT5w4k35egE9Q==\"},{\"dltOrderId\":\"751649B2BOffLine\",\"orderId\":\"DLT761649\",\"channel\":\"B2BOffLine\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537011771000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"2\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"mOufy6pmydbaM5W1bJtTfA==\"},{\"dltOrderId\":\"5588260Qunar\",\"orderId\":\"101660899232\",\"channel\":\"Qunar\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537011763000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"3\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"yOIPEcP+hOb9EMWoxqwsyQ==\"},{\"dltOrderId\":\"552334243EBK\",\"orderId\":\"7298625308\",\"channel\":\"EBK\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537012400000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"100\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"0jHRuSpVQwSKNdMMCuCVsg==\"},{\"dltOrderId\":\"552338588EBK\",\"orderId\":\"7298668639\",\"channel\":\"EBK\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537012757000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"100\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"O4AZJXVnG7VjrPt+IzXhCQ==\"},{\"dltOrderId\":\"552340932EBK\",\"orderId\":\"7298692019\",\"channel\":\"EBK\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537012945000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"0\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"RdDIkNR+2YCyy5FxFIln2Q==\"},{\"dltOrderId\":\"38505288851SMS\",\"orderId\":\"7298692019\",\"channel\":\"SMS\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537012945000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"1\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"NnF3p2YgU4LPPMi1YJPGZA==\"},{\"dltOrderId\":\"39444788854SMS\",\"orderId\":\"7298682934\",\"channel\":\"SMS\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537012875000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"0\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"OK8omFCaaPi6AfjWZo6X/Q==\"},{\"dltOrderId\":\"552339974EBK\",\"orderId\":\"7298682934\",\"channel\":\"EBK\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537012875000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"100\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"ibqtScBZJt1bFVriEDTIKw==\"},{\"dltOrderId\":\"5560299Qunar\",\"orderId\":\"101656673609\",\"channel\":\"Qunar\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1536652586000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"4\",\"checkinDate\":\"/Date(1536595200000+0800)/\",\"checkoutDate\":\"/Date(1536768000000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"nnWWIWZ1/Mx1CYrBx/0JOA==\"},{\"dltOrderId\":\"5588364Qunar\",\"orderId\":\"101660207689\",\"channel\":\"Qunar\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537012984000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"3\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537027200000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"CcNWPnVM2/I3sJ/jLML3zQ==\"},{\"dltOrderId\":\"5588365Qunar\",\"orderId\":\"101660919376\",\"channel\":\"Qunar\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537013001000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"3\",\"checkinDate\":\"/Date(1536940800000+0800)/\",\"checkoutDate\":\"/Date(1537113600000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"YLa8RBT8yzc/1S2/3Jfwyw==\"},{\"dltOrderId\":\"751860B2BOffLine\",\"orderId\":\"DLT761860\",\"channel\":\"B2BOffLine\",\"childChannel\":null,\"updateTime\":null,\"orderDate\":\"/Date(1537013328000+0800)/\",\"formType\":\"N\",\"orderStatus\":\"1\",\"checkinDate\":\"/Date(1537027200000+0800)/\",\"checkoutDate\":\"/Date(1537113600000+0800)/\",\"hotelId\":null,\"hotelName\":null,\"roomId\":null,\"roomName\":null,\"md5Key\":\"Gu46vdFYVjNvvFBPY0WqeQ==\"}]}";
//        GetDltOrderInfoResponse response= JSON.parseObject(respStr, GetDltOrderInfoResponse.class);

        String orderInfoStr = "{\"dltOrderId\":\"554126866EBK\",\"orderId\":\"7317180228\",\"releationOrder\":{\"lastDltOrderId\":null,\"nextDltOrderId\":null,\"lastOrderId\":null,\"nextOrderId\":null,\"lastConfirmType\":null,\"nextConfirmType\":null},\"channel\":\"EBK\",\"childChannel\":null,\"orderCurrency\":\"CNY\",\"orderPrice\":1170,\"formType\":\"N\",\"orderStatus\":\"100\",\"cancelRules\":[{\"deductType\":null,\"lastCancelTime\":\"/Date(1450972800000+0800)/\",\"value\":null}],\"paymentType\":\"预付\",\"updateTime\":\"2018-09-18 14:39\",\"orderDate\":\"/Date(1537252646000+0800)/\",\"confirmno\":\"\",\"checkinDate\":\"/Date(1537200000000+0800)/\",\"checkoutDate\":\"/Date(1537372800000+0800)/\",\"cityId\":206,\"cityName\":\"长沙\",\"cityEName\":null,\"hotelId\":\"1225295\",\"hotelName\":\"长沙美爵酒店\",\"hotelEName\":\"Grand Mercure Changsha Downtown\",\"roomId\":\"191918077\",\"roomName\":\"豪华房(特惠)<无早><双床>\",\"roomEName\":\"Deluxe Room(Special Promotion)<Room Only><Twin Bed>\",\"roomnum\":1,\"bedType\":\"双床\",\"isHoldRoom\":\"R\",\"isRiskyOrder\":null,\"contactName\":\"向金娜\",\"customerDid\":null,\"customerName\":\"向金娜\",\"customerQuantity\":1,\"additionalList\":[],\"specialMemo\":\"重要客人。 \\r\\n房费携程支付，无返佣(月结)。房价保密、请不要向客人透露。。 \",\"orderMemo\":null,\"dltIssueOrder\":{\"issueOrderId\":null,\"issueStatus\":6,\"hotelConfirmno\":null,\"issueType\":\"3\",\"purchaseCode\":\"\",\"purchaseName\":null,\"purchaseTel\":null,\"purchaseCurrency\":null,\"purchasePrice\":null,\"roomPurchasePriceList\":[{\"effectDate\":\"/Date(1537200000000+0800)/\",\"mealType\":null,\"breakfast\":null,\"breakfastNum\":0,\"currency\":null,\"price\":null},{\"effectDate\":\"/Date(1537286400000+0800)/\",\"mealType\":null,\"breakfast\":null,\"breakfastNum\":0,\"currency\":null,\"price\":null}]},\"roomPriceList\":[{\"effectDate\":\"/Date(1537200000000+0800)/\",\"mealType\":4,\"breakfast\":\"无早餐\",\"breakfastNum\":0,\"currency\":\"CNY\",\"price\":585},{\"effectDate\":\"/Date(1537286400000+0800)/\",\"mealType\":4,\"breakfast\":\"无早餐\",\"breakfastNum\":0,\"currency\":\"CNY\",\"price\":585}],\"brandId\":null}";

        String a = "{\n" +
                "        \"dltOrderId\": \"554126866EBK\",\n" +
                "        \"orderId\": \"7317180228\",\n" +
                "        \"releationOrder\": {\n" +
                "            \"lastDltOrderId\": null,\n" +
                "            \"nextDltOrderId\": null,\n" +
                "            \"lastOrderId\": null,\n" +
                "            \"nextOrderId\": null,\n" +
                "            \"lastConfirmType\": null,\n" +
                "            \"nextConfirmType\": null\n" +
                "        },\n" +
                "        \"channel\": \"EBK\",\n" +
                "        \"childChannel\": null,\n" +
                "        \"orderCurrency\": \"CNY\",\n" +
                "        \"orderPrice\": 1170,\n" +
                "        \"formType\": \"N\",\n" +
                "        \"orderStatus\": \"100\",\n" +
                "        \"cancelRules\": [\n" +
                "            {\n" +
                "                \"deductType\": null,\n" +
                "                \"lastCancelTime\": \"/Date(1450972800000+0800)/\",\n" +
                "                \"value\": null\n" +
                "            }\n" +
                "        ],\n" +
                "        \"paymentType\": \"预付\",\n" +
                "        \"updateTime\": \"2018-09-18 14:39\",\n" +
                "        \"orderDate\": \"/Date(1537252646000+0800)/\",\n" +
                "        \"confirmno\": \"\",\n" +
                "        \"checkinDate\": \"/Date(1537200000000+0800)/\",\n" +
                "        \"checkoutDate\": \"/Date(1537372800000+0800)/\",\n" +
                "        \"cityId\": 206,\n" +
                "        \"cityName\": \"长沙\",\n" +
                "        \"cityEName\": null,\n" +
                "        \"hotelId\": \"1225295\",\n" +
                "        \"hotelName\": \"长沙美爵酒店\",\n" +
                "        \"hotelEName\": \"Grand Mercure Changsha Downtown\",\n" +
                "        \"roomId\": \"191918077\",\n" +
                "        \"roomName\": \"豪华房(特惠)<无早><双床>\",\n" +
                "        \"roomEName\": \"Deluxe Room(Special Promotion)<Room Only><Twin Bed>\",\n" +
                "        \"roomnum\": 1,\n" +
                "        \"bedType\": \"双床\",\n" +
                "        \"isHoldRoom\": \"R\",\n" +
                "        \"isRiskyOrder\": null,\n" +
                "        \"contactName\": \"向金娜\",\n" +
                "        \"customerDid\": null,\n" +
                "        \"customerName\": \"向金娜\",\n" +
                "        \"customerQuantity\": 1,\n" +
                "        \"additionalList\": [],\n" +
                "        \"specialMemo\": \"重要客人。 \\r\\n房费携程支付，无返佣(月结)。房价保密、请不要向客人透露。。 \",\n" +
                "        \"orderMemo\": null,\n" +
                "        \"dltIssueOrder\": {\n" +
                "            \"issueOrderId\": null,\n" +
                "            \"issueStatus\": 6,\n" +
                "            \"hotelConfirmno\": null,\n" +
                "            \"issueType\": \"3\",\n" +
                "            \"purchaseCode\": \"\",\n" +
                "            \"purchaseName\": null,\n" +
                "            \"purchaseTel\": null,\n" +
                "            \"purchaseCurrency\": null,\n" +
                "            \"purchasePrice\": null,\n" +
                "            \"roomPurchasePriceList\": [\n" +
                "                {\n" +
                "                    \"effectDate\": \"/Date(1537200000000+0800)/\",\n" +
                "                    \"mealType\": null,\n" +
                "                    \"breakfast\": null,\n" +
                "                    \"breakfastNum\": 0,\n" +
                "                    \"currency\": null,\n" +
                "                    \"price\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"effectDate\": \"/Date(1537286400000+0800)/\",\n" +
                "                    \"mealType\": null,\n" +
                "                    \"breakfast\": null,\n" +
                "                    \"breakfastNum\": 0,\n" +
                "                    \"currency\": null,\n" +
                "                    \"price\": null\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"roomPriceList\": [\n" +
                "            {\n" +
                "                \"effectDate\": \"/Date(1537200000000+0800)/\",\n" +
                "                \"mealType\": 4,\n" +
                "                \"breakfast\": \"无早餐\",\n" +
                "                \"breakfastNum\": 0,\n" +
                "                \"currency\": \"CNY\",\n" +
                "                \"price\": 585\n" +
                "            },\n" +
                "            {\n" +
                "                \"effectDate\": \"/Date(1537286400000+0800)/\",\n" +
                "                \"mealType\": 4,\n" +
                "                \"breakfast\": \"无早餐\",\n" +
                "                \"breakfastNum\": 0,\n" +
                "                \"currency\": \"CNY\",\n" +
                "                \"price\": 585\n" +
                "            }\n" +
                "        ],\n" +
                "        \"brandId\": null\n" +
                "    }\n";
        DltOrderInfo dltOrderInfo = JSON.parseObject(a, DltOrderInfo.class);
        dltHotelOrderService.createOrder(dltOrderInfo,merchantCode);
    }

    @Test
    public void testQueryPricePlan(){
        Integer pricePlanId = 22 ;
        PricePlanPO po = dltMapRoomPOMapper.queryPricePlan(pricePlanId);
        System.out.print(po.getBreakfast());
    }

}