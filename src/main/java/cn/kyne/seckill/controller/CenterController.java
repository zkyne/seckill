package cn.kyne.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 入口
 * Created by zhangkun01 on 2017/9/6.
 */
@Controller
public class CenterController {

    @GetMapping(value = "")
    public String index(){
        return "index";
    }
}
