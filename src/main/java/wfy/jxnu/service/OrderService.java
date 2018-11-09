package wfy.jxnu.service;

import wfy.jxnu.dao.*;
import wfy.jxnu.mo.Order;
import wfy.jxnu.util.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderService {
    // 添加订单方法
    public void addOrder(Order order){
        //调用dao中添加订单的方法
        OrderDao orderDao=new OrderDaoImpl();

        OrderItemDao orderItemDao=new OrderItemDaoImpl();

        BookDao bookDao=new BookDaoImpl();

        Connection connection=null;

        boolean flag=false;
        try {
            connection= JdbcUtils.getConn();
            connection.setAutoCommit(false);
            //首先把Auto commit设置为false,不让它自动提交
            // 1.调用OrderDao完成向orders表中添加数据
            flag=orderDao.addOrder(order,connection);
            // 2.调用OrderItemDao完成对orderItem表的添加操作
            flag=orderItemDao.addOrderItem(order.getItems(),connection);

            // 3.调用BookDao完成对book表中数量修改操作.
            flag=bookDao.updateBookNum(order.getItems(),connection);

           connection.commit();//进行手动提交（commit）
            System.out.println("提交成功!");
            connection.setAutoCommit(true);
            // 提交完成后回复现场将Auto commit,还原为true,

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }


    }
}
