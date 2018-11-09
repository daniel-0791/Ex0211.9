package wfy.jxnu.dao;

import wfy.jxnu.mo.Book;
import wfy.jxnu.mo.OrderItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao {

    // 添加订单项
    public boolean addOrderItem(List<OrderItem> items, Connection connection) throws SQLException;

    // 添加订单项
    public boolean addOrderItem(List<OrderItem> items) throws SQLException;

    public List<Book> findProductByOrderId(String orderid)
            throws SQLException;

    // 根据订单id查询所有的订单项.
    public List<OrderItem> findOrderItemByOrderId(String id)
            throws SQLException;

    // 根据订单id删除订单项
    public void delOrderItemByOrderId(String id) throws SQLException;

}
