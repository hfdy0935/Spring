package com.hspedu.chapter25.hw01.dao;

import com.hspedu.chapter25.JDBCUtils.JDBCUtils;
import com.hspedu.chapter25.p6.utilds.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDAO<T> {
    private QueryRunner qr = new QueryRunner();


    public void execute(String sql) {
        try (Connection conn = JDBCUtils.getConnection()) {
            qr.execute(conn, sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 统一dml方法，针对任意表
     *
     * @param sql        要执行的sql语句
     * @param parameters PreparedStatement中给sql中占位符传的参数
     * @return 正整数，表示执行该sql语句所影响的行数
     */
    public int update(String sql, Object... parameters) {
        Connection conn = null;
        try {
            conn = JDBCUtilsByDruid.getConnection();
            int update = qr.update(conn, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, null, conn);
        }
    }

    /**
     * 查询多条数据
     *
     * @param sql        要执行的sql语句
     * @param cls        结果类的反射类
     * @param parameters PreparedStatement中给sql中占位符传的参数
     * @return 结果类实例组成的List
     */
    public List<T> queryMulti(String sql, Class<T> cls, Object... parameters) {
        try (Connection conn = JDBCUtilsByDruid.getConnection()) {
            return qr.query(conn, sql, new BeanListHandler<T>(cls), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询单行数据
     *
     * @param sql        要执行的sql语句
     * @param cls        结果类的反射类
     * @param parameters PreparedStatement中给sql中占位符传的参数
     * @return 结果类的实例
     */
    public T querySingle(String sql, Class<T> cls, Object... parameters) {
        try (Connection conn = JDBCUtilsByDruid.getConnection()) {
            return qr.query(conn, sql, new BeanHandler<T>(cls), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询单行单列
     *
     * @param sql        要执行的sql语句
     * @param cls        查询结果应有的类型的Class实例
     * @param parameters PreparedStatement中给sql中占位符传的参数
     * @return 传的泛型参数
     */
    public <U> U queryScalar(String sql, Class<U> cls, Object... parameters) {
        try (Connection conn = JDBCUtilsByDruid.getConnection()) {
            return qr.query(conn, sql, new ScalarHandler<U>(), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
