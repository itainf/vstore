package com.vstore.service;

import com.vstore.entity.SysUser;
import java.util.List;

public interface SysUserService     {

      /**
       * 查询一个结果集
       * @param sysUser SysUser对象
       * @return list<SysUser>
       */
      List<SysUser> queryList(SysUser sysUser);

      /**
       * 新增一个对象
       * @param sysUser SysUser对象
       * @return int
       */
      int insertSelective(SysUser sysUser);

      /**
       * 通过主建更新一个对象，主键不能为空
       * @param sysUser SysUser对象
       * @return int
       */
      int updateByPrimaryKeySelective(SysUser sysUser );

      /**
       * 通过主键id查询一个对象
       * @param id 主键
       * @return SysUser
       */
      SysUser selectByPrimaryKey(Long id);

      /**
       * 通过主键删除对象
       * @param id 主键
       * @return int
       */
      int deleteByPrimaryKey(Long id);

      SysUser querySysUserByLoginName(String loginName);


}
