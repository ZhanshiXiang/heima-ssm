package com.yunhe.ssm.controller;

import com.yunhe.ssm.domain.SysLog;
import com.yunhe.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author James
 * @create 2019-08-14 17:54
 */

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

     @Autowired
     private ISysLogService sysLogService;

    /**
     * 查看所有日志
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
       ModelAndView mv=new ModelAndView();
      List<SysLog> sysLogList= sysLogService.findAll();
      mv.addObject("sysLogs",sysLogList);
      mv.setViewName("syslog-list");
      return  mv;
    }
}
