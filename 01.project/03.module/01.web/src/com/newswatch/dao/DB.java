package com.newswatch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.newswatch.interfaces.BaseInterface;
import com.newswatch.utils.PropertyUtil;

/**
 * 数据库操作基础类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 18:21
 */
public class DB implements BaseInterface {
    /**
     * 日志处理器
     */
    static Logger logger = Logger.getLogger(DB.class);

    public static Connection getConn() {

        Connection c = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }

        try {
            c = DriverManager.getConnection(PropertyUtil.getInstance().getProperty(MYSQL_CONNECTION));
        } catch (SQLException e) {
            e.printStackTrace();
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                c = null;
            }
        }
        return c;
    }

    public static Statement createStatement(Connection c) {

        Statement stmt = null;
        try {
            stmt = c.createStatement();
        } catch (SQLException e) {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                stmt = null;
            }
        }
        return stmt;
    }

    public static ResultSet executeQuery(Connection c, Statement stmt, String sql) {
        logger.info("query sql:[" + sql + "]");
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                stmt = null;
            }
        }
        return rs;
    }

    public static void executeUpdate(String sql) throws Exception {
        logger.info("update sql:[" + sql + "]");
        Connection conn = DB.getConn();
        Statement stmt = DB.createStatement(conn);
        try {
            stmt.executeUpdate(sql);
        } finally {
            DB.close(stmt);
            DB.close(conn);
        }
    }

    public static void close(ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            rs = null;
        }
    }

    public static void close(Statement stmt) {

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            stmt = null;
        }
    }

    public static void close(Connection c) {

        if (c != null) {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            c = null;
        }
    }

    public static PreparedStatement prepareStatement(Connection c, String sql) {

        PreparedStatement pstmt = null;
        try {
            pstmt = c.prepareStatement(sql);
        } catch (SQLException e) {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                pstmt = null;
            }
        }
        return pstmt;
    }
}
