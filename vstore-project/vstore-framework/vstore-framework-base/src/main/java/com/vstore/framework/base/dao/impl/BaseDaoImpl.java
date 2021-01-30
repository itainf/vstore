package com.vstore.framework.base.dao.impl;

import com.vstore.framework.base.dao.BaseDao;
import com.vstore.framework.base.paging.Page;
import com.vstore.framework.base.util.MapAttrHandler;
import com.vstore.framework.base.util.MapUtils;
import com.vstore.framework.base.util.ResultAttrHandler;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.BatchExecutor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Repository
public class BaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {

        private final int BATCH_SIZE   = 50;

        @Override
        @Autowired
        public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
            super.setSqlSessionFactory(sqlSessionFactory);
        }

        @Override
        public int insert(String statement, Object entity) {
            return getSqlSession().insert(statement, entity);
        }

        @Override
        public <T> T insert(T entity) {
            String mapperKey = entity.getClass().getName();
            mapperKey = StringUtils.replace(mapperKey, ".entity.", ".dao.");
            mapperKey = mapperKey + "Mapper."+BaseDao.INSERT;
            getSqlSession().insert(mapperKey, entity);
            return entity;
        }

        @Override
        public <T> T insertSelective(T entity) {
            String mapperKey = entity.getClass().getName();
            mapperKey = StringUtils.replace(mapperKey, ".entity.", ".dao.");
            mapperKey = mapperKey + "Mapper."+BaseDao.INSERT_SELECTIVE;

            getSqlSession().insert(mapperKey, entity);
            return entity;
        }

        @Override
        public int insertAll(String statement, Collection collection) {
            return doBatch(statement,collection);
        }

        @Override
        public int deleteByPrimaryKey(Class entity, Object key) {
            String mapperKey = entity.getName();
            mapperKey = StringUtils.replace(mapperKey, ".entity.", ".dao.");
            mapperKey = mapperKey + "Mapper."+BaseDao.DELETE_BY_PRIMARY_KEY;
            return  getSqlSession().delete(mapperKey, key);
        }

        @Override
        public int deleteByExample(Object example) {
            String mapperKey = example.getClass().getName();
            mapperKey = StringUtils.substringBeforeLast(mapperKey, "Example");
            mapperKey = StringUtils.replace(mapperKey, ".entity.", ".dao.");
            mapperKey = mapperKey + "Mapper."+BaseDao.DELETE_BY_EXAMPLE;

            return  getSqlSession().delete(mapperKey, example);
        }

        @Override
        public int delete(String statement, Object parameter) {
            return getSqlSession().delete(statement, parameter);
        }

        @Override
        public int deleteAll(String statement, Collection collection) {

            return   doBatch(statement,collection);
        }

        @Override
        public int updateByPrimaryKey(Object record) {
            String mapperKey = record.getClass().getName();
            mapperKey = StringUtils.replace(mapperKey, ".entity.", ".dao.");
            mapperKey = mapperKey + "Mapper."+BaseDao.UPDATE_BY_PRIMARY_KEY;
            return  getSqlSession().update(mapperKey, record);
        }

        @Override
        public int updateByPrimaryKeySelective(Object record) {
            String mapperKey = record.getClass().getName();
            mapperKey = StringUtils.replace(mapperKey, ".entity.", ".dao.");
            mapperKey = mapperKey + "Mapper."+BaseDao.UPDATE_BY_PRIMARY_KEY_SELECTIVE;
            return getSqlSession().update(mapperKey, record);
        }

        @Override
        public int updateByExample(Object record, Object example) {
            String mapperKey = record.getClass().getName();
            mapperKey = StringUtils.replace(mapperKey, ".entity.", ".dao.");
            mapperKey = mapperKey + "Mapper."+BaseDao.UPDATE_BY_EXAMPLE;

            return  getSqlSession().update(mapperKey, MapUtils.toMap("record", record, "example", example));

        }

        @Override
        public int updateByExampleSelective(Object record, Object example) {
            String mapperKey = record.getClass().getName();
            mapperKey = StringUtils.replace(mapperKey, ".entity.", ".dao.");
            mapperKey = mapperKey + "Mapper."+BaseDao.UPDATE_BY_EXAMPLE_SELECTIVE;

            return getSqlSession().update(mapperKey, MapUtils.toMap("record", record, "example", example));

        }

        @Override
        public int update(String statement, Object parameter) {
            return getSqlSession().update(statement, parameter);
        }

        @Override
        public int updateAll(String statement, Collection collection) {
            return doBatch(statement,collection);

        }

        @Override
        public <T> T selectByPrimaryKey(Class entity, Object key) {
            String mapperKey = entity.getName();
            mapperKey = StringUtils.replace(mapperKey, ".entity.", ".dao.");
            mapperKey = mapperKey + "Mapper."+BaseDao.SELECT_BY_PRIMARY_KEY;
            return  selectOne(mapperKey, key);
        }

        @Override
        public <T> List<T> selectByExample(Object example) {
            String mapperKey = example.getClass().getName();
            mapperKey = StringUtils.substringBeforeLast(mapperKey, "Example");
            mapperKey = StringUtils.replace(mapperKey, ".entity.", ".dao.");
            mapperKey = mapperKey + "Mapper."+BaseDao.SELECT_BY_EXAMPLE;

            return getSqlSession().selectList(mapperKey, example);
        }

        @Override
        public <T> List<T> selectList(String statement, Object parameter) {
            return getSqlSession().selectList(statement, parameter);
        }

        @Override
        public  List selectAttrList(String statement, Object parameter) {
            ResultAttrHandler attrHandler = new ResultAttrHandler();
            getSqlSession().select(statement, parameter, attrHandler);
            return attrHandler.getResultList();
        }

        @Override
        public <T> T  selectOne(String statement, Object parameter) {
            List<T> list = selectList(statement, parameter);
            if(CollectionUtils.isNotEmpty(list)){
                return  list.iterator().next();
            }
            return null;
        }

        @Override
        public  <T> T selectAttrOne(String statement, Object parameter) {
            List<T> list = selectAttrList(statement, parameter);
            if(CollectionUtils.isNotEmpty(list)){
                return list.iterator().next();
            }
            return null;
        }

        @Override
        public <K,V> Map<K, V> selectMap(String statement, Object parameter, String key) {
            return getSqlSession().selectMap(statement, parameter, key);
        }

        @Override
        public <K,V> Map<K, V> selectAttrMap(String statement, Object parameter, String key) {
            MapAttrHandler<K, V> mapAttrHandler = new MapAttrHandler<>();
            Map<K,V> map = getSqlSession().selectMap(statement, parameter, MapUtils.setAttrKey(key));
            return mapAttrHandler.getResultMap(map);
        }

        @Override
        public   <T> Page<T>  selectByPage(String statement, Object parameter, Integer pageNo, Integer limit) {
            Page<T> pageInfo = new Page<>(pageNo, limit);
            com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageNo, limit);
            List<T> resultList = getSqlSession().selectList(statement, parameter, pageInfo);
            pageInfo.setRecords(resultList);
            if(CollectionUtils.isNotEmpty(resultList)){
                pageInfo.setTotal(new Long(page.getTotal()).intValue());
            }else{
                pageInfo.setTotal(0);
            }
            return pageInfo;
        }

        @Override
        public  Page<Object> selectByAttrPage(String statement, Object parameter, Integer pageNo, Integer limit) {
            ResultAttrHandler attrHandler = new ResultAttrHandler();
            com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageNo, limit);
            Page<Object> pageInfo = new Page<>(pageNo, limit);
            List<Object> resultList = getSqlSession().selectList(statement, parameter, pageInfo);
            pageInfo.setRecords(  attrHandler.getResultList(resultList));
            if(CollectionUtils.isNotEmpty(attrHandler.getResultList())){
                pageInfo.setTotal(new Long(page.getTotal()).intValue());
            }else{
                pageInfo.setTotal(0);
            }
            return pageInfo;
        }

        private int doBatch(String statement, Collection collection) {
            try{
                Configuration conn = getSqlSession().getConfiguration();
                ManagedTransactionFactory managedTransactionFactory = new ManagedTransactionFactory();
                BatchExecutor batchExecutor = new BatchExecutor(conn, managedTransactionFactory.newTransaction(getSqlSession().getConnection()));
                int i = 0;
                List<BatchResult> lrs = new ArrayList<BatchResult>();
                for (Object entity : collection) {
                    batchExecutor.doUpdate(conn.getMappedStatement(statement), entity);
                    if (i++ > 0 && i % BATCH_SIZE == 0) {
                        List<BatchResult> b1 = batchExecutor.doFlushStatements(false);
                        lrs.addAll(b1);
                    }
                }
                List<BatchResult> b2 = batchExecutor.doFlushStatements(false);
                lrs.addAll(b2);

                int counts = 0;
                if (CollectionUtils.isNotEmpty(lrs)) {
                    for (BatchResult br : lrs) {
                        for (int c : br.getUpdateCounts()) {
                            counts += c;
                        }
                    }
                }
                return counts;
            }catch(DataAccessException e){
                logger.error("sqlId is : " + statement);
                logger.error("jdbc.error.code.common.update", e);
                throw e;
            }catch(Exception de){
                logger.error("sqlId is : " + statement);
                logger.error("jdbc.error.code.common.update", de);
                throw new RuntimeException(de);
            }
        }
}
