<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>当当读书双11秒杀</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../../css/style.css" rel="stylesheet" type="text/css"/>
    <link href="../../css/common.css" rel="stylesheet" type="text/css"/>
    <link href="../../css/jquery.bxslider.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="../../js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../../js/common.js"></script>
    <script type="text/javascript" src="../../js/jquery.bxslider.js"></script>
    <style type="text/css">
        body {
            max-width: 800px;
        }

        .ui-content {
            padding: 0;
            margin: 0;
        }

        .bx-wrapper .bx-viewport {
            box-shadow: none;
            border: none;
            background: none;
            left: 0;
        }

        .bx-wrapper .bx-prev {
            background: url(../../images/controls.png) no-repeat 0 -32px;
            left: 0;
        }

        .bx-wrapper .bx-next {
            background: url(../../images/controls.png) no-repeat -43px -32px;
            right: 0;
        }

        #returnTop {
            position: fixed;
            bottom: 60px;
            right: 10px;
            width: 44px;
            height: 44px;
            background: url(../../images/returnTop.png) no-repeat;
            background-size: contain;
        }

        footer {
            position: fixed;
            bottom: 0;
            height: 50px;
            width: 100%;
            border-top: 1px solid #eee;
            background: #fff;
            z-index: 1;
        }

        footer a {
            display: block;
            width: 80%;
            margin: 10px auto;
            border-radius: 10px;
            font-size: 1.5em;
            text-align: center;
            color: #ff5000;
            background: #eee url(../../images/time.png) 28% 1px no-repeat;
            background-size: 28px;
            padding-left: 40px;
        }
    </style>

</head>
<body style="margin-left: 400px">

<div data-role="page" id="mypage" >
    <div role="main" class="ui-content">
        <ul class="sliderlist">
            <li>
                <div class="miaosha_list">
                    <br/>
                    <br/>
                    <%--<div class="img">
                        <div>
                            <img src="images/25125042-1_u_1.jpg" alt="">
                            <div class="dw"><img src="images/miaosha_position.png" alt=""></div>
                        </div>
                    </div>--%>
                    <!--页面必要代码,img标签上请务必带上图片真实尺寸px-->
                    <div id="showbox">
                        <img src="../../images/25125042-1_u_1.jpg" alt="" width="800" height="800"/>
                        <img src="../../images/25125042-2_u_1.jpg" alt="" width="800" height="800"/>
                        <img src="../../images/25125042-3_u_1.jpg" alt="" width="800" height="800"/>
                    </div><!--展示图片盒子-->
                    <div id="showsum"></div><!--展示图片里边-->
                    <p class="showpage">
                        <a href="javascript:void(0);" id="showlast"> < </a>
                        <a href="javascript:void(0);" id="shownext"> > </a>
                    </p>

                    <div class="textbox">
                        <div class="text">
                            <h3>城南旧事（名师导读，随书赠送《名著考点与创新试题精华》）</h3>
                        </div>
                    </div>
                    <div class="money">
                        <div class="left">
                            <h2>￥9.90<span>￥36.00</span><em>限时购</em></h2>
                        </div>
                        <div class="right">
                            <ul class="time">
                                <div class="time-text">距离开抢时间：</div>
                                <li class="hours"></li>
                                <li class="none">:</li>
                                <li class="second"></li>
                                <li class="none">:</li>
                                <li class="miao"></li>
                                <div class="clear"></div>
                            </ul>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="tab">
                        <p class="tab-hd"><span class="on">图文详情</span><span>评论(1226)</span></p>
                        <div class="tab-nr">
                            <div class="tab-innr show">
                                <img src="../../images/99999990001712592_1_o.jpg" alt="">
                            </div>
                            <div class="tab-innr">
                                这里显示评论列表
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>

<div id="returnTop"></div>
<footer><a href="">抢购即将开始</a></footer>
<script type="text/javascript">
    // 倒计时
    var startTime = new Date();
    startTime.setFullYear(2016, 1, 1);// 抢购开始年月日
    startTime.setHours(23);
    startTime.setMinutes(59);
    startTime.setSeconds(59);
    startTime.setMilliseconds(999);
    var EndTime = startTime.getTime();
    function GetRTime() {
        var NowTime = new Date();
        var nMS = EndTime - NowTime.getTime();
        var nD = Math.floor(nMS / (1000 * 60 * 60 * 24));
        var nH = Math.floor(nMS / (1000 * 60 * 60)) % 24;
        var nM = Math.floor(nMS / (1000 * 60)) % 60;
        var nS = Math.floor(nMS / 1000) % 60;
        if (nMS < 0) {
            $("#dao").hide();
            $(".banner_box .time").show();
        } else {
            $(".banner_box .time").show();
            $("#daoend").hide();
            $("#RemainD").text(nD);
            $(".hours").text(nH);
            $(".second").text(nM);
            $(".miao").text(nS);
        }
        // 倒计时不足10补0
        if (nH < 10) {
            $(".hours").text('0' + nH);
        } else {
            $(".hours").text(nH);
        }
        if (nM < 10) {
            $(".second").text('0' + nM);
        } else {
            $(".second").text(nM);
        }
        if (nS < 10) {
            $(".miao").text('0' + nS);
        } else {
            $(".miao").text(nS);
        }
    }

    $(document).ready(function () {
        // 秒杀商品多图滚动
        //$('.img').bxSlider({auto: true, pager:false});

        var timer_rt = window.setInterval("GetRTime()", 1000);
        // tab切换
        $('.tab-hd span').bind('click', function (event) {
            $(this).addClass('on').siblings().removeClass('on');
            $('.tab-innr').eq($(this).index()).addClass('show').siblings().removeClass('show');
        });
        // 返回顶部
        $('body').scrollTop(0);
        $("#returnTop").bind('click', function () {
            var speed = 500;
            $('body,html').animate({scrollTop: 0}, speed);
            return false;
        });
    });

    $(document).ready(function(){
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

</body>
</html>
