package com.vstore.framework.base.dialect;


public class OracleDialect implements Dialect {
    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitSqlString(String sql, int offset, int limit, int count) {

        sql = sql.trim();
        boolean isForUpdate = false;
        if(sql.toLowerCase().endsWith(" FOR UPDATE")){
            sql = sql.substring(0, sql.length() - 11);
            isForUpdate = true;
        }
        boolean hasOffset = offset > 0;

        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        if(hasOffset){
            pagingSelect.append("SELECT * FROM ( SELECT ROW_.*, ROWNUM ROWNUM_ FROM ( ");
        }else{
            pagingSelect.append("SELECT * FROM ( ");
        }
        pagingSelect.append(sql);
        if(hasOffset){
            pagingSelect.append(" ) ROW_ ) WHERE ROWNUM_ <= " + (offset + limit) + " AND ROWNUM_ > " + (offset) + "");
        }else{
            pagingSelect.append(" ) WHERE ROWNUM <= " + limit);
        }

        if(isForUpdate){
            pagingSelect.append(" FOR UPDATE");
        }

        return pagingSelect.toString();
    }

    @Override
    public String getCountSqlString(String sql) {
        sql = trim(sql);
        StringBuffer sb = new StringBuffer(sql.length() + 10);
        sb.append("SELECT COUNT(1) AS " + RS_COLUMN + " FROM  ( ");
        sb.append(sql);
        sb.append(" )");
        return sb.toString();
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }

    private String trim(String sql) {
        sql = sql.trim();
        if(sql.endsWith(SQL_END_DELIMITER)){
            sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
        }
        return sql;
    }
}
