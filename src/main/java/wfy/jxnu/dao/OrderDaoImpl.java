package wfy.jxnu.dao;

import wfy.jxnu.mo.Customer;
import wfy.jxnu.mo.Order;
import wfy.jxnu.util.JdbcUtils;
import wfy.jxnu.util.Mytools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl  implements  OrderDao{

    @Override
    public boolean addOrder(Order order,Connection connection) throws SQLException {
        boolean flag=false;

        PreparedStatement preparedStatement=null;

        try{

            String sql="INSERT INTO ordert(order_code,cust_id,order_time,total_price,total_number,receiverinfo,paystate)\n" +
                    "VALUES (?,?,?,?,?,?,?) ";
            preparedStatement= connection.prepareStatement(sql);

            preparedStatement.setString(1,order.getId());
            preparedStatement.setInt(2,order.getUserId());
            java.util.Date date = new java.util.Date();   // 获取当前时间
            java.sql.Date sql_date = new java.sql.Date(date.getTime()); //转换成java.sql.Date
            preparedStatement.setDate(3, sql_date);
            preparedStatement.setFloat(4,order.getMoney());
            preparedStatement.setInt(5,order.getTotalNum());
            preparedStatement.setString(6,order.getReceiverinfo());
            preparedStatement.setInt(7,order.getPaystate());


            int intflag=preparedStatement.executeUpdate();
            if (intflag==1) flag=true;
            return flag;

        }finally {
            JdbcUtils.closeStatement(preparedStatement);

        }
    }
    @Override
    public boolean addOrder(Order order) throws SQLException {
        boolean flag=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try{

            connection= JdbcUtils.getConn();

            String sql="INSERT INTO ordert(order_code,cust_id,order_time,total_price,total_number,receiverinfo,paystate)\n" +
                    "VALUES (?,?,?,?,?,?,?) ";
            preparedStatement= connection.prepareStatement(sql);

            preparedStatement.setString(1,order.getId());
            preparedStatement.setInt(2,order.getUserId());
            java.util.Date date = new java.util.Date();   // 获取当前时间
            java.sql.Date sql_date = new java.sql.Date(date.getTime()); //转换成java.sql.Date
            preparedStatement.setDate(3, sql_date);
            preparedStatement.setFloat(4,order.getMoney());
            preparedStatement.setInt(5,order.getTotalNum());
            preparedStatement.setString(6,order.getReceiverinfo());
            preparedStatement.setInt(7,order.getPaystate());


            int intflag=preparedStatement.executeUpdate();
            if (intflag==1) flag=true;
            return flag;

        }finally {
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }

    @Override
    public List<Order> findOrder(Customer user) throws SQLException {
        List<Order>  orders=new ArrayList<Order>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try{

            connection=JdbcUtils.getConn();
            String  sql=   "select  order_code,cust_id,order_time,total_Number,total_price,receiverinfo,paystate,cust_no\n" +
                    "from orderInfoView\n" +
                    "where  cust_id=?";
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getCustId());
            resultSet=preparedStatement.executeQuery();


            while (resultSet.next()){
                Order order=new Order();
               order.setId(resultSet.getString(1));

                orders.add(order);
            }

            return orders;

        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }

    @Override
    public boolean delOrderById(String id) throws SQLException {
        boolean flag=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try{

            connection= JdbcUtils.getConn();

            String sql="delete  from  ordert\n" +
                    "where order_code=?";
            preparedStatement= connection.prepareStatement(sql);

            preparedStatement.setString(1,id);

            int intflag=preparedStatement.executeUpdate();
            if (intflag==1) flag=true;
            return flag;

        }finally {
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }
}
