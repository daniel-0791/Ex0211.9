package wfy.jxnu.dao;

import wfy.jxnu.util.JdbcUtils;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcEx {

    public int callPro3(String bookTypeName) throws SQLException, IOException {
        int bookNum=0;
        System.out.println("-------  start 测试调用存储过程：带输出结果集");
        Connection conn = null;
        CallableStatement callStmt = null;
        conn=JdbcUtils.getConn();
        // 存储过程
        callStmt = conn.prepareCall("{call pro3(?,?)}");

        //给存储过程的第1个参数设置值
        callStmt.setObject(1,bookTypeName);

        //注册存储过程的第2个参数
        callStmt.registerOutParameter(2,java.sql.Types.INTEGER);


        //执行存储过程
        callStmt.execute();
       //得到存储过程的输出参数值
        bookNum=callStmt.getInt(2);


        JdbcUtils.closeStatement(callStmt);
        JdbcUtils.closeConn(conn);

     return bookNum;

    }

    public int callPro4(String bookTypeName) throws SQLException, IOException {
        System.out.println("-------  start 测试调用存储过程：带输出参数和结果集合");
        int bookNum=0;
        Connection conn = null;
        CallableStatement callStmt = null;
        conn=JdbcUtils.getConn();
        // 存储过程
        callStmt = conn.prepareCall("{call pro4(?,?)}");

        //给存储过程的第1个参数设置值
        callStmt.setObject(1,bookTypeName);

        //注册存储过程的第2个参数
        callStmt.registerOutParameter(2,java.sql.Types.INTEGER);
        //执行存储过程

        ResultSet rs=callStmt.executeQuery();

        //得到存储过程的输出参数值

        while( rs.next())
            System.out.println (rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));


        //得到存储过程的输出参数值
        bookNum=callStmt.getInt(2);

        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(callStmt);
        JdbcUtils.closeConn(conn);
        return bookNum;
    }
}
