package com.atguigu.schedule.dao;

import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {
    private static final QueryRunner queryRunner = new QueryRunner();

    /**
     * 查询单条
     *
     * @param clazz Class<T>
     * @param sql   String
     * @param args  Object...
     * @return T
     */
    public T findOne(Class<T> clazz, String sql, Object... args) {
        Connection connection = JDBCUtil.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), args);
        } catch (SQLException e) {
            System.out.println(111111);
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.releaseConnection();
        }
    }

    /**
     * 查询多条
     *
     * @param clazz Class<T>
     * @param sql   String
     * @param args  Object...
     * @return List<T>
     */
    public List<T> findMany(Class<T> clazz, String sql, Object... args) {
        Connection connection = JDBCUtil.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.releaseConnection();
        }
    }

    /**
     * 更新
     *
     * @param sql  sql语句
     * @param args 填充preparedStatement占位的值
     * @return 改变的行数
     */
    public int update(String sql, Object... args) {
        Connection connection = JDBCUtil.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.releaseConnection();
        }
    }
}
