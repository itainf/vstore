package com.vstore.service.impl;


import com.vstore.entity.SysUser;
import com.vstore.entity.SysUserExample;
import com.vstore.mapper.SysUserMapper;
import com.vstore.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl   implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> queryList(SysUser sysUser) {
        if(sysUser!=null){
            SysUserExample example=new SysUserExample();
            if(StringUtils.isNotBlank(sysUser.getUserName())){
                example.createCriteria().andUserNameEqualTo(sysUser.getUserName());
            }
            return  (List<SysUser>)sysUserMapper.selectByExample(example);
        }else {
            return  (List<SysUser>)sysUserMapper.selectByExample(null );
        }
    }


    @Override
   public SysUser querySysUserByLoginName(String loginName){
       SysUserExample example=new SysUserExample();
       example.createCriteria().andLoginNameEqualTo(loginName);
       List<SysUser> list=    sysUserMapper.selectByExample(example);
       if(list!=null && list.size()>0){
           return list.get(0);
       }else{
           return null;
       }
    }

    @Override
    public int insertSelective(SysUser record) {
        return sysUserMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return sysUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public SysUser selectByPrimaryKey(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }


}
