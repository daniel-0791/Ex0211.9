package wfy.jxnu.dao;

import wfy.jxnu.mo.Customer;
import wfy.jxnu.util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {


    public Customer getCustomer(String cusName,String cusPWD) throws SQLException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{

            connection=JdbcUtils.getConn();
            String  sql=   "select cust_id,cust_no,cust_pwd,cust_img from customert "+
                    "where  cust_no=? and cust_pwd=?";
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,cusName);
            preparedStatement.setString(2,cusPWD);
            resultSet=preparedStatement.executeQuery();

            Customer customer=null;
            if (resultSet.next()){
                customer=new Customer();
                customer.setCustId(resultSet.getInt(1));
                customer.setCustNo(resultSet.getString(2));
                customer.setCustPWD(resultSet.getString(3));
                customer.setCustImg(resultSet.getString(4));
            }

            return customer;

        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }
    public boolean resetPWD(String cusName,String cusPWD) throws SQLException{
        boolean flag=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try{

            connection=JdbcUtils.getConn();

            String sql="update customert \n" +
                    "set cust_pwd=?\n" +
                    "where cust_no=?";
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,cusPWD);
            preparedStatement.setString(2,cusName);
            int intflag=preparedStatement.executeUpdate();
            if (intflag==1) flag=true;
            return flag;

        }finally {
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }

    @Override
    public boolean updateCustomerHeadImg(Customer customer) throws SQLException {
        boolean flag=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try{

            connection=JdbcUtils.getConn();

            String sql="update customert \n" +
                    "set cust_img=?\n" +
                    "where cust_no=?";
            preparedStatement= connection.prepareStatement(sql);

            preparedStatement.setString(1,customer.getCustImg());
            preparedStatement.setString(2,customer.getCustNo());
            int intflag=preparedStatement.executeUpdate();
            if (intflag==1) flag=true;
            return flag;

        }finally {
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }

    public Customer getCustomer(String cusName) throws SQLException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{

            connection=JdbcUtils.getConn();
            String  sql=   "select cust_id,cust_no,cust_pwd from customert "+
            "where  cust_no=?";
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,cusName);
            resultSet=preparedStatement.executeQuery();

            Customer customer=new Customer();
            if (resultSet.next()){
                customer.setCustId(resultSet.getInt(1));
                customer.setCustNo(resultSet.getString(2));
                customer.setCustPWD(resultSet.getString(3));
            }

            return customer;

        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }
    /**
     * 添加新用户
     * @param customer
     * @return
     * @throws SQLException
     */
    public  boolean  addCustomer(Customer customer) throws SQLException{
//        insert into customert(cust_no,cust_pwd)
//        values('abc','1111')

        boolean flag=false;
        if (customer==null)  return flag;

        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try{

            connection=JdbcUtils.getConn();

            String sql="insert into customert(cust_no,cust_pwd)\n" +
                    "values(?,?)";
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,customer.getCustNo());
             preparedStatement.setString(2,customer.getCustPWD());
           int intflag=preparedStatement.executeUpdate();
           if (intflag==1) flag=true;
           return flag;

        }finally {
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }


    }

    public List<Customer> queryCustomer() throws  SQLException{

        List<Customer> customerList=new ArrayList<Customer>();
        Connection connection= JdbcUtils.getConn();
        String sql="SELECT cust_id,cust_no,cust_pwd FROM customert";
        Statement statement=connection.createStatement();

        ResultSet resultSet=  statement.executeQuery(sql);



        while (resultSet.next()){

            Customer customer=new Customer();

            customer.setCustId(resultSet.getInt(1));
            customer.setCustNo(resultSet.getString(2));
            customer.setCustPWD(resultSet.getString(3));

            customerList.add(customer);

        }//结果集封装到了list


        resultSet.close();
        statement.close();
        connection.close();

        return customerList;
    }


}
