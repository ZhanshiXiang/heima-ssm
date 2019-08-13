package com.yunhe.ssm.controller;

import com.yunhe.ssm.domain.Role;
import com.yunhe.ssm.domain.UserInfo;
import com.yunhe.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
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


    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("authentication.principal.username=='James'")
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


    /**
     * 根据用户id查询以及用户添加角色
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userid) throws Exception {
        ModelAndView mv=new ModelAndView();
        //1、根据用户id查询用户
        UserInfo userInfo = userService.findByIdUser(userid);
        //2、根据用户id查询可以添加的角色
         List<Role> otheRoles=  userService.findOtherRoles(userid);

         mv.addObject("user",userInfo);
         mv.addObject("roleList",otheRoles);
         mv.setViewName("user-role-add");

         return  mv;
    }

    /**
     * 为用户添加角色
     * @param userId user_role 中间表中的userId字段
     * @param roleIds  数组 角色Id
     * @return
     */

   @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId, @RequestParam(name = "ids",required = true) String[] roleIds ){

       //添加角色
       userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
