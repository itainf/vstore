package com.vstore.framework.base.dialect;


public class MySqlDialect implements Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitSqlString(String sql, int offset, int limit, int count) {
        sql = trim(sql);
        StringBuffer sb = new StringBuffer(sql.length() + 20);
        sb.append(sql);
        if (offset > 0) {
            sb.append(" limit ").append(offset).append(',').append(limit)
                    .append(SQL_END_DELIMITER);
        } else {
            sb.append(" limit ").append(limit).append(SQL_END_DELIMITER);
        }
        return sb.toString();
    }

    @Override
    public String getCountSqlString(String sql) {
        sql = trim(sql);
        StringBuffer sb = new StringBuffer(sql.length() + 10);
        sb.append("SELECT COUNT(1) AS " + RS_COLUMN + " FROM  ( ");
        sb.append(sql);
        sb.append(" ) as CountTable");
        return sb.toString();
    }

    private String trim(String sql) {
        sql = sql.trim();
        if (sql.endsWith(SQL_END_DELIMITER)) {
            sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
        }
        return sql;
    }

}
