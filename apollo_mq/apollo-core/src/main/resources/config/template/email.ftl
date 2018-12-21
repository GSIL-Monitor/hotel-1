<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"></meta>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
    <meta http-equiv="X-UA-Compatible" content="ie=edge,chrome=1"></meta>
</head>
<style type="text/css">
    * {
        padding: 0;
        margin: 0;
    }
    .print {
        padding: 20px;
        width: 880px;
        margin: 0 auto;
        font-family: 'Microsoft YaHei';
        color: #565a64;
        background: #fff;
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.26);
        margin-bottom: 20px;
        padding-bottom: 40px;
        position: relative;
        display: block !important;

    }
    .print .p-head {}
    .print .p-head .p-head-notice {
        height: 42px;
    }
    .print .p-head .p-head-notice .p-head-notice-left {
        float: left;
    }
    .print .p-head .p-head-notice .p-head-notice-left p {
        line-height: 21px;
        height: 21px;
        font-size: 12px;

    }
    .print .p-head .p-head-notice .p-head-notice-right {
        float: right;
    }
    .print .p-head .p-head-notice .p-head-notice-right a {
        float: right;
        line-height: 30px;
        color: #3a589a;
        margin-right: 30px;
        cursor: pointer;
    }
    .print .p-head .p-head-notice .p-head-notice-right a:hover {
        text-decoration: underline;
    }
    .print .p-head .p-head-notice .p-head-notice-right span {
        float: right;
        display: inline-block;
        width: 60px;
        height: 30px;
        line-height: 30px;
        background-color: #3a589a;
        color: #fff;
        text-align: center;
        border-radius: 4px;
        cursor: pointer;
    }
    .p-head-title {
        height: 44px;
        font-size: 24px;
        text-align: center;
        padding-top: 20px;
    }
    .print .p-body {
        font-size: 12px;

    }
    .print .p-body .content-title {
        font-weight: 700;
        font-size: 14px;
        margin-top: 20px;
        text-align: left;
    }
    .content-total {
        font-weight: 700;
        font-size: 14px;
        margin-top: 10px;
        text-align: right;
    }
    .print .p-body dl {
        line-height: 30px;
        padding-left: 20px;
    }
    .print .p-body .orderfill-product-list dl {
        padding-left: 130px;
        line-height: 0;
    }
    .print .p-body .subsection-style {
        padding-left: 20px;
    }
    .print .p-body .orderfill-product-list dl dt {
        width: 130px;
    }
    .print .p-body .orderfill-product-list dl dd {
        width: 620px;
        padding-left: 0px;
    }
    .print .p-body dt {
        width: 90px;
        display: inline-block;
        color: #9b9b9b;
    }
    .print .p-body dd {
        width: 700px;
        display: inline-block;
        padding-left: 20px;
    }
    .print .p-body .content-table {}
    .print .p-body dl .specialNotice {
        font-size: 10px;
        color: #9b9b9b;
    }
    .print .p-body .content-table tr th {
        border: 1px solid #DEE0E5;
        background-color: #F5F5F5;
    }
    .print .p-body .content-table tr td {
        text-align: center;
        border: 1px solid #DEE0E5;
    }
    .print .p-body .doubleTable {
        margin-top: 20px;

    }
    .print .p-body .doubleTable dd {
        float: left;
        margin-left: 0;
        width: 47%;
        /*padding-left: 0;*/
        padding: 0 10px 0 0;
    }
    .print .p-body .doubleTable dd p {
        font-weight: 700;
        font-size: 14px;
    }
    .print .p-body .doubleTable dd:last-child {
        float: right;
        margin-left: 0;
        padding: 0;
    }
    .page .page-section {
        padding: 15px 40px;
        position: relative;
        font-size: 13px;
    }
    .page .page-section:not(:last-child) {
        border-bottom: 1px solid #DEE0E5;
    }
	
	.order-time-section {
    position: absolute;
    left:30px;
    top:30px;
    font-size:14px;
    color: #777;
    font-weight: normal;
    }
    .seal-img-section {
        position: absolute;
        right:20px;
        bottom: 0px;
        width:180px;
        height:180px;
    }
    .couple dt {
        position: relative;
        float: left;
        width: 110px;
        color: #888;
        margin-right: 10px;
        flex-shrink: 0;
        padding: 5px 0;
        line-height: 20px;
    }
    .font-xxl {
        font-size: 20px;
        line-height: 20px;
        padding-bottom: 10px;
        width: 580px;
        line-height: 26px;
    }
    .checkbox input , .radio input  {

        width: 18px;
        height: 18px;
        position: relative;
        top:5px;

    }
    .checkbox {
        position: relative;
        top: -3px;
        margin-right: 15px;
    }
    .couple dd {
        position: relative;
        float: left;
        line-height: 20px;
        width: 618px;
        padding: 5px 0;

    }
    .color-warning {
        color: #ff6000 !important;
    }
    .font-bold {
        font-weight: bold;
    }
    .table-list {
        width: 100%;
        position: relative;
    }
    .uheader {
        font-weight: bold;
        min-height: auto !important;
    }
    .table-list .row {
        min-height: 36px;
        position: relative;
        width: 100%;
        color: #3f3f3f;
        font-size: 13px;
        font-weight: bold;
    }
    .table-list .row > li {
        word-break: break-all;
        padding: 8px;
        float: left;
        position: relative;
    }
    .print .normal-table ul.row > li:nth-of-type(1) {
        width: 12%;
    }
    .couple:after,.row:after,.uheader:after {
        content: ".";
        display: block;
        height: 0;
        overflow: hidden;
        visibility: hidden;
        clear: both;
        width: 100%
    }
	
