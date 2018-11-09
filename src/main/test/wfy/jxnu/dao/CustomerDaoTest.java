package wfy.jxnu.dao;

import wfy.jxnu.mo.Customer;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerDaoTest {

    @Test
    public void queryCustomer() {

        CustomerDaoImpl customerDao=new CustomerDaoImpl();
        List<Customer> customerList=null;
        try {
            customerList=customerDao.queryCustomer();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertNotNull(customerList);
        assertEquals(customerList.size(),4);
    }
}