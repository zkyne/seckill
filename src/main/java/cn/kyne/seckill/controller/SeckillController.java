package cn.kyne.seckill.controller;

import cn.kyne.seckill.dto.Exposer;
import cn.kyne.seckill.dto.SeckillExecution;
import cn.kyne.seckill.dto.SeckillResult;
import cn.kyne.seckill.entity.Seckill;
import cn.kyne.seckill.enums.SeckillStatenum;
import cn.kyne.seckill.exception.RepeatKillException;
import cn.kyne.seckill.exception.SeckillCloseException;
import cn.kyne.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by kyne on 2017/9/5.
 */
@Controller
@RequestMapping(value = "/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    /**
     * 获取秒杀列表
     * @param model
     * @return
     */
    @GetMapping(value = "/list")
    public String list(Model model){
        //获取列表页
        List<Seckill> seckillList = seckillService.listSeckills();
        model.addAttribute("list",seckillList);
        return "list";
    }

    /**
     * 获取秒杀详情
     * @param seckillId
     * @param model
     * @return
     */
    @GetMapping(value = "/{seckillId}/detail")
    public String detail(@PathVariable("seckillId") Long seckillId,Model model){
        if(seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getSeckill(seckillId);
        if(seckill == null){
            return list(model);
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    /**
     * 获取秒杀url
     * @param seckillId
     */
    @PostMapping(value = "/{seckillId}/exposer",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<>(true, exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result = new SeckillResult<>(false, e.getMessage());
        }
        return result;
    }

    /**
     * 执行秒杀
     * @param seckillId
     * @param md5
     * @param userPhone
     * @return
     */
    @PostMapping(value = "/{seckillId}/{md5}/execution",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone",required = false) Long userPhone){

        if(userPhone == null){
            return new SeckillResult<>(false, "未注册");
        }
        try {
            //秒杀成功
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<>(true, seckillExecution);
        }catch (SeckillCloseException sc){
            //秒杀关闭
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatenum.END);
            return new SeckillResult<>(true, seckillExecution);
        }catch (RepeatKillException rk){
            //重复秒杀
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatenum.REPEAT_KILL);
            return new SeckillResult<>(true, seckillExecution);
        }catch (Exception e){
            //系统异常
            logger.error(e.getMessage(),e);
            SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillStatenum.INNER_ERROR);
            return new SeckillResult<>(true, seckillExecution);
        }
    }

    @GetMapping(value = "/time/current",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<Long> curTime(){
        Date curTime = new Date();
        return new SeckillResult<>(true, curTime.getTime());
    }
}
