package wfy.jxnu.dao;

import wfy.jxnu.mo.Customer;
import wfy.jxnu.mo.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    public boolean addOrder(Order order, Connection connection) throws SQLException;
    public boolean addOrder(Order order) throws SQLException;
    // 根据用户的角色来查询订单
    public List<Order> findOrder(Customer user) throws SQLException;
    // 根据id删除订单
    public boolean delOrderById(String id) throws SQLException;

}
