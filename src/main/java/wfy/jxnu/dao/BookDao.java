package wfy.jxnu.dao;

import wfy.jxnu.mo.Book;
import wfy.jxnu.mo.OrderItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    public List<Book> getRecommadation(int size) throws SQLException;
    public List<Book> getRecommThisWeek(int size) throws SQLException;
    public List<Book> getRecommadation(int start,int size) throws SQLException;
    //start记录的起始位置

    public int getBookNumber() throws SQLException;
    public Book findById(int id) throws SQLException;
    // 添加订单后，修改商品的数量，传入一个connection为了事务
    public boolean updateBookNum(List<OrderItem> items, Connection connection) throws SQLException;

    public int getBookNumber(String bookTypeName) throws SQLException;

}
