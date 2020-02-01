package com.yunhe.ssm.controller;

import com.yunhe.ssm.domain.Permission;
import com.yunhe.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author James
 * @create 2019-08-11 1:14
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    /**
     * 查询所有的权限
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return  mv;
    }


    /**
     * 添加角色
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public  String save(Permission permission)throws Exception{
        permissionService.save(permission);
        return  "redirect:findAll.do";
    }

}