.normal-table table th,.normal-table table td {
    padding: 12px 8px;
    text-align: left;
    
        vertical-align: top;
}
.normal-table table th {

}
.normal-table table tr:nth-child(even) td {
    background-color: #f1f1f1;
}
.table-body .line-stripe {
    text-align: right;
    padding: 10px 20px;
    font-size: 14px;
    border-top: 1px solid #DEE0E5
}
    .table-body .row {
        font-weight: normal;
    }
    i {font-style:normal}
</style>

<body>
    <div class="print page">
    <div class="p-head">
        <h3 class="p-head-title">${supplyType}</h3>
		<h3 class="order-time-section">发单日期：${sendTime}</h3>
    </div>
    <div class="page-section">
        <dl class="couple couple-start"><dt class=""><span>供货单号<!----></span></dt>
            <dd class="" style="width: 618px;">
                ${supplyOrderCode}
            </dd>
        </dl>
        <dl class="couple"><dt class=""><span>接收方<!----></span></dt>
            <dd class="" style="width: 618px;">
                ${supplyName}
            </dd>
        </dl>
        <dl class="couple"><dt class=""><span>发送方<!----></span></dt>
            <dd class="" style="width: 618px;">
                ${company}
            </dd>
        </dl>
    </div>
    <div class="page-section">
        <dl class="couple"><dt class=""><span>酒店名<!----></span></dt>
            <dd class="" style="width: 618px;"><span class="font-bold font-xl">${hotelName}</span></dd>
        </dl>
        <dl class="couple"><dt class=""><span>酒店电话<!----></span></dt>
            <dd class="" style="width: 618px;"><span>${hotelPhone}</span></dd>
        </dl>
        <dl class="couple"><dt class=""><span>客人<!----></span></dt>
            <dd class="" style="width: 618px;">
                ${guest}
            </dd>
        </dl>
        <dl class="couple couple-start"><dt class=""><span>备注<!----></span></dt>
            <dd class="" style="width: 618px;">
                ${note}
            </dd>
        </dl>
    </div>
    <div class="page-section">
        <div class="table-list normal-table">
            <table cellpadding="0" cellspacing="0" width="100%">
                    <colgroup>
                        <col width="12%">
                        <col width="18%">
                        <col width="15%">
                        <col width="15%">
                        <col width="8%">
                        <col width="8%">
                        <col width="12%">
                        <col>
                    </colgroup>
                    <tr>
                    <th>类型</th>
                    <th>早餐+附加项</th>
                    <th>入住日期</th>
                    <th>离店日期</th>
                    <th>间数</th>
                    <th>天数</th>
                    <th>单价(${currency})</th>
                    <th>总价(${currency})</th>
                </tr>
                <#list supplyProductList as p>
                    <#list p.dateSegmentList as d>
                    <tr class="row">
                        <td>${p.roomTypeName}</td>
                        <td class="bold">
                            ${p.rateplanName}
                        </td>
                        <td>${d.checkinDate}</td>
                        <td>${d.checkoutDate}</td>
                        <td>${d.roomNum}间</td>
                        <td>${d.days}天</td>
                        <td>${d.basePrice}</td>
                        <td>${d.basePrice*d.roomNum*d.days}</td>
                    </tr>
                    </#list>
                </#list>
                <#list derateList as d>
                    <tr class="row">
                        <td>减免政策</td>
                        <td>${d.name}</td>
                        <td>--</td>
                        <td>--</td>
                        <td>${d.roomNumSum}间夜</td>
                        <td>--</td>
                        <td>--</td>
                        <td>${d.basePriceSum}</td>
                    </tr>
                </#list>
                <#list supplyAdditionChargeList as a>
                    <tr class="row">
                        <td>${(a.additionType = 1) ? string("附加项","减免政策")}</td>
                        <td>${a.name}</td>
                        <td>--</td>
                        <td>--</td>
                        <td>${a.num}份</td>
                        <td>${a.days}天</td>
                        <td>${a.basePrice}</td>
                        <td>${a.basePriceSum}</td>
                    </tr>
                </#list>
            </table>
            <div class="table-body">
                    <p class="line-stripe">合计 :  <span class="color-warning">${currency}${orderSum}</span></p>
					
            </div>
        </div>
    </div>
    <div class="page-section">
        <div class="font-xxl title">房费我司支付，杂费客人自理；客人如需取消或修改请告知客人与我司联系，酒店予客人的任何承诺无效！</div>
        <dl class="couple"><dt class=""><span>供应商回复<!----></span></dt>
            <dd class="" style="width: 618px;">
                <label class="checkbox"><!----><input type="checkbox"></input> <i class="icon-hook"></i> 接受</label>
                <label class="checkbox"><!----><input type="checkbox"></input> <i class="icon-hook"></i> 拒绝</label>
            </dd>
        </dl>
        <dl class="couple"><dt class=""><span>订单确认号<!----></span></dt>
            <dd class="" style="width: 618px;"></dd>
        </dl>
        <dl class="couple"><dt class=""><span>确认人签名<!----></span></dt>
            <dd class="" style="width: 618px;"></dd>
        </dl>
        <dl class="couple"><dt class=""><span>确认日期<!----></span></dt>
            <dd class="" style="width: 618px;"></dd>
        </dl>
            <img src="${seal}" class="seal-img-section">
    </div>
</div>
</body>
</html>