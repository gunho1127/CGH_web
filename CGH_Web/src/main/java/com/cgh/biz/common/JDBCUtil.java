package com.cgh.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // JDBC 1단계 : 드라이버 객체 로딩
            DriverManager.registerDriver(new org.h2.Driver());
            // JDBC 2단계 : 커넥션 연결
            String jdbcUrl = "jdbc:h2:tcp://localhost/~/student";
            conn = DriverManager.getConnection(jdbcUrl, "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(PreparedStatement stmt, Connection conn) {
        // JDBC 5단계 : 연결 해제
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        // JDBC 5단계 : 연결 해제
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(stmt, conn);
    }
}
