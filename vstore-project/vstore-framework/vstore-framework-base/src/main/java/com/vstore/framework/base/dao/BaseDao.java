package com.vstore.framework.base.dao;

import com.vstore.framework.base.paging.Page;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseDao {

    public static final String COUNT_BY_EXAMPLE                = "countByExample";

    public static final String SELECT_BY_EXAMPLE               = "selectByExample";

    public static final String SELECT_BY_PRIMARY_KEY           = "selectByPrimaryKey";

    public static final String DELETE_BY_EXAMPLE               = "deleteByExample";

    public static final String DELETE_BY_PRIMARY_KEY           = "deleteByPrimaryKey";

    public static final String INSERT                          = "insert";

    public static final String INSERT_SELECTIVE                = "insertSelective";

    public static final String UPDATE_BY_EXAMPLE               = "updateByExample";

    public static final String UPDATE_BY_EXAMPLE_SELECTIVE     = "updateByExampleSelective";

    public static final String UPDATE_BY_PRIMARY_KEY_SELECTIVE = "updateByPrimaryKeySelective";

    public static final String UPDATE_BY_PRIMARY_KEY           = "updateByPrimaryKey";

    public static final String NULL                            = "NULL";

    public static final String TYPE_NAME                       = "TypeName";

    public static final String LABEL                           = "Label";

    public static final String CLASS_NAME                      = "ClassName";

    public static final String NAME                            = "Name";

    public static final String TABLE_NAME                      = "TableName";

    public static final String DABABASE_NAME                   = "DababaseName";

    public static final String SCHEMA_NAME                     = "SchemaName";

    public SqlSession getSqlSession();

    public int insert(String statement, Object parameter);

    public <T> T insert(T entity);

    public <T> T insertSelective(T entity);

    public int insertAll(String statement, Collection collection);

    public int deleteByPrimaryKey(Class entity, Object key);

    public int deleteByExample(Object example);

    public int delete(String statement, Object parameter);

    public int deleteAll(String statement, Collection collection);

    public int updateByPrimaryKey(Object record);

    public int updateByPrimaryKeySelective(Object record);

    public int updateByExample(Object record, Object example);

    public int updateByExampleSelective(Object record, Object example);

    public int update(String statement, Object parameter);

    public int updateAll(String statement, Collection collection);

    public <T> T selectByPrimaryKey(Class entity, Object key);

    public <T> List<T> selectByExample(Object example);

    public <T> List<T> selectList(String statement, Object parameter);

    public  List  selectAttrList(String statement, Object parameter);

    public <T> T selectOne(String statement,Object parameter);

    public  <T> T  selectAttrOne(String statement, Object parameter);

    public <K,V> Map<K, V> selectMap(String statement, Object parameter, String key);

    public <K,V> Map<K, V> selectAttrMap(String statement, Object parameter, String key);

    public <T> Page<T> selectByPage(String statement, Object parameter, Integer pageNo, Integer limit);

    public  Page<Object> selectByAttrPage(String statement, Object parameter, Integer pageNo, Integer limit);
}
