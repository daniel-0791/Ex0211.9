package wfy.jxnu.dao;

import wfy.jxnu.mo.Customer;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class CustomerDaoImplTest {

    @Test
    public void addCustomer() {
        CustomerDao    customerDao=new CustomerDaoImpl();
        Customer customer=new Customer();
        customer.setCustNo("abc");
        customer.setCustPWD("123123");
        boolean flag=false;
        try {
            flag=customerDao.addCustomer(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(flag,false);
    }


@Test
    public void addCustomerOk() {
        CustomerDao    customerDao=new CustomerDaoImpl();
        Customer customer=new Customer();
        customer.setCustNo("abcd");
        customer.setCustPWD("123123");
        boolean flag=false;
        try {
            flag=customerDao.addCustomer(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(flag,true);
    }

     @Test
public void updateCustomerHeadImg(){
         CustomerDao    customerDao=new CustomerDaoImpl();
         Customer customer=new Customer();
         customer.setCustNo("abc");
         customer.setCustImg("custImages/b1.jpg");
         boolean flag=false;
         try {
             flag=customerDao.updateCustomerHeadImg(customer);
         } catch (SQLException e) {
             e.printStackTrace();
         }

         assertEquals(flag,true);
    }
}