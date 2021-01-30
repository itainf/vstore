package com.vstore.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vstore.entity.SysUser;
import com.vstore.framework.base.util.Response;
import com.vstore.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author vstore
 *
 */
@Controller
public class UserController extends BaseController {

   private static final Logger logger=LoggerFactory.getLogger(UserController.class);

   @Resource
   private SysUserService sysUserService;

    /**
     * 测试项目是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/user/queryUser")
    public  PageInfo<SysUser> testBody(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestBody(required = false) SysUser sysUser ,@RequestParam Integer pageNum
    ){
        PageHelper.startPage(pageNum,10);
        List<SysUser> list=  sysUserService.queryList(sysUser);
        return    new PageInfo<SysUser>(list);
    }


    /**
     * 测试项目是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/user/addUser")
    public Response<String> addUser(HttpServletRequest request, HttpServletResponse response, @RequestBody SysUser sysUser ){
        sysUser.setTenementId(1L);
        Response<String> responseMgr=   new Response<>();
        responseMgr.setIsSuccess("true");
       if(sysUserService.querySysUserByLoginName(sysUser.getLoginName() )!=null){
           responseMgr.setIsSuccess("false");
           responseMgr.setResult ("登录明已经存在,不能重复新增");
       }else{
           sysUserService.insertSelective(sysUser);
       }
        return responseMgr;
    }




    @ResponseBody
    @RequestMapping(value = "/user/updateUser")
    public Response  editUser(HttpServletRequest request, HttpServletResponse response, @RequestBody SysUser sysUser ){
        sysUserService.updateByPrimaryKeySelective(sysUser);
        return  ok( );
    }

    @ResponseBody
    @RequestMapping(value = "/user/queryUserById")
    public Response  queryUserById(HttpServletRequest request, HttpServletResponse response, @RequestBody SysUser sysUser ){
        SysUser user=sysUserService.selectByPrimaryKey( sysUser.getId());
        return  ok( user );
    }





}
