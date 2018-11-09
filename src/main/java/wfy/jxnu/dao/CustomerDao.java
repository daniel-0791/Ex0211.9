package wfy.jxnu.dao;

import wfy.jxnu.mo.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    public List<Customer> queryCustomer() throws SQLException;
    public  boolean  addCustomer(Customer customer) throws SQLException;
    public Customer getCustomer(String cusName) throws SQLException;
    public Customer getCustomer(String cusName,String cusPWD) throws SQLException;
    public boolean resetPWD(String cusName,String cusPWD) throws SQLException;

    /**
     * 更新头像
     * @param customer
     * @return
     * @throws SQLException
     */
    public  boolean  updateCustomerHeadImg(Customer customer) throws SQLException;

}
