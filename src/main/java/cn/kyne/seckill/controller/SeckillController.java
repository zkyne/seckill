package cn.kyne.seckill.controller;

import cn.kyne.seckill.entity.Seckill;
import cn.kyne.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by kyne on 2017/9/5.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @GetMapping("/list")
    public String list(Model model){
        //获取列表页
        List<Seckill> seckillList = seckillService.listSeckills();
        model.addAttribute("list",seckillList);
        return "list";
    }

    //@GetMapping("/{seckill}")

}
