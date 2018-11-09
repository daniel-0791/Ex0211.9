package wfy.jxnu.dao;

import wfy.jxnu.mo.Book;
import wfy.jxnu.mo.OrderItem;
import wfy.jxnu.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrderItemDaoImpl implements OrderItemDao {

    @Override
    public boolean addOrderItem(List<OrderItem> items,Connection connection) throws SQLException {
        boolean flag = false;

        PreparedStatement preparedStatement = null;

        try {



            String sql = "INSERT INTO orderitemt(order_Code,book_id,order_Number,orderItem_id) values (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < items.size(); i++) {
                OrderItem item = items.get(i);
                // 2.手动封装一些信息,利用UUID生成订单号
                String id = UUID.randomUUID().toString();
                preparedStatement.setString(1, item.getOrderId());
                preparedStatement.setInt(2, item.getBookId());
                preparedStatement.setInt(3, item.getBuyNum());
                preparedStatement.setString(4,id);
                // 3) 将一组参数添加到此 PreparedStatement 对象的批处理命令中。
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            flag = true;
            return flag;

        } finally {
            JdbcUtils.closeStatement(preparedStatement);

        }
    }

    @Override
    public boolean addOrderItem(List<OrderItem> items) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = JdbcUtils.getConn();

            String sql = "INSERT INTO orderitemt(order_Code,book_id,order_Number,orderItem_id) values (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);//1,首先把Auto commit设置为false,不让它自动提交
            for (int i = 0; i < items.size(); i++) {
                OrderItem item = items.get(i);
                // 2.手动封装一些信息,利用UUID生成订单号
                String id = UUID.randomUUID().toString();
                preparedStatement.setString(1, item.getOrderId());
                preparedStatement.setInt(2, item.getBookId());
                preparedStatement.setInt(3, item.getBuyNum());
                preparedStatement.setString(4,id);
                // 3) 将一组参数添加到此 PreparedStatement 对象的批处理命令中。
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.commit();//2,进行手动提交（commit）
            System.out.println("提交成功!");
            connection.setAutoCommit(true);
            // 3,提交完成后回复现场将Auto commit,还原为true,

            flag = true;
            return flag;

        } finally {
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }

    @Override
    public List<Book> findProductByOrderId(String orderid) throws SQLException {
        return null;
    }

    @Override
    public List<OrderItem> findOrderItemByOrderId(String id) throws SQLException {
        return null;
    }

    @Override
    public void delOrderItemByOrderId(String id) throws SQLException {

    }
}
