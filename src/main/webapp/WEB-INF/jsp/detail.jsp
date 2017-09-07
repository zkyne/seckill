<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../css/common.css" rel="stylesheet" type="text/css"/>
    <link href="../../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title>秒杀商品详情</title>
</head>
<body>
    <div class="container">
        <div class="panel">
            <div class="panel-heading text-center">
                <h2>${seckill.name}</h2>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-5" style="border:1px solid darkorange;padding-left: 35px;padding-top: 5px">
                        <div id="showbox">
                            <c:forEach items="${seckill.image}" var="image">
                                <img src="../../images/${image}" alt="" width="600" height="600"/>
                            </c:forEach>
                        </div><!--展示图片盒子-->
                        <div id="showsum"></div><!--展示图片里边-->
                        <p class="showpage">
                            <a href="javascript:void(0);" id="showlast"> < </a>
                            <a href="javascript:void(0);" id="shownext"> > </a>
                        </p>
                    </div>
                    <div class="col-xs-5 col-lg-offset-1" style="padding-top: 50px;">
                        <div class="row">
                            <h3>${seckill.description}</h3>
                        </div>
                        <div class="row" style="padding-top: 30px">
                            <span class="glyphicon glyphicon-yen">
                            </span>
                            <span style="color: red;font-size: 20px">
                                <fmt:formatNumber value="${seckill.seckillPrice}" pattern="0.00"/>
                            </span>
                        </div>
                        <div class="row" style="padding-top: 30px">
                            原价:<span class="glyphicon glyphicon-yen"></span>
                            <span style="text-decoration:line-through;">
                                <fmt:formatNumber value="${seckill.originalPrice}" pattern="0.00"/>
                            </span>
                        </div>
                    </div>
                </div>
               <div class="row">
                   <div class="col-xs-6 col-xs-offset-6 text-right">
                       <h4 class="text-danger" style="color: red">
                           <span class="glyphicon glyphicon-time"></span>
                           <span class="glyphicon" id="seckill-box"></span>
                       </h4>
                   </div>
               </div>
            </div>
            <div class="panel-footer">
                <div class="row">
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active"><a href="javascript:void(0);" id="seckillDescA">商品详情</a></li>
                        <li role="presentation"><a href="javascript:void(0);" id="seckillCommentA">商品评论</a></li>
                    </ul>
                </div>
                <div class="row text-center" id="seckillDesc">
                    <img src="../../images/${seckill.detailImg}" alt=""/>
                </div>
                <div class="row" hidden="hidden" id="seckillComment">
                    <span>暂无评论</span>
                </div>
            </div>
        </div>

    </div>
    <%--modal start--%>
    <div class="modal fade" id="killPhoneModal" style="padding-top: 150px">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-phone"></span>秒杀电话
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="killPhone" id="killPhone" placeholder="请输入手机号o(∩_∩)o" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <%--验证信息--%>
                    <span id="killPhoneMessage" class="glyphicon"></span>
                    <button type="button" id="killPhoneBtn" class="btn btn-success">
                        <span class="glyphicon glyphicon-phone"></span>Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
    <%--modal end--%>
</body>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../js/common.js"></script>
<script type="text/javascript" src="../../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../../js/jquery.cookie.min.js"></script>
<script type="text/javascript" src="../../js/jquery.countdown.min.js"></script>
<script type="text/javascript" src="../../js/seckill.js"></script>
<script type="text/javascript">
    $(function () {
        seckill.detail._init({
            seckillId:${seckill.seckillId},
            startTime:${seckill.startTime.time},//获取毫秒
            endTime:${seckill.endTime.time}
        });

        $('#seckillCommentA').click(function () {
            $("#seckillDescA").parent().removeClass('active');
            $(this).parent().addClass('active');
            $('#seckillDesc').hide();
            $('#seckillComment').show();
        });
        $('#seckillDescA').click(function () {
            $('#seckillCommentA').parent().removeClass('active');
            $(this).parent().addClass('active');
            $('#seckillDesc').show();
            $('#seckillComment').hide();
        });
        //图片放大镜
        var showproduct = {
            "boxid":"showbox",
            "sumid":"showsum",
            "boxw":400,//宽度,该版本中请把宽高填写成一样
            "boxh":400,//高度,该版本中请把宽高填写成一样
            "sumw":60,//列表每个宽度,该版本中请把宽高填写成一样
            "sumh":60,//列表每个高度,该版本中请把宽高填写成一样
            "sumi":7,//列表间隔
            "sums":5,//列表显示个数
            "sumsel":"sel",
            "sumborder":1,//列表边框，没有边框填写0，边框在css中修改
            "lastid":"showlast",
            "nextid":"shownext"
        };//参数定义
        $.ljsGlasses.pcGlasses(showproduct);//方法调用，务必在加载完后执行
    });
</script>
</html>
