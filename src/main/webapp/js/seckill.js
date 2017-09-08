//存放主要的交互逻辑js代码
//javascript模块化
var seckill = {
    //封装秒杀相关ajax的url地址
    URL: {
        _current:function () {
            return '/seckill/time/current';
        },
        _exposer:function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        //程序秒杀
        _execution:function (seckillId,md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        },
        //通过存储过程执行秒杀
        _executionByProcedure:function (seckillId,md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/exeProcedure'
        }
    },
    _handleSeckillKill:function (seckillId,node) {
        //处理秒杀逻辑
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.ajax({
            type:'post',
            url:seckill.URL._exposer(seckillId),
            data:{},
            dataType:'json',
            success:function (result) {
                if(result && result['success']){
                    var exposer = result['data'];
                    if(exposer['exposed']){
                        //开启秒杀
                        //获取秒杀地址
                        var md5 = exposer['md5'];
                        //var killUrl = seckill.URL._execution(seckillId,md5);
                        var killUrl = seckill.URL._executionByProcedure(seckillId,md5);
                        //绑定一次点击事件
                        $('#killBtn').one('click',function () {
                            //执行秒杀请求
                            //1.先禁用按钮
                            $('#killBtn').prop('disabled','disabled');
                            //2.发送请求
                            $.ajax({
                                type:'post',
                                url:killUrl,
                                data:{},
                                dataType:'json',
                                success:function (result) {
                                    if(result && result['success']){
                                        var killResult = result['data'];
                                        var state = killResult['state'];
                                        var stateInfo = killResult['stateInfo'];
                                        //3.显示秒杀结果
                                        node.html('<span class="label label-success" style="font-size: 22px">' + stateInfo + '</span>');
                                    }
                                }
                            });
                        });
                        node.show(300);
                    }else{
                        //未开启秒杀
                        var curTime = exposer['curTime'];
                        var startTime = exposer['startTime'];
                        var endTime = exposer['endTime'];
                        //重新计算计时逻辑
                        seckill._countdown(seckillId,curTime,startTime,endTime)
                    }
                }else{
                    console.log('result:' + result);
                }
            }
        });
    },
    _countdown:function (seckillId,curTime,startTime,endTime) {
        var seckillBox = $('#seckill-box');
        if(curTime > endTime){
            //秒杀结束
            seckillBox.html('秒杀结束!')
        }else if(curTime < startTime){
            var killTime = new Date(startTime + 1000);
            //秒杀未开始
            seckillBox.countdown(killTime,function (event) {
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown',function () {//倒计时结束后回调事件
                //获取秒杀地址,控制实现逻辑,执行秒杀
                seckill._handleSeckillKill(seckillId,seckillBox);
            })
        }else{
            //秒杀开始
            seckill._handleSeckillKill(seckillId,seckillBox);
        }
    },
    _validatePhone:function (phone) {
       return !!(phone && phone.length === 11 && !isNaN(phone));
    },

    detail: {
        //详情页初始化
        _init: function (params) {
            //手机验证和登录,计时交互
            //规划我们的交互流程
            var killPhone = $.cookie('killPhone');
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            //验证手机号
            if(!seckill._validatePhone(killPhone)){
                //绑定手机号
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show:true,//显示模态框
                    backdrop:'static',//禁止位置关闭
                    keyboard:false//关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhone').val();
                    if(seckill._validatePhone(inputPhone)){
                        //电话写入cookie
                        $.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'});
                        //刷新页面
                        window.location.reload();
                    }else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">非法手机号!</label>').show(300);
                    }
                });
            }
            //已经登录
            //计时交互
            $.ajax({
                type:'get',
                url:seckill.URL._current(),
                dataType:'json',
                data:{},
                success:function(result){
                    if(result && result['success']){
                        var curTime = result['data'];
                        //时间判断
                        seckill._countdown(seckillId,curTime,startTime,endTime);
                    }else{
                        console.log('result:' + result)
                    }
                }
            });
        }
    }
};