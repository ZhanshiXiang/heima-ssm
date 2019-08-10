package com.yunhe.ssm.controller;

import com.yunhe.ssm.domain.UserInfo;
import com.yunhe.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author James
 * @create 2019-08-09 11:55
 */
@Controller
@RequestMapping("/user")
public class UserController {

     @Autowired
   private IUserService userService;

    /**
     * 查询所有的用户
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView  findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 新增用户
     * @param userInfo
     * @return 返回重定向查询所有用户
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String  saveUser(UserInfo userInfo) throws Exception{

        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 根据用户Id查询用户的详情包括角色、权限信息
     * @param id
     * @return
     */
    @RequestMapping("/findByIdUser.do")
    public ModelAndView findByIdUser(String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo=userService.findByIdUser(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
}
